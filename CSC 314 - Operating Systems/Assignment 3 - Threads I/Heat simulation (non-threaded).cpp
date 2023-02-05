#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <cctype>
#include <cstring>
#include <unistd.h>
#include <fstream>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <limits.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <iomanip>
#include <cstdlib>

using namespace std;

// Globals
int height = 0;
int columns = 0;
int tinit = 0;
double C0 = 0.0;
double C1 = 0.0;
double C2 = 0.0;
double delta = 0.0;
int maxIterations = 0;

double ** cylinder;
double ** scratch;

double averageTemp = 0;
double maxDelta = 0;

double globalAverageTemp = 0;
int numGlobalTempSamples = 0;

clock_t startClocks = 0;
clock_t endClocks = 0;

void displayCylinderValues(double ** c);
void initCylinder(double ** c, int x);
bool cycle(double ** c);
double calculateCellValue(double ** c, int x, int y);
bool checkForConvergence(double ** prev, double ** curr);
void calculateAverageAndMaxDelta(double ** c);

// calculates average of all the cells in the final iteration
void calculateAverageAndMaxDelta(double ** c) 
{
  double t1 = 0;
  double t2 = 0;
  double p = 0;

  // declared the cylinder...now initialize
  for (int i = 0; i < height; i++) 
  {
    for (int j = 0; j < columns; j++) 
    {
      t1 += c[i][j];
      if((i==0) && (j ==0)) 
        p = c[i][j];
      else 
      {
        t2 = abs(p - c[i][j]);
        if(t2 > maxDelta)
          maxDelta = t2;
      }
    }
  }

  t1 = t1 / (height * columns);
  averageTemp = t1;

  // also calculate the average of ALL iterations
  globalAverageTemp = globalAverageTemp / numGlobalTempSamples;
}

void displayCylinderValues(double ** c) 
{
  double t = 0.0;

  for(int i = 0; i < height; i++) 
  {
    for (int j = 0; j < columns; j++) 
    { //formatting stuff
      cout << right;
      cout << setw(10);
      cout << setprecision(4);

      t = c[i][j] ;
      cout << t;
    }
    cout << endl;
  }
}

void initCylinder(double ** c, int x) 
{
  // cout << " InitCylinder (" << x << ") " << endl;

  for (int i = 0; i < height; i++) 
  {
    for (int j = 0; j < columns; j++)
      c[i][j] = x;  
  }
}

// true if every cell is < delta
bool checkForConvergence(double ** prev, double ** curr) 
{
  double t1, t2, t3;

  // cout << "checkForConvergence prev: " << endl;
  // displayCylinderValues(prev);
  // cout << "checkForConvergence curr: " << endl;
  // displayCylinderValues(curr);

  // calculate the next cell value for each in the cylinder and store in the scratch cylinder
  for(int i = 0; i < height; i++) 
  {
    for(int j = 0; j < columns; j++) 
    {
      t1 = prev[i][j];
      t2 = curr[i][j];

      t3 = abs(t1 - t2);
      if(t3 > delta)
        return false;
    }
  }
  return true;
}
// cycle through the cylinder and calculate the values for the next cycle
// true = we converged on this cycle
// false = we did not converge on this cycle

bool cycle(double ** cyl)
{
  // we need a new cyl or we'll wipe out the old one before we're done with it
  initCylinder(scratch, 0);

  double t1 = 0;

  // calculate the next cell value for each in the cylinder and store in the scratch cylinder
  for (int h = 0; h < height; h++) 
  {
    for (int c = 0; c < columns; c++) 
    {
			if((h == height/2 && c == columns/2) || (h == height/4 && c == columns/4) || (h == height-3 && c == columns-3))
      {
        scratch[h][c] = cyl[h][c];
        continue;
      }
      else
      {
        t1 = calculateCellValue(cyl, h, c);
        scratch[h][c] = t1;

        globalAverageTemp += t1;
        numGlobalTempSamples++;
      }

    }
  }

  // at this point we have both the current iteration (in scratch) and the previous one (in cyl) and
  // check for convergence:
  if(checkForConvergence(cyl, scratch) == true) 
  {
    //cout << " Converged !" << endl;
    return true;
  }
  // this run did NOT converge, clear it for the next run

  // copy the scratch contents back to the original cylinder structure
  for(int i = 0; i < height; i++) 
  {
      for(int j = 0; j < columns; j++) 
      {
        t1 = scratch[i][j];
        cyl[i][j] = t1;
      }
  }
  return false;
}

double calculateCellValue(double ** cyl, int x, int y) 
{
  double t1 = 0;
  double t2 = 0;
  double t3 = 0;
  double X  = 0;
  double n0 = 0;
  double n1 = 0;
  double n2 = 0;
  double n3 = 0;
  double d0 = 0;
  double d1 = 0;
  double d2 = 0;
  double d3 = 0;

  double tempT2 = 0;
  double tmepT3 = 0;

  // The new value of cell x, y  is calculated as follows:
  // – the value of cyl ( x, y ) * coefficient C0
  // – the average of the 4 direct neighbor cells (N0, N1, N2, N3) each multiplied by coefficient C1
  // – the average of the 4 diagonal neighbor cells (D0, D1, D2, D3) each multipled by coefficient C2
  //    D0 N0 D1
  //    N3 x  N1
  //    D3 N2 D2

  X = cyl[x][y];

  // first check the corners...
  if((x == 0) && (y == 0)) //HERE
  {
    // upper left; no d0, n0, d1
    // n3, d3 wrap
    n0 = 0;
    n1 = cyl[x][y + 1];
    n2 = cyl[x + 1][y];
    n3 = cyl[x][columns-1];
    d0 = 0;
    d1 = 0;
    d2 = cyl[x + 1][y + 1];
    d3 = cyl[x+1][columns-1];

    tempT2 = (n1 + n2 + n3 + (n1 + n2 + n3)/3);
    tmepT3 = (d2 + d3 + (d2 + d3));
  }
  else if((x == 0) && (y == (columns-1))) 
  {
    // upper right: no d0, n0, d1
    // n1, d2 wrap
    n0 = 0;
    n1 = cyl[x][0];
    n2 = cyl[x + 1][y];
    n3 = cyl[x][y - 1];
    d0 = 0;
    d1 = 0;
    d2 = cyl[x + 1][0];
    d3 = cyl[x + 1][y - 1];

    tempT2 = (n1 + n2 + n3 + (n1 + n2 + n3)/3);
    tmepT3 = (d2 + d3 + (d2 + d3));
  }
  else if((x == (height-1)) && (y == 0)) //HERE
  {
    //lower left: d3, n2, d2
    // d0, n3 wrap
    n0 = cyl[x - 1][y];
    n1 = cyl[x][y + 1];
    n2 = 0;
    n3 = cyl[x][columns-1];
    d0 = cyl[x - 1][columns-1];;
    d1 = cyl[x - 1][y + 1];
    d2 = 0;
    d3 = 0;

    tempT2 = (n0 + n1 + n3 + (n0 + n1 + n3)/3);
    tmepT3 = (d0 + d1 + (d0 + d1));
  }
  else if((x==(height-1)) && (y==(columns-1))) 
  {
    // lower right: no d2, n2, d3
    // n1, d1 wrap
    n0 = cyl[x - 1][y];
    n1 = cyl[x][0];
    n2 = 0;
    n3 = cyl[x][y - 1];
    d0 = cyl[x - 1][y - 1];
    d1 = cyl[x-1][0];
    d2 = 0;
    d3 = 0;

    tempT2 = (n0 + n1 + n3 + (n0 + n1 + n3)/3);
    tmepT3 = (d0 + d1 + (d0 + d1));
  }
  else if(x == 0) // now check the edges
  {
    // top edge (not in a corner): no d0, n0, d1
    n0 = 0;
    n1 = cyl[x][y + 1];
    n2 = cyl[x + 1][y];
    n3 = cyl[x][y - 1];
    d0 = 0;
    d1 = 0;
    d2 = cyl[x + 1][y + 1];
    d3 = cyl[x + 1][y - 1];

    tempT2 = (n1 + n2 + n3 + (n1 + n2 + n3)/3);
    tmepT3 = (d2 + d3 + (d2 + d3));
  }
  else if(x == (height-1)) 
  {
    // bottom edge (not in a corner): no d3, n2, d2
    n0 = cyl[x - 1][y];
    n1 = cyl[x][y + 1];
    n2 = 0;
    n3 = cyl[x][y - 1];
    d0 = cyl[x - 1][y - 1];
    d1 = cyl[x - 1][y + 1];
    d2 = 0;
    d3 = 0;

    tempT2 = (n0 + n1 + n3 + (n0 + n1 + n3)/3);
    tmepT3 = (d0 + d1 + (d0 + d1));
  }
  else if(y == 0) //HERE
  {
    // left edge (not in a corner): wrap d0, n3, or d3
    n0 = cyl[x - 1][y];
    n1 = cyl[x][y + 1];
    n2 = cyl[x + 1][y];
    n3 = cyl[x][columns-1];
    d0 = cyl[x-1][columns-1];
    d1 = cyl[x - 1][y + 1];
    d2 = cyl[x + 1][y + 1];
    d3 = cyl[x+1][columns-1];

    tempT2 = (n0 + n1 + n2 + n3);
    tmepT3 = (d0 + d1 + d2 + d3);
  }
  else if(y == (columns-1)) 
  {
    // right edge (not in a corner): wrap d1, n1, or d2
    n0 = cyl[x - 1][y];
    n1 = cyl[x][0];
    n2 = cyl[x + 1][y];
    n3 = cyl[x][y - 1];
    d0 = cyl[x - 1][y - 1];
    d1 = cyl[x-1][0];
    d2 = cyl[x+1][0];
    d3 = cyl[x + 1][y - 1];

    tempT2 = (n0 + n1 + n2 + n3);
    tmepT3 = (d0 + d1 + d2 + d3);
  }
  else 
  {
    // in the middle somewhere; no edges to deal with
    n0 = cyl[x - 1][y];
    n1 = cyl[x][y + 1];
    n2 = cyl[x + 1][y];
    n3 = cyl[x][y - 1];
    d0 = cyl[x - 1][y - 1];
    d1 = cyl[x - 1][y + 1];
    d2 = cyl[x + 1][y + 1];
    d3 = cyl[x + 1][y - 1];

    tempT2 = (n0 + n1 + n2 + n3);
    tmepT3 = (d0 + d1 + d2 + d3);
  }

  t1 = C0 * X; // get the center value
  t2 = C1 * tempT2; // get the averages of the neighbors
  t3 = C2 * tmepT3; // get the averages of the diagonals

  double temp;
  temp = t1 + t2 + t3; // add them all up

  return temp;
}

int main(int argc, char *argv[])
{
  int iterations = 0;

	// for (int i=0; i<argc; i++)
  // {
  //   cout << "argv[" << i << "]=" << argv[i] << endl;
  // }

  //set globals with argv[]
  height  = stoi(argv[1]);
  columns = stoi(argv[2]);
  tinit   = stoi(argv[3]);
  C0      = stof(argv[4]);
  C1      = stof(argv[5]);
  C2      = stof(argv[6]);
  delta   = stof(argv[7]);
  maxIterations = stoi(argv[8]);

  //create cylinder shape
  cylinder = new double *[height];

  for (int i = 0; i < height; i++) 
  {
    cylinder[i] = new double[columns];
  }

  // create scratch cylinder
  scratch = new double *[height];

  for(int i = 0; i < height; i++) 
  {
    scratch[i] = new double[columns];
  }

  // initialize the cylinders
  initCylinder(cylinder, tinit);
  //cout << "Initialized cylinder" << endl;
  //displayCylinderValues(cylinder);

  cylinder[height/4][columns/4] = 0;
  cylinder[height/2][columns/2] = 40;
  cylinder[height-3][columns-3] = 0;

  // display the initial cylinder values
  cout << " *** Start of Simulation ***" << endl;
  displayCylinderValues(cylinder);
  cout << endl;

  // cycle through each iteration; if the cycle converges, we're done
  do  
  {
    cout << " Cycle *** " << iterations << endl;
    iterations++;
    // cycle through the calculations on the cylinder
    if(cycle(cylinder) == true) 
    {
      displayCylinderValues(cylinder);
      cout << endl;
      break;
    }
    else 
      displayCylinderValues(cylinder);

    cout << endl;
  }while(iterations <= maxIterations);

  // calculate program execution time
  calculateAverageAndMaxDelta(cylinder);

  // time taken by program goes here
  endClocks = clock();
  double elapsed = double(endClocks - startClocks)/CLOCKS_PER_SEC;
  elapsed = elapsed * 1000000;  // convert it microseconds

  cout << fixed << "Time taken by program: " << elapsed <<  " microseconds" << endl;
  cout << "Num Iterations " << iterations << endl;
  cout << "     Average Temp (last cycle) " << averageTemp << endl;
  //cout << "     Average Temp (all cycles) " << globalAverageTemp << endl;
  cout << "     Max Delta " << maxDelta << endl;
  cout << endl;
  displayCylinderValues(cylinder);

  return 1;
}
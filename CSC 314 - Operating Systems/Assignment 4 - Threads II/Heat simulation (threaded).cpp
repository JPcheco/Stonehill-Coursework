// #include <iostream>
// #include <string>
// #include <vector>
// #include <sstream>
// #include <cctype>
// #include <cstring>
// #include <unistd.h>
// #include <fstream>
// #include <sys/types.h>
// #include <sys/wait.h>
// #include <stdio.h>
// #include <limits.h>
// #include <sys/stat.h>
// #include <fcntl.h>
// #include <iomanip>
// #include <cstdlib>

#include <iostream>
#include <string>
#include <vector>
#include <sstream>
#include <sys/types.h>
#include <fcntl.h>
#include <thread>
#include <iomanip>
#include <cstdlib>
#include <semaphore.h>
#include <thread>
#include <mutex>

using namespace std;

// ./main 10 10 20 .5 .1 .025 .001 10000 > result.txt

// Globals
int height = 0;
int columns = 0;
int tinit = 0;
double C0 = 0.0;
double C1 = 0.0;
double C2 = 0.0;
double delta = 0.0;
int maxIterations = 0;
int requestedThreads = 1;

double ** cylinder;
double ** scratch;

double averageTemp = 0;
double maxDelta = 0;

double globalAverageTemp = 0;
int numGlobalTempSamples = 0;

clock_t startClocks = 0;
clock_t endClocks = 0;

const int MAX_THREADS = 100;
thread t[MAX_THREADS];         // store threads here
sem_t * Sems[MAX_THREADS];     // sems to signal threads

bool terminal[MAX_THREADS];
int counter[MAX_THREADS];

void rowThread( int threadNum );

mutex nextRowMutex;
int nextRowToHandle = 0;
int  getNextRowToHandle ();
void clearNextRowToHandle ();

mutex rowsCompletedMutex;
int rowsCompleted = 0;        // used to track how many rows have been completed
void clearRowsCompleted();
int  bumpRowsCompleted();
int  getRowsCompleted();
void displayRow (int row);
sem_t * displayRowSem;
void createDisplayRowSem();

int iterations = 1;

void displayCylinderValues(double ** c);
void initCylinder(double ** c, int x);
bool cycle(); 
double calculateCellValue(double ** c, int x, int y);
bool checkForConvergence(double ** prev, double ** curr);
void calculateAverageAndMaxDelta(double ** c);

// calculates average of all the cells in the final iteration
void calculateAverageAndMaxDelta(double ** c) 
{
  double t1 = 0;
  double t2 = 0;
  double runningtotal = 0;
  double p = 0;

  p = c[0][0];

  // declared the cylinder...now initialize
  for(int i = 0; i < height; i++) 
  {
    for(int j = 0; j < columns; j++) 
    {
      t1 = c[i][j];
      t2 = abs(p - c[i][j]);

      if(t2 > maxDelta)
        maxDelta = t2;

      runningtotal += t1;
    }
  }

  t1 = t1 / (height * columns);
  averageTemp = t1;

  // calculate the average of ALL iterations
  //globalAverageTemp = globalAverageTemp / numGlobalTempSamples;
}

void displayCylinderValues(double ** c) 
{
  double t = 0.0;

  for(int i = 0; i < height; i++) 
  {
    for (int j = 0; j < columns; j++) 
    { //formatting stuff
      if ( j == 0 )
        cout  << setw (2);
      else
        cout << setw(9);

      cout << right;
      //cout << fixed;
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

  for(int i = 0; i < height; i++) 
  {
    for(int j = 0; j < columns; j++)
      c[i][j] = x;  
  }
}

// true if every cell is < delta
bool checkForConvergence(double ** prev, double ** curr) 
{
  double t1, t2, t3;

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

bool cycle()
{
  int x;
  // we need a new cyl or we'll wipe out the old one before we're done with it
  initCylinder(scratch, 0);

  double t1 = 0;

	for(int i = 0; i < requestedThreads; i++) 
	{      
		sem_post(Sems[i]); // wake up the row threads to process the rows of the cylinder
	}

	// wait until all threads are done...change this out later so we're not spin waiting
	do 
	{
		x = getRowsCompleted();
	}while(x < height);

	//cout << "this cycle is complete" << endl;
	clearRowsCompleted();
	clearNextRowToHandle();  

  // check for convergence:
  if(checkForConvergence(cylinder, scratch) == true) 
  {
    //cout << " Converged !" << endl;
    return true;
  }
  // this run did NOT converge, clear it for the next run

  // copy the scratch contents back to the original cylinder structure
  for(int h = 0; h < height; h++) 
  {
    for(int c = 0; c < columns; c++) 
    {
      if((h == height/2 && c == columns/2) || (h == height/4 && c == columns/4) || (h == height-3 && c == columns-3))
      {
        scratch[h][c] = cylinder[h][c];
        
        continue;
      }
      else
      {
        // t1 = calculateCellValue(cylinder, h, c);
        // scratch[h][c] = t1;

        t1 = scratch[h][c];
        cylinder[h][c] = t1;
      } 
    }
  }
  double ** tmp = scratch;
  scratch = cylinder;
  cylinder = tmp;

  return false;
}

int getNextRowToHandle() 
{
  int x;

  nextRowMutex.lock();
  if(nextRowToHandle == height)
      x = -1;
  else
      x = nextRowToHandle++;
  nextRowMutex.unlock();

  return x;
}

void clearNextRowToHandle() 
{
  nextRowMutex.lock();
  nextRowToHandle= 0;
  nextRowMutex.unlock();
}

void clearRowsCompleted()
{
  rowsCompletedMutex.lock();
  rowsCompleted = 0;
  rowsCompletedMutex.unlock();
}

int getRowsCompleted() 
{
  int x;

  rowsCompletedMutex.lock();
  x = rowsCompleted;
  rowsCompletedMutex.unlock();

  return x;
}

int bumpRowsCompleted() 
{
  int x;

  rowsCompletedMutex.lock();
  x = rowsCompleted++;
  rowsCompletedMutex.unlock();

  return x;
}


void rowThread(int rowNumber) 
{
  int row;
  double t1 = 0;

  //cout << " Inside rowThread " << rowNumber << endl;
  counter[rowNumber] = 0;

  sem_wait(Sems[rowNumber]);

  while(terminal[rowNumber] == false)
  {
    row = getNextRowToHandle(); // grab the next row in the available array

    // if no more rows to process, wait
    if(row == -1)
      sem_wait(Sems[rowNumber]);
    else 
    {
      // debug bump counter; count the number of rows processed by each thread
      counter[rowNumber]++;

      for(int c = 0; c < columns; c++) 
      {
        if((row == height/2 && c == columns/2) || (row == height/4 && c == columns/4) || (row == height-3 && c == columns-3))
        {
          scratch[row][c] = cylinder[row][c];
          
          continue;
        }
        else
        {
          t1 = calculateCellValue(cylinder, row, c);
          scratch[row][c] = t1;
        }    
      }

      /////////
      // problem is here
      /////////
      // if this is the next row to be printed, then display it
      int rowToDisplay = getRowsCompleted();
      if(row != rowToDisplay) 
      {
        sem_wait (displayRowSem);
        sem_post (displayRowSem);
      }
      displayRow(row);
      bumpRowsCompleted();

      // int rowToDisplay = getRowsCompleted();
      // if(row != rowToDisplay) 
      // {
			// 	do
			// 	{
			// 		sem_wait(displayRowSem);
			// 		rowToDisplay = getRowsCompleted();
			// 	}while(row != rowToDisplay);
      // }
      // displayRow(row);
      // bumpRowsCompleted();
			// sem_post (displayRowSem);
      // bumpRowsCompleted();
    }
  }
  //cout << "exiting rowThread " << rowNumber << "  terminal[" << rowNumber << "] = " << terminal[rowNumber] << endl;
}

void displayRow(int row) 
{
  stringstream ss;

  ss.clear(); ss.str("");

  for (int j = 0; j < columns; j++) 
  {
    if(j == 0)
      ss  << setw (2);
    else
      ss << setw(9);

    ss << right;
    ss << setprecision(4);

    double t = scratch[row][j] ;
    ss << t;
  }
  ss << endl;

  rowsCompletedMutex.lock();
  cout << ss.str();
  rowsCompletedMutex.unlock();
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

void createDisplayRowSem() 
{
  stringstream ss;
  int ret;

  // get a unique name for each thread, in this case threadNum0, threadNum1, etc
  ss.clear(); ss.str("");
  ss << "displayRowSem";

  ret = sem_unlink(ss.str().c_str());
  if(ret == -1) 
    cout << "sem_unlink(" << ss.str().c_str() << "):  ret = " << ret << " " << "errno: " << errno << endl;

  //cout << "trying sem_open(" << ss.str().c_str() << ")...";
  displayRowSem = sem_open ( ss.str().c_str(), O_CREAT | O_EXCL, (mode_t)S_IRWXU, 1);

  // if(displayRowSem == (int *)-1) 
  // {
  //   cout << "displayRowSem = sem_open(" << ss.str().c_str() << "): " << "ret = "
  //        << displayRowSem << ", errno: " << errno << endl;

  //   exit (1);
  // }
}

int main(int argc, char *argv[])
{
  //int iterations = 0;
  stringstream ss;

  startClocks = clock();

	cout << endl;
  cout << "***** Heat Simulation (Threaded & Non-Threaded) *****" << endl;
  cout << "Running for values: ";

  for (int i = 1; i < argc; ++i)
      cout << argv[i] << " ";
  cout << endl;

  //set globals with argv[]
  height  = stoi(argv[1]);
  columns = stoi(argv[2]);
  tinit   = stoi(argv[3]);
  C0      = stof(argv[4]);
  C1      = stof(argv[5]);
  C2      = stof(argv[6]);
  delta   = stof(argv[7]);
  maxIterations = stoi(argv[8]);
  requestedThreads = stoi(argv[9]);

  if(requestedThreads > MAX_THREADS)
    requestedThreads = MAX_THREADS;

  //create cylinder/scratch shape
  cylinder = new double *[height];
  scratch = new double *[height];

  for (int i = 0; i < height; i++) 
  {
    cylinder[i] = new double[columns];
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
  cout << " Cycle *** 0" << endl;
  cout << "*****************************************************************************************" << endl;
  displayCylinderValues(cylinder);

  for(int i = 0; i < MAX_THREADS; i++)
    terminal[i]= false;

  if(requestedThreads > 0) // set up threads
  {
    createDisplayRowSem(); // create semaphore to control displaying rows

    for(int i = 0; i < requestedThreads; i++) 
    {
      ss.clear();
      ss.str("");
      ss << "threadSem" << i;

      sem_unlink(ss.str().c_str());

      //cout << "trying sem_open(" << ss.str().c_str() << ")...";
      Sems[i] = sem_open(ss.str().c_str(), O_CREAT | O_EXCL, (mode_t) S_IRWXU, 0);
      terminal[i] = false;

      //cout << "Starting rowThread " << i << endl;
      t[i] = thread(rowThread, i);
    }
  }  // thread prep

  // cycle through each iteration; if the cycle converges, we're done
  do  
  {
    cout << " Cycle *** " << iterations++ << endl;
    cout << "*****************************************************************************************" << endl;

    // cycle through the calculations on the cylinder
    if(cycle() == true) 
    {
      displayCylinderValues(cylinder);
      cout << endl;
      break;
    }
    else 
    {
      if(requestedThreads == 0)
        displayCylinderValues(cylinder);
    }
    cout << endl;
  }while(iterations <= maxIterations);

  // calculate program execution time
  calculateAverageAndMaxDelta(cylinder);

  // time taken by program goes here
  endClocks = clock();
  double elapsed = double(endClocks - startClocks)/CLOCKS_PER_SEC;
  elapsed = elapsed * 1000000;  // convert it microseconds

  cout <<  "********************************** End of Simulation ************************************" << endl;
  cout << fixed << "Time taken by program: " << elapsed <<  " microseconds" << endl;
  cout << "Num Iterations " << iterations << endl;
  cout << "     Average Temp (last cycle) " << averageTemp << endl;
  cout << "     Max Delta " << maxDelta << endl;
  cout << endl;
  displayCylinderValues(cylinder);

  return 1;
}
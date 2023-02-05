#include <Grid.h>
#include <iostream>
#include <sstream>
#include <cstdlib>
#include <time.h>

Grid::Grid()
{

	for (int row=0; row<ROWS; row++)
	{
		for (int col=0; col<COLS; col++)
		{
			grid[row][col] = new Node(row, col);
			//cout << "New Node in Grid created" << endl;
		}
	}

	// link the nodes

	//east/west
	for(int row=0; row<ROWS; row++)
	{
		for(int col=0; col<COLS; col++)
		{
			if(col==0)
			{ 
				grid[row][col]->setEast(grid[row][col+1]);
			}
			else if(col==COLS-1)
			{
				grid[row][col]->setWest(grid[row][col-1]);
			}
			else
			{
				grid[row][col]->setEast(grid[row][col+1]);
				grid[row][col]->setWest(grid[row][col-1]);
			}
			//cout << "Node connected successfully in Grid (E/W)" << endl;
		}		
	}

	//North/South
	for(int col=0; col<COLS; col++)
	{
		for(int row=0; row<ROWS; row++)
		{
			if(row==0)
			{
				grid[row][col]->setSouth(grid[row+1][col]);
			}
			else if(row==ROWS-1)
			{
				grid[row][col]->setNorth(grid[row-1][col]);
			}
			else
			{
				grid[row][col]->setNorth(grid[row-1][col]);
				grid[row][col]->setSouth(grid[row+1][col]);
			}
			//cout << "Node connected successfully in Grid (N/S)" << endl;
		}		
	}
}

Grid::~Grid()
{
	cout << "~Grid(): Got called" << endl;
	if (!isEmpty()) cout << "~Grid(): Memory leak detected!" << endl;
}

bool Grid::isEmpty()
{
	for (int row=0; row<ROWS; row++)
	{
		for (int col=0; col<COLS; col++)
		{	
			if(grid[row][col] != NULL)
				return false;	
		}
	}

	return true;
}

void Grid::deleteRandomNode()
{
	srand(time(NULL));

	int row = rand()%10;
	int col = rand()%10;

	while(grid[row][col] == NULL)
	{
		row = rand()%10;
	  col = rand()%10; 
	}
	deleteNode(row, col);

	//int RandNum = (rand()%100)+1;
	//
	// while(RandNum>0)
	// {
	// 	for (int row=0; row<ROWS; row++)
	// 	{
	// 		for (int col=0; col<COLS; col++)
	// 		{	
	// 			if(grid[row][col] != NULL)
	// 				RandNum--;
	//
	// 			if(RandNum==0)
	// 				deleteNode(row, col);
	// 		}
	// 	}
	// }

}

void Grid::deleteNode(int row, int col)
{	
	//grid[row][col]->~Node();
	delete grid[row][col];
	grid[row][col] = NULL;
}

string Grid::toString()
{
	ostringstream result;

	result << "**************************************************************************" << endl;
	for (int row=0; row<ROWS; row++)
	{
		result << row << ": ";
		for (int col=0; col<COLS; col++)
		{
			if (grid[row][col]!=NULL)
			{
				result << "[" << col << "]: N" << grid[row][col]->getNorthCount() << "" <<
																	 "E" << grid[row][col]->getEastCount()  << "" <<
																	 "S" << grid[row][col]->getSouthCount() << "" <<
																	 "W" << grid[row][col]->getWestCount()  << "."; 
														
			}
		}
		result << endl;
	}
	return result.str();
}
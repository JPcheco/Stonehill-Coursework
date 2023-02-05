.settings
#include <iostream>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>

using namespace std;

int FIFO();
int OPTIMAL();
int LRU();

int REFERENCE_STRING[] = {0, 2, 1, 3, 5, 4, 6, 3, 7, 4, 7, 3, 3, 5, 5, 3, 1, 1, 1, 7, 2, 3, 4, 1};
int length = 24; //REFERENCE_STRING.size();

int main(void)
{
	cout << "***** Quantum/Context Switch Detector *****" << endl;

	int FIFO_pagefaults = FIFO();
	cout << "FIFO page faults: " << FIFO_pagefaults << endl;

	int OPTIMAL_pagefaults = OPTIMAL();
	cout << "OPTIMAL page faults: " << OPTIMAL_pagefaults << endl;

	int LRU_pagefaults = LRU();
	cout << "LRU page faults: " << LRU_pagefaults << endl;

  return 1;
}

int FIFO()
{
	int PAGE_FRAMES[4] = {-1, -1, -1, -1}; // 4 page frames
	int pagefaults = 0;
	int temp;
	bool checker = false;

	for(int i = 0; i < length; i++)
	{ //run through REFERENCE_STRING
		temp = REFERENCE_STRING[i];
		//check for page fault
		for(int j : PAGE_FRAMES)
		{
      if(j == temp)
				checker = true; // no page fault occured
		}

		if(!checker)
		{ //theres a page fault
			pagefaults++;
			//cout << "Page Fault on: " << temp << endl;
			//shift everything in the array and add new value to front 
			for(int x = 4 - 1; x > 0; x--)
				PAGE_FRAMES[x] = PAGE_FRAMES[x - 1];
			PAGE_FRAMES[0] = temp;
		}
		
		cout << "[" << PAGE_FRAMES[0]  << " " << PAGE_FRAMES[1]  << " " << PAGE_FRAMES[2]  << " " << PAGE_FRAMES[3]  << "]" << endl;
		checker = false;
	}
	return pagefaults;
}

int OPTIMAL()
{
	int PAGE_FRAMES[4] = {-1, -1, -1, -1}; // 4 page frames
	int arrayD[4] = {100, 100, 100, 100}; //distance array
	int pagefaults = 0;
	int temp;
	bool checker = false;
	int distance = 0;
	int highest = 0;
	int position = 0;

	for(int i = 0; i < length; i++)
	{ //run through REFERENCE_STRING
		temp = REFERENCE_STRING[i];
		//cout << "temp (REFERENCE_STRING[i]): " << temp << endl;
		for(int j = 0; j < 4; j++)
		{
      if(temp == PAGE_FRAMES[j]) //check for page fault
				checker = true; // no page fault occured
		}

		if(!checker)
		{ //theres a page fault
			pagefaults++;
			//cout << "Page Fault #: " << pagefaults << endl;

			for(int d = 0; d < 4; d++) //loop through PAGE_FRAMES
			{
				for(int value = i; value < length; value++)
				{
					// cout << "D: " << PAGE_FRAMES[d];
					// cout << " Value: " << REFERENCE_STRING[value] << endl;
					if(PAGE_FRAMES[d] != REFERENCE_STRING[value])
					{
						distance++;
						//cout << "distance: " << distance << endl;
					}
					else
					{
						arrayD[d] = distance;
						break;
					}
					arrayD[d] = distance;
				}
				distance = 0;
			}
			
			for(int a = 0; a < 4; a++)
			{
				if(arrayD[a] > highest)
				{
					highest = arrayD[a];
					position = a;
				}
			}
			// cout << "arrayD: [" << arrayD[0]  << " " << arrayD[1]  << " " << arrayD[2]  << " " << arrayD[3]  << "]" << endl;
			// cout << "replaced " << PAGE_FRAMES[position] << " with " << temp << endl;
			PAGE_FRAMES[position] = temp;	
		}
		
		cout << "[" << PAGE_FRAMES[0]  << " " << PAGE_FRAMES[1]  << " " << PAGE_FRAMES[2]  << " " << PAGE_FRAMES[3]  << "]" << endl;
		highest = 0;
		position = 0;
		checker = false;
	}
	return pagefaults;
}

int LRU()
{
	int PAGE_FRAMES[4] = {-1, -1, -1, -1}; // 4 page frames
	int pagefaults = 0;
	int temp;
	bool checker = false;

	for(int i = 0; i < length; i++)
	{ //run through REFERENCE_STRING
		temp = REFERENCE_STRING[i];
		for(int j = 0; j < 4; j++)
		{
      if(temp == PAGE_FRAMES[j]) //check for page fault
			{
				checker = true; // no page fault occured

				for(int x = j; x > 0; x--)
					PAGE_FRAMES[x] = PAGE_FRAMES[x - 1];
				PAGE_FRAMES[0] = temp;
			}		
		}

		if(!checker)
		{ //theres a page fault
			pagefaults++;
			//cout << "Page Fault on: " << temp << endl;
			//shift everything in the array and add new value to front 
			for(int x = 4 - 1; x > 0; x--)
				PAGE_FRAMES[x] = PAGE_FRAMES[x - 1];
			PAGE_FRAMES[0] = temp;
		}
		
		cout << "[" << PAGE_FRAMES[0]  << " " << PAGE_FRAMES[1]  << " " << PAGE_FRAMES[2]  << " " << PAGE_FRAMES[3]  << "]" << endl;
		checker = false;
	}
	return pagefaults;
}
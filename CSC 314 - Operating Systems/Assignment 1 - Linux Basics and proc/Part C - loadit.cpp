#include <iostream>
#include <fstream>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

using namespace std;

int main(void)
{	
	cout << "***** Putting Load on the System *****" << endl;

	float doingSomething = 0;

	int pid;

	pid = fork();

	if(pid == 0) //child
	{
		while(true) 
		{
			doingSomething = doingSomething + 1;
			//cout << doingSomething << endl;

			//Creates and writes to files
			ofstream MyFile("CPUeater1.txt");

			// Write to the file
			MyFile << "CPU Waster number: " << doingSomething << endl;

			// Close the file
			MyFile.close();
		}		
	}
	else //parent
	{
		while(true) 
		{
			doingSomething = doingSomething + 1;
			//cout << doingSomething << endl;

			//Creates and writes to files
			ofstream MyFile("CPUeater2.txt");

			// Write to the file
			MyFile << "CPU Waster number: " << doingSomething << endl;

			// Close the file
			MyFile.close();
		}
	}
}
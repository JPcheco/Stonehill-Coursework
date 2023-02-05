#include<iostream>
#include<Grid.h>

using namespace std;
int main(void)
{	
	cout << "***** C++ Warmup NonRandom Node Delete*****" << endl;
	Grid gridNonRandom;

	cout << gridNonRandom.toString() << endl;

	for (int row=0; row<ROWS; row++)
	{
		for (int col=0; col<COLS; col++)
		{
			gridNonRandom.deleteNode(row,col);
			cout << gridNonRandom.toString() << endl;
		}
	}

	cout << "***** C++ Warmup Random Node Delete*****" << endl;
	Grid gridRandom;
	cout << gridRandom.toString() << endl;

	do
	{
		gridRandom.deleteRandomNode();
		cout << gridRandom.toString() << endl;
	} while (!gridRandom.isEmpty());
}
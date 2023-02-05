#include <Node.h>

#define ROWS 10
#define COLS 10

class Grid
{
	Node* grid[ROWS][COLS];

	public:
		Grid();
		~Grid();
		void deleteRandomNode();
		void deleteNode(int row, int col);
		bool isEmpty();
		string toString();
};
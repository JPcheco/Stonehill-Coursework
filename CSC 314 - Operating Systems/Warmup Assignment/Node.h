#include<string>

using namespace std;

class Node
{
	public:
		int row, col;
		string *data; //danger of having memory leak

		Node *north;
		Node *east;
		Node *south;
		Node *west;

		Node *getWest();
		Node *getEast();
		Node *getNorth();
		Node *getSouth();
		void setWest(Node* n);
		void setEast(Node* n);
		void setNorth(Node* n);
		void setSouth(Node* n);

		//when at node seee how far in the direction you can go
		int getNorthCount(); 
		int getEastCount();
		int getSouthCount();
		int getWestCount();

		Node();
		~Node();
		Node(int rowValue, int colValue);
};
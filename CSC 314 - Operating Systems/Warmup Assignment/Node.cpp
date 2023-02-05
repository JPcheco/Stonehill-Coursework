#include <iostream>
#include <Node.h>

using namespace std;

Node::Node()
{
	north=east=south=west=NULL;
	row=col=0;
	data = new string("Dynamic Memory is AWESOME!");
}

Node::Node(int rowValue, int colValue)
{
	north=east=south=west=NULL;
	row=rowValue;
	col=colValue;
	data = new string("Dynamic Memory is AWESOME!");
}

Node::~Node() //Destuctor
{	//go to neighbors and set their refrence to current node to NULL
	Node* n = getNorth();
	if(n != NULL)
		n->setSouth(NULL);
	
	Node* s = getSouth();
	if(s != NULL)
		s->setNorth(NULL);

	Node* e = getEast();
	if(e != NULL)
		e->setWest(NULL);

	Node* w = getWest();
	if(w != NULL)
		w->setEast(NULL);

	delete data;

	w = NULL;
	s = NULL;
	e = NULL;
	n = NULL;
	data = NULL;
}

//work through the nodes and navigate through them
int Node::getNorthCount()
{	
	Node* temp = NULL;
	int count = 0;

	temp = getNorth();

	while(temp != NULL)
	{
		temp = temp->getNorth();
		count++;
	}
	
	return count;
}

int Node::getEastCount()
{
	Node* temp = NULL;
	int count = 0;

	temp = getEast();

	while(temp != NULL)
	{
		temp = temp->getEast();
		count++;
	}
	
	return count;
}

int Node::getSouthCount()
{	
	Node* temp = NULL;
	int count = 0;

	temp = getSouth();

	while(temp != NULL)
	{
		temp = temp->getSouth();
		count++;
	}
	
	return count;
}

int Node::getWestCount()
{	
	Node* temp = NULL;
	int count = 0;

	temp = getWest();

	while(temp != NULL)
	{
		temp = temp->getWest();
		count++;
	}
	
	return count;
}

void Node::setWest(Node* n) 
{
	west = n;
}

Node* Node::getWest() 
{
	return west;
}

void Node::setEast(Node* n) 
{
	east = n;
}

Node* Node::getEast() 
{
	return east;
}

void Node::setNorth(Node* n) 
{
	north = n;
}

Node* Node::getNorth()
{
	return north;
}

void Node::setSouth(Node* n) 
{
	south = n;
}

Node* Node::getSouth() 
{
	return south;
}
#include "safeQueue.h"

using namespace std;

safeQueue::safeQueue()
{

}

void safeQueue::push(int x)
{
	qMutex.lock();

	if(q.size() < SEATS) 
	{
		q.push(x);
	}

	qMutex.unlock();
}

int safeQueue::pop()
{
	qMutex.lock();

	if(q.empty()) 
	{
		qMutex.unlock();

		// return a 0 if the queue is empty
		return 0;
	}

	int temp = q.front();
	q.pop();

	qMutex.unlock();
	return temp;
}

int safeQueue::size() 
{
	qMutex.lock();

	int temp = q.size();

	qMutex.unlock();
	return temp;
}
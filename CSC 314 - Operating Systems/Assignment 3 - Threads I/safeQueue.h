#include <iostream>
#include <sys/types.h>
#include <unistd.h>
#include <stdlib.h>
#include <semaphore.h>
#include <mutex>
#include <queue>

using namespace std;

class safeQueue
{
	mutex qMutex;
	queue<int>  q;

public:
	safeQueue();
	void push(int x);
	int pop();
};
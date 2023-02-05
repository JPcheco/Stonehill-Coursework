#include <mutex>
#include <queue>

using namespace std;

const int SEATS = 10;

class safeQueue
{
	mutex qMutex;
	queue<int> q;

public:
	safeQueue();
	void push(int x);
	int pop();
	int size();
};
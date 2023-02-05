#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <thread>
#include <unistd.h>
#include <functional>

#include "safeQueue.h"

using namespace std;

void filterThread(safeQueue * upQ, int filter, int threadNum, sem_t * upSem);
void feederThread(int threadNum);
void createSemAndQueue(int threadNum);
void displayStringstream( stringstream * ss);

const int FEEDER_THREAD_INDEX_START = 0;
int feederThreadIndex = FEEDER_THREAD_INDEX_START;

thread t[1000]; //store threads here
bool threadUsed[1000];  // store an indicator which threads are used
bool shutdown[1000];   // command which threads need to shut down to allowed an ordered shutdown


sem_t fSems[1000];
sem_t * filterSems[1000];   // semaphores for each thread to talk to child thread
safeQueue * downQs[1000];   // queue for each thread to send values to each thread

int TERMINUS = -1; //end condition
int numPrimes = 0;
int counter = 0;

safeQueue * msgQ = new safeQueue();

// this thread creates the stream of numbers and feeds them downstream into the filter threads
void feederThread(int threadNum) 
{
  int msg;
  int numBeingProcessed = 2; // start at prime of 2
	int index = threadNum + 1;  // index =thread number of the child we create (downstream partner)
	stringstream ss;

	// ss << "Started the feederThread" << endl;
	// displayStringstream(&ss);
  
	// create a queue to give numbers to be filters down to the child thread and a semaphore to talk to
	// that child thread
	createSemAndQueue(threadNum);

	// ss << "createdSemandQueue" << endl;
	// displayStringstream(&ss);

	// create first filter thread to look for 2's
	threadUsed[index] = true;
  t[index++] =  thread(filterThread, downQs[threadNum], 2, index, filterSems[threadNum]);

	// ss << "Started the feederThread...1" << endl;
	// displayStringstream(&ss);

  do 
  {
    // check the message queue to see if the exit condition was hit
    msg = msgQ->pop();

    if(msg == TERMINUS) 
    {
			ss << "Start the TERMINUS" << endl;
			displayStringstream(&ss);

      // shut down the threads in order
      for(int i = 1000; i > 1; i--) 
      {
        if(threadUsed[i] == true) 
        { // find the last thread and shut down it down 
          cout << " setting shutdown[" << i << "] = true; " << endl;
          shutdown[i] = true;
					sem_post (filterSems[i-1]);

          t[i].join();

					t[i].~thread();
          cout << " feederthread back from t[" << i << "].join() " << endl;
					displayStringstream(&ss);
        }
      }
      break;
    }    
    // push a number down to the first filter
    downQs[threadNum]->push(numBeingProcessed++);

    // wake the filter up then wait until it gets done
		sem_post(filterSems[threadNum]);
		sem_wait(filterSems[threadNum]);

  }while(true);
  cout << "Feeder thread ending" << endl;
  
	
  // send a post downstream just to give them a chance to wake up and cleannup for shutdown
  //sem_post(filterSems[index]);
}

void filterThread(safeQueue * upQ, int filter, int threadNum, sem_t * upSem)
{
	int t1;
	int value = 0;
	string message;
	stringstream ss;
	bool startedNextFilter = false;
	int index = threadNum + 1;

  cout << filter << endl; //this prints the prime number(filter) to the screen

  if(++counter >= numPrimes)
  { //done, push end condition onto queue
    msgQ->push(TERMINUS);

		cout << "Thread " << filter << " reached the end condition" << endl;
		displayStringstream(&ss);

		
    return;
  }

	// create a queue to give numbers to be filters down to the child thread and a semaphore to talk to
	// that child thread
	createSemAndQueue(threadNum);
    
  do // filter
  {
    sem_wait(upSem);
		// first check for the shutdown flag
    if(shutdown[threadNum] == true)
    {
      // watch for the exit condition for current thread,it true... exit
			cout << "filterthread " << threadNum << ": need to kill thread " << threadNum << endl;
			displayStringstream(&ss);
      break;
    }
    
    value = upQ->pop(); // get a value from the parent queue

    // if the value from the queue is evenly divisible by filter, drop it
    if((value % filter) != 0) 
    {
      // if already started the next thread, just pass the number to it
      if(startedNextFilter) 
      {
				// pass the value down to the child
				downQs[threadNum]->push(value);

				// tell our child there's a value in the queue, then wait for her to get done with it
				sem_post(filterSems[threadNum]);
				sem_wait(filterSems[threadNum]);
      }
      else 
      {
       // no child to pass one to. need to create next fikter thread
				startedNextFilter = true;
				threadUsed[index] = true;

				// downQs[threadNum] = my queue to send stuff to this child
				// value = the filter that teh child will use to drop
				// index = the thread number of the child
				// filterSems[threadNum] = the sem used to talk to this child and it back to me
				t[index] = thread (filterThread, downQs[threadNum], value, index, filterSems[threadNum]);
      }
    }
    else
    {
      // necessary else... dont delete this
			// if I can evenly divide this number by my filter, filter it out by dropping it
    }
    sem_post(upSem);
  }while(true);

  ss << "***Thread " << filter << ", threadNum " << threadNum << " is ending" << endl;
	displayStringstream(&ss);
}

void createSemAndQueue(int threadNum)
{
	stringstream ss;
	// ss << "Started createSemAndQueue" << endl;
	// displayStringstream(&ss);

	// create a semaphore to communicate with the threads
	filterSems[threadNum] = &fSems[threadNum];
	int ret = sem_init(filterSems[threadNum], 0, 0);
	if ( ret == -1) 
	{
		ss << "error" << endl;
		displayStringstream(&ss);

	}
	// create a queue to give numbers to be filters down to the child thread
	downQs[threadNum] = new safeQueue();
}

// displays the passed stringstream and then clears it to be ready for the next message
void displayStringstream(stringstream * ss) 
{
	cout << ss->str();
	ss->clear();
	ss->str("");
}

int main(void)
{
	string msg;
  string theLine;
	int numBeingProcessed = 2; //has to start at 2

  cout << "****** Sieve of Eratosthenes ******" << endl;

  cout << "How many primes: ";
  getline(cin, theLine);
  
  if((theLine == "exit") || cin.eof())
    exit(0);

  numPrimes = stoi(theLine);

  if(numPrimes > 1000)
  {
    cout << "Too many primes... now exiting" << endl;
    exit(0);
  }

	//create the feeder thread to feed the number stream to the others
	threadUsed[feederThreadIndex] = true;
	
	t[feederThreadIndex] = thread(feederThread, FEEDER_THREAD_INDEX_START);

	// join the feeder thread to start all the fun
	t[feederThreadIndex].join();
	
	t[feederThreadIndex].~thread();

	cout << "Main ending" << endl;
	sleep(5);

	return 0;
}
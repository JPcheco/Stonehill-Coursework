#include <iostream>
#include <string>
#include <sstream>
#include <fstream>
#include <iomanip>
#include <cstdlib>
#include <queue>
#include <optional>
#include <thread>
#include <unistd.h>
#include <cerrno>
#include <sys/stat.h>
#include <semaphore.h>

using namespace std;

int MAX_READERS = 0;
int rc = 0;
int sleepyR;

sem_t mutex;
sem_t w_mutex;

void* readerThread(void*)
{
	while(true)
	{
		sem_wait(&mutex);
		rc++;
		if(rc == MAX_READERS)
			sem_post(&w_mutex);
		sem_post(&mutex);

		sem_wait(&mutex);
		rc--;
		if(rc == 0)
			sem_post(&w_mutex);
		sem_post(&mutex);

		cout << "R ";
		usleep(sleepyR * 500000);
	}
}

void* writerThread(void*)
{
	srand(time(NULL));

	while(true)
	{
		int sleepytime = rand() % 5 + 1;
		sleepyR = rand() % 3 + 1;

		sem_wait(&w_mutex);
		cout << "W (" << sleepyR << " sec) next W in (" << sleepytime << " secs)" << endl;
		sleep(sleepytime);
		sem_post(&w_mutex);

	}
}

int main(void)
{
	srand(time(0));

	cout << "***** Reader Writer (No Starvation) *****" << endl;
	cout << " Enter N readers: ";
	cin >> MAX_READERS;
	cout << endl;

	sem_init(&mutex, 0, 1);
	sem_init(&w_mutex, 0, 1);

	pthread_t writerT;
	pthread_create(&writerT, NULL, writerThread, NULL);

	pthread_t readers[MAX_READERS];

	for(int i = 1; i < MAX_READERS + 1; i++)
		pthread_create(&readers[i], NULL, readerThread, NULL);

	// have the writer thread join and start the program
	pthread_join(writerT, NULL);

	return 0;
}
#include <iostream>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>
#include <fstream>
#include <string>
#include <sstream>
#include <iomanip>
#include <ctime>
#include <stdio.h>
#include <chrono>
#include <pthread.h>
#include <semaphore.h>

using namespace std;
using namespace chrono;

sem_t sem1;
sem_t sem2;

void * thread1(void * param);
void * thread2(void * param);

void * thread1(void * param)
{
	for(long i = 0; i < 1000; i++)
	{
		// cout << "in thread 1" << endl;
		sem_post(&sem1);
		sem_wait(&sem2);
	}
  pthread_exit(0);//thread exit 	
}

void * thread2(void * param)
{
	for(long i = 0; i < 1000; i++)
	{
		// cout << "in thread 2" << endl;
		sem_post(&sem2);
		sem_wait(&sem1);
	}
  pthread_exit(0);//thread exit 	
}

int getCTX()
{
	ifstream in;
	string s;
	int l = 0;
	string temp = "";

	in.open("/proc/stat");
	for(int i=0; i<10; i++)
		getline(in, s);// cout << s <<endl;
  getline(in, s); //cout << s <<endl;

	l = s.length();
	for (int i = 0; i < l; i++)
	{	
		if(i > 5)
			temp = temp + s[i]; 	
	}
  in.close();

	return stol(temp);
}

void Quantum()
{
	int varieable;

	clock_t c_start = clock(); //start CPU timer
	cout << "Time start: " << c_start << endl;
	double CTX_before = getCTX();  // cout << "CTX_before: " << CTX_before << endl;	

	for(int i; i < 10000000; i++)
		varieable++;
	
	clock_t c_end = clock() ; //take cpu time
	cout << "Time end: " << c_end << endl;
	double CTX_after = getCTX(); //  cout << "CTX_after: " << CTX_after << endl;

	double time_elapsed_ms = (c_end-c_start);
	cout << "CPU time used (in millioseconds): " << time_elapsed_ms << " ms\n";
	double CTX_total = CTX_after - CTX_before;
	cout << "CTX_total: " << CTX_total << endl;
	
	// calculate quantum
	double quantum = time_elapsed_ms / CTX_total;
	cout << "Quantum: " << quantum << endl;
}

int main(void)
{
	cout << "***** Quantum/Context Switch Detector *****" << endl;
  
	pthread_t t1;
	pthread_t t2;

	sem_init(&sem1, 0, 0);
	sem_init(&sem2, 0, 0); 

	Quantum();

	high_resolution_clock::time_point time1 = high_resolution_clock::now(); //take wall clocktime
	clock_t c_start = clock();

	double CTX_before = getCTX();
  pthread_create(&t1,NULL, thread1, NULL);
	pthread_create(&t2,NULL, thread2, NULL);

	pthread_join(t1,NULL);
  pthread_join(t2,NULL);
	double CTX_after = getCTX();
	
	// Stop measuring time and calculate the elapsed time
	clock_t c_end = clock();
	high_resolution_clock::time_point time2 = high_resolution_clock::now();
	duration<double> time_span = duration_cast<duration<double>>(time2 - time1);
	cout << "Wall CLock: " << time_span.count() << " seconds" << endl;
	double time_elapsed_ms = (c_end-c_start);
	cout << "CPU time used: " << time_elapsed_ms/CLOCKS_PER_SEC << " Seconds\n";
	double CTX_total = CTX_after - CTX_before;
	cout << "CTX_total: " << CTX_total << endl;

	double CTXoverhead = CTX_total / ((time_elapsed_ms/CLOCKS_PER_SEC) - time_span.count());
	cout << "CTXoverhead: " << CTXoverhead * 0.00001 << " microseconds" << endl;

	return 1;
}
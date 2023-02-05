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

#include "safeQueue.h"

using namespace std;

// Globals
void doctorThread();
void patientThread(int patientNum);
void displayWaitingRoom();
int  arrivesInWaitingRoom(int p);

int  getNextPatientFromWaitingRoom ();
bool takeExaminingRoomIfEmpty (int p );

void setExaminingRoom(int x);
int  getExaminingRoom();

void displayStringstream( stringstream * ss);
void patientGoesAwayForAWhile( int patientNum, stringstream * ss);

void createPatientSem(int patientNum);
void createDoctorSem();

const int MAX_PATIENTS = 13;
const int docThread = 0;

// array [0] is the doctor thread
// array [1] is patient 1 thread
// array [2] is patient 2 thread, etc

thread t[MAX_PATIENTS+1]; // store the threads

sem_t pSem[MAX_PATIENTS+1];
sem_t * patientSem[MAX_PATIENTS+1];

sem_t docSem;
sem_t * doctorSem = &docSem;

safeQueue * room = new safeQueue; // 10 seats in the waiting room

mutex waitingRoomMutex;
mutex examiningRoomMutex;
int examiningRoom;

int main(void)
{
	stringstream ss;

	createDoctorSem(); //init all the semaphores

	for(int i = 1; i < MAX_PATIENTS + 1; i++)
		createPatientSem(i);

	cout << "****** Welcome to the Doctor's Office ******" << endl;

	displayWaitingRoom();

	t[docThread] = thread(doctorThread); // create the doctor thread

	// create some patient threads
	for(int i = 1; i < MAX_PATIENTS + 1; i++) 
		t[i] = thread(patientThread, i);

	// join and let the program work.
	t[docThread].join();

	// kill all the patient threads
  return 1;
}

///////////////////////////////////////
//////// METHODS //////////////////////
///////////////////////////////////////

void displayWaitingRoom()
{
	stringstream ss;

	safeQueue *q = new safeQueue;
	int x;

	waitingRoomMutex.lock();

	int size = room->size();

	if(size > 0)
		x = 1;

	ss << "Patients in room: [ ";
	
	for(int i = 0; i < size; i++)
	{
		x = room->pop();
		q->push(x); // saves each entry in order

		ss << x << " ";
	}
	for(int i = 0; i < (SEATS - size); i++)
	{
		ss << "* ";
	}
	ss << "]" << endl;

	// put back into the OG queue
	for(int i = 0; i < size; i++)
	{
		x = q->pop();
		room->push(x);
	}

	waitingRoomMutex.unlock();

	displayStringstream(&ss);
}

void doctorThread()
{
	stringstream ss;
	int p;
	int nextP;

	ss << "The Doctor is in." << endl;
	displayStringstream(&ss);

	do
	{
		p = getExaminingRoom();
		if(p == 0) //examiningRoom is empty
		{
			ss << "Doctor: nobody in the examiningRoom.. time to go nap. " << endl;
			displayStringstream(&ss);

			int ret = sem_wait(doctorSem);
			if(ret != 0)
			{
				//cout << "ret = " << ret << " " << "errno; " errno << endl;
			}

			ss << "Doctor: My Patient has arrived I just woke up " << endl;
			displayStringstream(&ss);
		}

		// look around the examining room
		p = getExaminingRoom();
		if(p > 0)
		{
			ss << "Doctor starts examining patient " << p << endl;
			displayStringstream(&ss);

			//spend time to examining
			int r = rand() % 3;
			sleep(r);

			ss << "Doctor examination done of patient " << p << endl;
			displayStringstream(&ss);

			//release patient
			sem_post(patientSem[p]);

			//check examiningRoom
			nextP = getNextPatientFromWaitingRoom();

			//set nextp to be next patient
			setExaminingRoom(nextP);

			//check to see if examiningRoom empty
			if(nextP == 0)
				continue;

			if(examiningRoom > 0)
			{
				ss << "Patient " << nextP << " being taken next." << endl;
				displayStringstream(&ss);
			}
		}
	}while(true);

	cout << " DoctorThread ending" << endl;
}

void patientThread(int patientNum)
{
	stringstream ss;
	int ret;
	bool result;
	int chairsAvailable;

	ss << "Patient: " << patientNum << " thread is now alive." << endl;
	displayStringstream(&ss);

	do
	{
		//if no other patients, go into examiningRoom
		ss << "Patient: " << patientNum << " arrives." << endl;
		displayStringstream(&ss);

		result = takeExaminingRoomIfEmpty(patientNum);
		if(result == true)
		{
			ss << "Patient " << patientNum << " enters the exam room and kicks doctor awake" << endl;
			displayStringstream(&ss);

			sem_post(doctorSem); //wakes the doctor up form his nappy nap

			ss << "Patient " << patientNum << " waiting for the doc to finish" << endl;
			displayStringstream(&ss);

			ret = sem_wait(patientSem[patientNum]); // wait sem for doc to finish
			if(ret == -1)
			{
				cout << "sem_wait( patientSem[" << patientNum << "] failed:  ret = " << ret << " " << "errno; " << errno << endl;
			}

			ss << "Patient " << patientNum << ": my exam is done " << endl;
			displayStringstream(&ss);

			ss << "Patient " << patientNum << ": going away for a while before my next visit..."  << endl;
			patientGoesAwayForAWhile( patientNum,&ss ); 

		}
		else
		{
			chairsAvailable = arrivesInWaitingRoom(patientNum);
			if(chairsAvailable == 0) //waiting room is full
			{
				ss << "Patient: " << patientNum << " leaves without being examined because no chairs available." << endl;
				patientGoesAwayForAWhile( patientNum, &ss );
			}
			else
			{
				ss << "Patient: " << patientNum << "  sees " << chairsAvailable << " chairs available, sits down, and waits to be examined." << endl;
				displayStringstream(&ss);

				//seat available, wait until the doc sees or kicks us out
				sem_wait(patientSem[patientNum]);
			}
		}
	}while(true);
}

void patientGoesAwayForAWhile(int patientNum, stringstream * ss)
{
	displayStringstream(ss);

	int r = (rand() % 10) + 30;
	r += (patientNum * 2);

	sleep(r);

	*ss << "Patient " << patientNum << ": is back for another visit" << endl;
	displayStringstream(ss);
}

int getNextPatientFromWaitingRoom()
{
	int nextP;

	waitingRoomMutex.lock();

	nextP = room->pop();

	waitingRoomMutex.unlock();

	return nextP;
}

int arrivesInWaitingRoom(int p)
{
	int result;
	int x;

	waitingRoomMutex.lock();

	x = room->size();
	if(x >= SEATS)
	{
		result = 0;
	}
	else
	{
		room->push(p);

		result = (SEATS - (x + 0));
		if(result < 0)
			result = 0;
	}

	waitingRoomMutex.unlock();

	displayWaitingRoom();

	return result;
}

int getExaminingRoom()
{
	int result;

	examiningRoomMutex.lock();
	result = examiningRoom;
	examiningRoomMutex.unlock();

	return result;
}

void setExaminingRoom(int x)
{
	examiningRoomMutex.lock();
	examiningRoom = x;
	examiningRoomMutex.unlock();
}

bool takeExaminingRoomIfEmpty(int p)
{
	bool result = false;

	examiningRoomMutex.lock();

	if(examiningRoom == 0)
	{
		examiningRoom = p;
		result = true;
	}

	examiningRoomMutex.unlock();

	return result;
}

void createDoctorSem()
{
	int ret;
	stringstream ss;

	//ss << "trying to init sem_init(doctorSem, 0, 0) " ;
	//displayStringstream(&ss);

	ret = sem_init(doctorSem, 0, 0);

	if(ret == -1)
	{
		ss << "trying to init sem_init(doctorSem, 0, 0) ";
		//ss << "failed, ret == -1, errno = " << errno << " = " << strerror(errno) << endl;
		displayStringstream(&ss);

		exit (0);
	}
	else
	{
		//ss << "succeeded" << endl;
		//displayStringstream(&ss);
	}
}

void createPatientSem(int threadNum)
{
	int ret;
	stringstream ss;

	// ss << "trying to init sem_init(patientSem[" << threadNum << "], 0, 0)";
	// displayStringstream(&ss);

	// initialize the pointer array location for threadNum
	patientSem[threadNum] = &pSem[threadNum];

	ret = sem_init(patientSem[threadNum], 0, 0);

	if(ret == -1)
	{
		ss << "trying to init sem_init ( [patientSem[" << threadNum << "], 0, 0)" ;
		//ss << "failed, ret == -1, errno = " << errno << " = " << strerror(errno) << endl;
		displayStringstream(&ss);

		exit (0);
	}
	else
	{
		//ss << "succeeded" << endl;
		//displayStringstream(&ss);
	}
}

void displayStringstream(stringstream * ss) 
{
	cout << ss->str();
	ss->clear();
	ss->str("");
}
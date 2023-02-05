#include <iostream>
#include <cstdlib>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>

using namespace std;

void* SThread(void * param);
void* AThread(void * param);
void* DThread(void * param);

//create semaphores
sem_t jimmiesSem, ice_creamSem, chocolate_sauceSem;

int main(void)
{
  cout << "***** Little Rascals *****" << endl;

  //intitialize semaphores
  sem_init(&jimmiesSem, 0, 0);
  sem_init(&ice_creamSem, 0, 0);
  sem_init(&chocolate_sauceSem, 0, 0);

  // Thread identifier
  pthread_t Spanky, Alfalfa, Darla;  

  // Create thread, no parameters to starting function
  pthread_create(&Spanky,NULL,SThread,NULL);
  pthread_create(&Alfalfa,NULL,AThread,NULL);
  pthread_create(&Darla,NULL,DThread,NULL);

  string items [3] = {"JIMMIES", "ICE_CREAM", "CHOCOLATE_SAUCE"}; // store the items 

  while(true)
  { // generates a random number to determin which item Miss Crabtree will put out
    int random = rand() % 3; // 0 = JIMMIES, etc...
    string item = items[random];
    cout << "Miss Crabtree puts " << item << " on the table." << endl;

    if(item == "JIMMIES")
		  sem_post(&jimmiesSem);
		else if(item == "ICE_CREAM")
		  sem_post(&ice_creamSem);
		else if(item == "CHOCOLATE_SAUCE")
			sem_post(&chocolate_sauceSem);

    sleep(3);
  }
  // Wait for thread to exit ... should never get here
  pthread_join(Spanky,NULL);
  pthread_join(Alfalfa,NULL);
  pthread_join(Darla,NULL);
}

void* SThread(void * param)
{
  while(true)
  {   
    sem_wait(&jimmiesSem);

    int random = 1+ rand() % 3; //generates waiting time in seconds
    cout << "Spanky eats some ice cream for " << random << " seconds." << endl << endl;
    sleep(random);
  }
  pthread_exit(0);
}

void* AThread(void * param)
{
  while(true)
  {   
    sem_wait(&ice_creamSem);

    int random = 1+ rand() % 3; //generates waiting time in seconds
    cout << "Alfalfa eats some ice cream for " << random << " seconds." << endl << endl;
    sleep(random);
  }
  pthread_exit(0);
}

void* DThread(void * param)
{
  while(true)
  {   
    sem_wait(&chocolate_sauceSem);

    int random = 1+ rand() % 3; //generates waiting time in seconds
    cout << "Darla eats some ice cream for " << random << " seconds." << endl << endl;
    sleep(random);
  }
  pthread_exit(0);
}
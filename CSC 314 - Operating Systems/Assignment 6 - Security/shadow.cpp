#define _XOPEN_SOURCE
#include <unistd.h>

#include <iostream>
#include <stdio.h>
#include <pwd.h>
#include <stdlib.h>
#include <string.h>
#include <shadow.h>
#include <errno.h>
#include<fstream>
#include<string>
#include<sstream>
#include<iomanip>

using namespace std;

extern int errno;

char *crypt(const char *key, const char *salt);

int main(int argc, char *argv[])
{
  char user[100];

    // Send the login prompt
    cout << "login: ";
    cout.flush();

    cin >> user;

    // Lookup password structure for userid
    struct spwd* pwd = getspnam(user);

    // If lookup fails, userid doesn't exist
    // Send error message and start over
    if (!pwd) {
      cout << "User does not exist." << endl;
      //continue;
    }

    cout << "/etc/shadow entry: " << pwd->sp_pwdp << endl;

  
  ifstream infile;
  string word;
  string password;


  infile.open("words.txt");

  while(getline(infile, word)) 
  {
    char * enc = crypt(word.c_str(), "$6$4cpIvwss$");
    password = pwd->sp_pwdp;

    // cout<< "password: " << password << endl;
    // cout<< "enc: " << enc << endl;
    //cout<< "word: " << word << endl;

    if(password == enc)
    {
      cout << "password is: " << word << endl;
      exit(0);
    } 
    
  }

  

}

// $6$4cpIvwss$Vsq6WbJv0foZt82FgOdoKCeHAaQYups2UwGG37xWT8Kz6vzhmFrCBCNolXIHFsQg8K5L5w9cus.usf8UlAA6./
#define _XOPEN_SOURCE
#include <unistd.h>

#include <iostream>
#include <stdio.h>
#include <pwd.h>
#include <stdlib.h>
#include <string.h>
#include <shadow.h>
#include <errno.h>

using namespace std;

extern int errno;

char *crypt(const char *key, const char *salt);

int main(int argc, char *argv[])
{
  while (true) {
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
      continue;
    }

    cout << "/etc/shadow entry: " << pwd->sp_pwdp << endl;
  }
}

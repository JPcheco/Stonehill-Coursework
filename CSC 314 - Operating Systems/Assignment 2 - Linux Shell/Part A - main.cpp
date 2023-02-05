#include <iostream>
#include <string>  
#include <vector>  
#include <sstream>
#include <cctype>
#include <cstring>
#include <unistd.h>
#include <fstream>  
#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <limits.h>

using namespace std; //introduces namespace std

int main(void)
{// Initialization stuff
	string theLine, directory;
	int argc;
	char * argv[100]; 

	while (true)
	{
		cout << "JacobShell> ";
		getline(cin,theLine);
		cout << "Command was: " << theLine << endl;
		if((theLine == "exit") || cin.eof())
		{
			exit(0);
		}

		//////////////////////////////////////////////////////////////////
		// Determine the command name, and construct the parameter list //
		//////////////////////////////////////////////////////////////////

		//init the array of char pointers to NULL
		for(int i = 0; i<= 100; i++)
		{
			argv[i] = NULL;
		}

		bool initializing = true;
		int index = 0; // initialize the index of params
		string theLine_copy1, theLine_copy2; 
		string current_letter, holder, s1;

		theLine_copy1 = theLine;
		theLine_copy2 = theLine;

		while(initializing)
		{
			theLine_copy2 = theLine_copy1;

			for(int i = 0; i < theLine_copy1.size(); i++)
			{
				current_letter = theLine_copy2.front();

				if(current_letter == " ")
				{
					theLine_copy2.erase(0,1);
					break;
				}
				else
				{
					holder.append(current_letter);
					theLine_copy2.erase(0,1);
				}
			}
			theLine_copy1 = theLine_copy2;

			argv[index] = strdup(holder.c_str());

			//cout << "*argv[" << index << "]: " << argv[index] << endl;
			index++;
			holder.clear();

			if(theLine_copy1.empty() == true)
			{
				argc = index;
				initializing = false;
			}
		}
	
		//////////////////////////////////////////
		// Find the full path name for the file //
		//////////////////////////////////////////

		// A name that begins with a "/" "./" or "../" is an absolute pathname 
		//that can be used to launch the execution

		char * pathname;
		pathname = argv[0];

		//cout<<"pathname: " << pathname << endl;

		char temp;
		string command, nonsymbols, w;

		ifstream in;

		temp = pathname[0];

		if(isalpha(temp) == 0) //starts with non alphabetical symbol
		{
			for(int x = 0; x < strlen(pathname); x++)
			{
				temp = pathname[x];
				if(isalpha(temp) == 0)
					command += temp;
				else	
					nonsymbols += temp;
			}
			//cout<<"command: "<< command <<endl;

			char cwd[PATH_MAX];
			if(getcwd(cwd, sizeof(cwd)) != NULL) 
				w += cwd;
			//cout << "Current working dir: " << w <<endl;

			if(command == "/")
				w = ""; //essentailly goes to nothing
			else if(command == "./")
				w = w + ""; //stays the same
			else if(command == "../")
			{ //go back one directory
				for(int x = 0; x < w.size(); x++)
				{		
					w.pop_back();
					if(w[w.size()-x] == '/')
					{
						w.append("o");
						break;
					}
				}
			}

			directory = w;	
			directory.append("/");
			directory.append(nonsymbols);

			cout << "Current working dir: " << directory <<endl;

			in.open(directory); //openfile
			if(in.is_open())
			{
				cout << "File can be opened in " << directory << endl;
				directory = "";
				in.close();
			}
			else
			{
				cout << "File does not exist in " << directory << endl;
				directory = "";
			}
		}	
		// Otherwise, your program must search each directory in the list 
		//specified by the PATH environment variable to find the relative pathname. 
		else
		{
			string s, s_copy, c_pathname, current;
			bool processing = true;

			//gets the non command symbols
			for(int x = 0; x < strlen(pathname); x++)
			{
				temp = pathname[x];
				if(isalpha(temp) != 0)
					nonsymbols += temp;
			}

			if(nonsymbols != "ls" && nonsymbols != "cat")
			{	//do if not ls or cat
				const char* h = getenv("PATH");
				s += h;

				//cout << "echo $PATH: " << s << endl;

				while(processing == true)
				{
					s_copy = s; // copy of "echo $PATH"
					//cout << "(copy) echo $PATH: " << s_copy << endl;

					for(int x = 0; x < s.size(); x++)
					{
						current = s_copy.front();
						//cout << "current: " << current << endl;
						if(current==":")
						{
							s_copy.erase(0,1);
							break;
						}
						else
						{
							directory.append(current);
							s_copy.erase(0,1);
						}
					}
					s = s_copy;
					directory.append("/");
					directory.append(nonsymbols);

					//cout << "nonsymbols: " << nonsymbols << endl;

					in.open(directory); //openfile
					if(in.is_open())
					{
						cout << "File can be opened in " << directory << endl;
						directory = "";
						in.close();
					}
					else
					{
						cout << "File does not exist in " << directory << endl;
						directory = "";
					}

					if(s.size()==0)
						processing = false;
				}
				processing = true;
			}
		}

		////////////////////////////////////////////////////////////////////
		// Launch the executable file with the specified parameters using //
		// the execvp command and the argv array. 												//
		////////////////////////////////////////////////////////////////////

		// Child 
		if(fork() == 0) 
		{ 
			execvp(argv[0], argv); 
		} // Parent 
		else 
		{ 
			int status=0; 
			wait(&status); 
			cout << "Child exited with status of " << status << endl; 
		} 
	}
}
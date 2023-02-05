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

string find_directory(string w, string nonsymbols)
{
	ifstream in;
	string directory;

	directory = w;	
	directory.append("/");
	directory.append(nonsymbols);

	//cout << "Current working dir: " << directory <<endl;

	in.open(directory); //openfile
	if(in.is_open())
	{
		return directory;
		//cout << "File can be opened in " << directory << endl;
		directory = "";
		in.close();
	}
	else
	{
		//cout << "File does not exist in " << directory << endl;
		directory = "";
	}
}

int main(void)
{// Initialization stuff
	string theLine, directory, fullpath;
	int argc;
	char * argv[100]; 

	while (true)
	{
		cout << "JacobShell> ";
		getline(cin,theLine);
		//cout << "Command was: " << theLine << endl;
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

			//out << "*argv[" << index << "]: " << argv[index] << endl;
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

		// cout<<"pathname: " << pathname << endl;
		// cout<<"pathname character: " << pathname[strlen(pathname)-1] << endl;

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
			fullpath = find_directory(w, nonsymbols);
		}	
		// Otherwise, your program must search each directory in the list 
		//specified by the PATH environment variable to find the relative pathname. 
		else
		{
			string s, s_copy, c_pathname, current, w;
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
					w = directory;

					fullpath = find_directory(w, nonsymbols);
					directory = "";
					w = "";

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

		int checker = 0;
		string amp = "&";
		int result;

		// cout << argv[argc-1] << endl;
		//cout << amp.c_str() << endl;

		char * newargv[100]; //making newargv to use in execvp
		bool newinitializing = true;
		int newindex = 0;
		int newargc = 0;

		result = strcmp(argv[argc-1], amp.c_str());
		//cout << "result: " << result << endl;

		if(result == 0) //enters if ends in &
		{
			cout << "encountered a '&'!!" << endl;
			checker = 1; 

			while(newinitializing)
			{
				if(strcmp(argv[newindex], amp.c_str()) == 0)
					newinitializing = false; 
				else
					newargv[newindex] = argv[newindex];
			
				newindex++;
			}
			newinitializing = true;
			newargc = newindex - 1;
		}

		for(int i = 0; i < 1; i++)
		 	cout << "newargv[" << i << "]: " << newargv[i] << endl;

  	cout << "Executing Fork()" << endl;
		
		// Child 
		if(fork() == 0) 
		{ //does it take the whole areguement or just the nonsymbols???
			if(checker == 1)
				execvp(newargv[0], newargv); 
			else
				execvp(argv[0], argv);
		} // Parent 
		else 
		{ 
			if(checker == 1)
			{
				int status=0;
				cout << "Child exited with status of " << status << endl;
			}
			else
			{
				int status=0; 
				wait(&status); 
				wait(&status);
				cout << "Child exited with status of " << status << endl;
			}
		}

		cout << endl; 
	}
}
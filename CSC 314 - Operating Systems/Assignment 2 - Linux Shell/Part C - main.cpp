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
#include <sys/stat.h>
#include <fcntl.h>

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

// get the next word after position p
void getNextWord( string theLine, int p, string & name)
{
	string t;
	int max_length;

	max_length = theLine.length();

	p++;  //skip past the operator
	while(theLine.at(p) == ' ')
		p++;

	while(theLine.at(p) != ' ') 
	{
		name.append(1,theLine.at(p));
		p++;

		if(p >= max_length)
			return;
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

		// cout<<"pathname: " << pathname << endl;
		// cout<<"pathname character: " << pathname[strlen(pathname)-1] << endl;

		char temp;
		string command, nonsymbols, w;

		ifstream in;

		temp = pathname[0];

		if(isalpha(temp) == 0) //starts with non-alphabetical symbol
		{
			w = "";

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

		///////////////////////////////////////////////////////
		///////////////////////////////////////////////////////
		///////////////////////////////////////////////////////

		string ampersand = "&";
		string less_than = "<";    
		string greater_than = ">";  
		string pipe_operator = "|";

		int pid;
		int result;
		int length = 1;
		int found = 0;
		int check = 0;
		int checkAMP = 0;

		string inbound_file;
		string outbound_file;
		string pipe_file ;
		bool gotAmpersand = false;
		int rr;

		found = theLine.find(ampersand, ampersand.size());
		if(found!=std::string::npos) 
		{
			cout << "found " << ampersand << " at: " << found << endl;
			checkAMP++;
			gotAmpersand = true;
		}

		found = theLine.find(less_than, less_than.size());
		if(found!=std::string::npos) 
		{
			cout << "found " << less_than << " at: " << found << endl;
			check++;
			// get the next word after the operator
			getNextWord(theLine, found, inbound_file);
		}

		found = theLine.find(greater_than, greater_than.size());
		if(found != std::string::npos) 
		{
			cout << "found " << greater_than << " at: " << found << endl;
			check++;
			// get the next word after the operator
			getNextWord(theLine, found, outbound_file);
		}

		found = theLine.find(pipe_operator, pipe_operator.size());
		if(found != std::string::npos) 
		{
			// cout << "found " << pipe_operator << " at: " << found << endl;
			check++;
			// get the next word after the operator
			getNextWord(theLine, found, pipe_file);
		}

		///////////////////////////////////////////
		// get rid of everything after the <,>,| //
		///////////////////////////////////////////

		char * newargv[100]; //making newargv to use in execvp
		bool newinitializing = true;
		int newindex = 0;
		int newargc = 0;

		if(check != 0)
		{
			while(newinitializing)
			{
				if(strcmp(argv[newindex], less_than.c_str()) == 0 || strcmp(argv[newindex], greater_than.c_str()) == 0 || strcmp(argv[newindex], pipe_operator.c_str()) == 0)
					newinitializing = false; 
				else
					newargv[newindex] = argv[newindex];
			
				newindex++;
			}
			newinitializing = true;

			//cout << "newindex: " << newindex << endl;
			newargc = newindex - 1;
		}

		if(checkAMP != 0)
		{
			while(newinitializing)
			{
				if(strcmp(argv[newindex], ampersand.c_str()) == 0)
					newinitializing = false; 
				else
					newargv[newindex] = argv[newindex];
			
				newindex++;
			}
			newinitializing = true;

			//cout << "newindex: " << newindex << endl;
			newargc = newindex - 1;
		}

	  // for(int i = 0; i < 2; i++)
		//  	cout << "newargv[" << i << "]: " << newargv[i] << endl;

		//////////////////////////////////////////////////////////////////////
		// Launch the executable file with the specified parameters using   //
		// the execvp command and the argv array.		  				    					//
		//////////////////////////////////////////////////////////////////////

		// handle pipe in the execution section below

		cout << "Fork() execution section" << endl;
		//  cout << "argv[0]: " << argv[0] << endl;
		/// do the pipe here
		if(pipe_file.length() > 0)
		{
			char * PreP_argv[100]; 
			char * PostP_argv[100]; 
			bool Pnewinitializing = true;
			int Pindex = 0;
			int PreP_argc = 0;
			int PostP_argc = 0;

			//cout << "made it here inside the pipe section" << endl;
			//split argv into pre and post pipe operator
			if(check != 0)
			{
				while(Pnewinitializing)
				{
					if(strcmp(argv[Pindex], pipe_operator.c_str()) == 0)
					{
						//cout << "encountered a pipe operator" << endl;
						newinitializing = false;
						PostP_argv[0] = argv[3];
						//cout << "PostP_argv[0]: " << PostP_argv[0] << endl;
						break;
					}						 
					else
						PreP_argv[Pindex] = argv[Pindex];

					//cout << "PreP_argv[" << Pindex << "]: " << PreP_argv[Pindex] << endl;
					Pindex++;
				}
				Pnewinitializing = true;

				//cout << "newindex: " << newindex << endl;
				newargc = Pindex - 2;

				PreP_argv[2] = NULL;
				PostP_argv[1] = NULL;
			}

			// cout << "PreP_argv[0]: " << PreP_argv[0] << endl;
			// cout << "PreP_argv[1]: " << PreP_argv[1] << endl;
			// cout << "PostP_argv[0]: " << PostP_argv[0] << endl;

			if(fork() == 0) //child
			{
				int thePipe[2];
				pipe(thePipe);

				if(fork() == 0)
				{
					// cout << "in the child process of the pipe." << endl;

					close(thePipe[1]);
					close(0);
					dup(thePipe[0]);
					close(thePipe[0]);

					//execvp post pipe_operator
					execvp(PostP_argv[0], PostP_argv);
				}
				else
				{
					// cout << "in the parent process of the pipe." << endl;

					close(thePipe[0]);
					close(1);
					dup(thePipe[1]);
					close(thePipe[1]);

					execvp(PreP_argv[0], PreP_argv);
				}
				// loop back to top of JacobShell
				continue;
			}
			else //parent
			{
				int status = 0;

				wait(&status);
			}

			
		}  
		else //parent
		{
			//cout << "The child process will call execvp(" << newargv[0] << ", " << newargc << ") " << endl;

			int newstdin, newstdout;

			if((pid = fork()) == 0) 
			{	// Child
				//cout << "This is the Child process (pid = " << pid << ") " << endl;

				if(fork() == 0) //child of the child
				{
					// if we have an inbound file...
					if(inbound_file.length() > 0) 
					{ // if we have an '<', redirect input from that file;
						if(fullpath.length() > 0)
							newstdin = open(fullpath.c_str(), O_RDONLY);
						else
							newstdin = open(inbound_file.c_str(), O_RDONLY);

						close(0);
						dup(newstdin);
						close(newstdin);

						cout << "Changing stdin to " << inbound_file << endl;

						int checker = execvp(newargv[0], newargv);
						if(checker == -1)
						{
							cout << "Encountered an ERROR with '<'" << endl;
							exit(1);
						}
					}
					else
						exit(0);
				}
				else //parent of the child
				{
					// if we have an outbound file...
					if(outbound_file.length() > 0) 
					{ // if we have an '>', redirect to that file;
						if(fullpath.length() > 0)
							newstdout = open(fullpath.c_str(), O_WRONLY | O_CREAT, S_IRWXU | S_IRWXG | S_IRWXO);
						else
							newstdout = open(outbound_file.c_str(), O_WRONLY | O_CREAT, S_IRWXU | S_IRWXG | S_IRWXO);

						close(1);
						dup(newstdout); 
						close(newstdout); 

						cerr << "Changing stdout to " << outbound_file << endl;
					}
				}

				//cout << "about to call execvp(" << newargv[0] << ", " << argv << ") " << endl;
				if(check != 0 && checkAMP != 0)
					execvp(newargv[0], argv);
				else
					execvp(argv[0], argv);
			} 
			else 
			{	// Parent
				if(gotAmpersand) 
				{
					int status = 0;

					cout << "This is the parent process (pid = " << pid << ").  Got '&' Not waiting for child. " << endl;
					cout << "Child exited with status of " << status << endl;
				} 
				else 
				{
					int status = 0;
					cout << "This is the parent process (pid = " << pid << "). Waiting for child to finish" << endl;

					wait(&status);
					wait(&status);
					cout << "This is the parent process (pid = " << pid << ")" << endl;
					cout << "Child exited with status of " << status << endl;
				}
			}
		}
		//in.close();

		cout << endl; 
	}
}
#include<iostream>
#include<fstream>
#include<string>
#include<sstream>
#include<iomanip>

using namespace std;

int main(void)
{	
	cout << "***** Linux *****" << endl;

	ifstream in;
	string s;

// Print machine name
  // in.open("/proc/sys/kernel/hostname");
  // in >> s;
  // cout << "Machine name: " << s << endl;
  // in.close();
	// cout << endl;

// Model name
	// in.open("grep 'model name' /proc/cpuinfo");
	in.open("/proc/cpuinfo");
	for(int i=0; i<4; i++)
		getline(in, s);
  in >> s >> s >> s;
	getline(in, s);
  cout << "Model name: " << s << endl;
  in.close();
	cout << endl;

// Version of Linux kernel
	in.open("/proc/version");
  getline(in, s);
  cout << "Version of Linux kernel: " << s << endl;
  in.close();
	cout << endl;

// Time since last booted in form dd:hh:mm:ss
	in.open("/proc/uptime");

  double time;
	int days, hours, minutes, seconds = 0;

	in >> time;

	days = (int)time/86400; //60sec*60min*24hr = 86400sec in day
	time = (int)time%86400; 

	hours = (int)time/3600; //60sec *60sec = 3600sec in hour
	time = (int)time%3600;

	minutes = (int)time/60; //60sec in min
	time = (int)time%60;

	seconds = time;
	std::stringstream ss; //using stringstream to pad the numbers
	//basically this set 2 allocated spaces and sets them to zero originally
	//days(for ex) then overwrites the 0 space
	ss << std::setfill('0') << std::setw(2) << days;
	ss << ":";
	ss << std::setfill('0') << std::setw(2) << hours;
	ss << ":";
	ss << std::setfill('0') << std::setw(2) << minutes;
	ss << ":";
	ss << std::setfill('0') << std::setw(2) << seconds;
	
  std::cout << "Time since last booted: " << ss.str() << endl;
  in.close();
	cout << endl;

//Total CPU time in sec has been spent
// Executing in user mode
	in.open("/proc/stat");
  getline(in, s);
	string temp;
	int n = s.length();
	for (int i = 0; i < n; i++)
	{	
		if(i > 4 && i < 13)
			temp = temp + s[i]; //* 0.01;		
	}
	//cout << temp << endl;
	double result;
	result = stod(temp) * 0.01;

  cout << "CPU time spent executing in User mode: " << result << " seconds" << endl;
	result = 0; temp = "";
  in.close();
	cout << endl;

// executing in system mode
in.open("/proc/stat");
  getline(in, s);

	for (int i = 0; i < n; i++)
	{	
		if(i > 24 && i < 33)
			temp = temp + s[i]; //* 0.01;		
	}
	//cout << temp << endl;
	result = stod(temp) * 0.01;

  cout << "CPU time spent executing in System mode: " << result << " seconds" << endl;
	result = 0; temp = "";

  in.close();
	cout << endl;

// "proc/stat/cpu[4]" * 0.01
	in.open("/proc/stat");
  getline(in, s);

	for (int i = 0; i < n; i++)
	{	
		if(i > 34 && i < 43)
			temp = temp + s[i]; //* 0.01;		
	}
	//cout << temp << endl;
	result = stod(temp) * 0.01;

  cout << "CPU time spent executing in idle mode: " << result << " seconds" << endl;
	result = 0; temp = "";
  in.close();
	cout << endl;	

// TOTAL amount of memory in the virtual machine
	in.open("/proc/meminfo");
  getline(in, s);

	int l = s.length();
	for (int i = 0; i < l; i++)
	{	
		if(i > 17)
			temp = temp + s[i]; 	
	}

  cout << "TOTAL amount of memory: " << temp << endl;
	temp = "";
  in.close();
	cout << endl;
	
// AVAILABLE amount of memory in the virtual Machine
	in.open("/proc/meminfo");
	for(int i=0; i<2; i++)
		getline(in, s); //cout << s <<endl;
  getline(in, s); //cout << s <<endl;

	l = s.length();
	for (int i = 0; i < l; i++)
	{	
		if(i > 17)
			temp = temp + s[i]; 	
	}

  cout << "AVAILABLE amount of memory: " << temp << endl;
	temp = "";
  in.close();
	cout << endl;

// number of context switches performed by the kernel
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

  cout << "Number of context switches performed by the kernel: " << temp << endl;
	temp = "";
  in.close();
	cout << endl;

// number of processes created since the virtual machine was booted
	in.open("/proc/stat");
	for(int i=0; i<12; i++)
		getline(in, s);// cout << s <<endl;
  getline(in, s); //cout << s <<endl;

	l = s.length();
	for (int i = 0; i < l; i++)
	{	
		if(i > 9)
			temp = temp + s[i]; 	
	}

  cout << "Number of processes created since the virtual machine was booted: " << temp << endl;
	temp = "";
  in.close();
	cout << endl;
}
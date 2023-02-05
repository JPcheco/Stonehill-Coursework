#include<iostream>
#include<unistd.h>
#include<fstream>
#include<string>

using namespace std;

int main(int argc, char* argv[])
{	
	cout << "***** Linux Basics and /proc *****" << endl;

	ifstream in;
	string s;

	int interval = atoi(argv[2]);
	int duration = atoi(argv[3]);
	int ticker = 2;

	double final_avg_accross_all_samples = 0;
	double sum = 0;
	double division_number = duration / interval;

	// Compute load average with periodic samples
	int iteration=0;
	while(iteration < duration)
	{
		sleep(interval);
		//sampleLoadAverage();
		in.open("/proc/loadavg");
		in >> s;
		cout << "After " << ticker << " seconds, load average is " << s << "." << endl;
		in.close();

		iteration = iteration + interval;
		ticker = ticker + 2;

		sum = sum + stod(s);
	}

	final_avg_accross_all_samples = sum / division_number;

	cout << "Compution of final load average across all samples is: " << final_avg_accross_all_samples << endl;
	return 0;
}
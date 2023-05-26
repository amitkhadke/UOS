#include<stdio.h>
#include<stdlib.h>
#include<fcntl.h>
#include<unistd.h>

char buffer [3000];

void filefunc(int old, int new)

{
	int count;
	while ((count=read(old, buffer, sizeof(buffer)))>0)
		{
			write(new, buffer, count);
		}
}

int main (int argc, char*argv[])
{
	int fsource, fdestination;
	if (argc!=3)
	{
		printf("Enter source file name and destination file name:");
		exit(1);
	}
	
	fsource=open(argv[1], O_RDONLY);
	if(fsource==-1)
		{
			printf("Write something in source file");
		}
	
	fdestination=creat(argv[2], O_RDWR);
	if(fdestination==-1)
		{
			printf("File isn't created");
		}
	
	filefunc(fsource, fdestination);
	exit(1);
}

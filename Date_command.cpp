#include <iostream>
#include <stdio.h>
#include <stdlib.h>
using namespace std;

// function to set date and time
void setDateTime(int date, int month, int year, int hour, int min)
{
 
    unsigned char buff[32] = { 0 };
    sprintf((char*)buff, (const char*)"date -s \"%02d/%02d/%02d %02d:%02d\"", month, date, year, hour, min);
    system((const char*)buff);
}


int main()
{
    int date,month,year,hour,min;
    cin>>date>>month>>year>>hour>>min;
    setDateTime(date,month,year,hour,min);
    cout << endl;
    
    return 0;
}

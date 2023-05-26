//Name : Amit Khadke
//Roll no.: A42
//PRN : 2021000032

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <string.h>

int main()
{
    char path[100];
    struct stat buf;
    char *pch;

    printf("Enter path : ");
    fgets(path, 100, stdin);

    pch = strchr(path, '\n');
    if (pch != NULL) {
        *pch = '\0';
    }
    else {
        printf("Invalid input\n");
        exit(1);
    }

    char *token = strtok(path, "/");
    while (token != NULL) {
        if (stat(token, &buf) == -1) {
            printf("Failed to stat %s\n", token);
            exit(1);
        }
        printf("Inode number of %s is %lu\n", token, buf.st_ino);
        token = strtok(NULL, "/");
    }

    return 0;
}

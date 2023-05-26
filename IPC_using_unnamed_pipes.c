#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    int fd[2];
    pid_t pid;

    if (argc != 3) 
    {
        fprintf(stderr, "Usage: %s <command 1> <command 2>\n", argv[0]);
        exit(1);
    }

    if (pipe(fd) == -1) 
    {
        perror("pipe");
        exit(1);
    }

    pid = fork();

    if (pid == -1) 
    {
        perror("fork");
        exit(1);
    }

    if (pid == 0) 
    {
        close(fd[1]); 
        dup2(fd[0], STDIN_FILENO);
        close(fd[0]);
        system(argv[2]);
    } 
    else 
    {
        close(fd[0]); 
        dup2(fd[1], STDOUT_FILENO); 
        close(fd[1]); 
        system(argv[1]); 
        wait(NULL);
    }

    return 0;
}

#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

void handle_sigfpe(int sig) {
    printf("Signal triggered\n");
    exit(1);
}

int main() {
    pid_t pid = fork();
    
    if (pid < 0) {
        perror("Failed to create child process");
        exit(1);
    }
    else if (pid == 0) {
        
        signal(SIGFPE, handle_sigfpe);
        int result = 1/0;
        exit(0);
    }
    else {
        
        printf("Created child process with PID %d\n", pid);
        signal(SIGCHLD, SIG_IGN);  
       
            
        sleep(1);
        
    }
    
    return 0;
}

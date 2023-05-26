#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
    int pid, status;
    int num1, num2, sum, product;

    // get input from user
    printf("Enter two numbers: ");
    scanf("%d %d", &num1, &num2);

    // fork a child process
    pid = fork();

    if (pid < 0) {
        // fork failed
        printf("Error: Fork failed.\n");
        exit(1);
    } else if (pid == 0) {
        // child process
        printf("Child process with PID %d started.\n", getpid());
        sum = num1 + num2;
        printf("Child process calculated sum: %d\n", sum);
        exit(0);
    } else {
        // parent process
        printf("Parent process with PID %d started.\n", getpid());
        printf("Parent process is waiting for child process to finish...\n");
        wait(&status);
        printf("Parent process received sum: %d\n", sum);
        product = num1 * num2;
        printf("Parent process calculated product: %d\n", product);
        printf("Parent process finished.\n");
    }

    return 0;
}

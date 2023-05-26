#!/bin/bash

n=$1
a=0
b=1

echo "Fibonacci series up to $n:"

while [ $a -le $n ]
do
    echo -n "$a "

    # update variables
    temp=$b
    b=$((a + b))
    a=$temp
done

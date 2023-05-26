#!/bin/bash

echo "File Information in the current directory:"

for file in *
do
    if [ -f "$file" ]
    then
        echo "Name: $file"
        echo "Size: $(du -h "$file" | awk '{print $1}')"
        echo "Owner: $(ls -l "$file" | awk '{print $3}')"
        echo "Permissions: $(ls -l "$file" | awk '{print $1}')"
        echo "Last modified: $(date -r "$file")"
        echo "------------------"
    fi
done

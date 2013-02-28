#!/bin/bash

path="$1"

if [ ! -e "$path" ]; then
    echo "renamehash: path $path does not exist"
    exit 1
fi

if [ ! -f "$path" ]; then
    echo "renamehash: path $path is not a file"
    exit 1
fi

directory=`dirname "$path"`
file=`basename "$path"`
extension="${file##*.}"
sum=`sha1sum -b "$path" | cut -d " " -f 1`
newpath="$directory/$sum"

if [[ "$file" == *"."* ]]; then newpath="$newpath.$extension"; fi
if [ "$path" == "$newpath" ]; then exit; fi
if [ -e "$newpath" ]; then echo "renamehash: file $newpath exists"; fi
mv "$path" "$newpath"

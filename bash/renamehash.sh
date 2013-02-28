#!/bin/bash

function renamehash
{
    path="$1"
    directory=`dirname "$path"`
    file=`basename "$path"`
    extension="${file##*.}"
    sum=`sha1sum -b "$path" | cut -d " " -f 1`
    newpath="$directory/$sum"

    if [[ "$file" == *"."* ]]; then newpath="$newpath.$extension"; fi
    if [ "$path" == "$newpath" ]; then exit; fi
    if [ -e "$newpath" ]; then echo "renamehash: file $newpath exists"; fi
    mv "$path" "$newpath"
}

if [ ! -e "$1" ]
then
    echo "renamehash: path $1 does not exist"
    exit 1
fi

if [ -f "$1" ]
then
    renamehash "$1"
elif [ -d "$1" ]
then
    find "$1" -type f -not -name .DS_Store | while read f
    do
        renamehash "$f"
    done
else
    echo "renamehash: path $1 is not a file or directory"
    exit 1
fi

#!/bin/bash

path="$1"

if [ ! -e "$path" ]; then
    echo "renamehashes: path $path does not exist"
    exit 1
fi

if [ ! -d "$path" ]; then
    echo "renamehashes: path $path is not a directory"
    exit 1
fi

find "$path" -type f -not -name .DS_Store | while read f; do ./renamehash.sh "$f"; done

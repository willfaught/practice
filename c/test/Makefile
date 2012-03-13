cc = gcc
flags = -ansi -pedantic -Wall -g
build = $(cc) $(flags)

main: main.o test.o
	$(build) -o test main.o test.o

main.o: main.c
	$(build) -c main.c

test.o: test.c test.h
	$(build) -c test.c	

clean:
	rm -f *.o test

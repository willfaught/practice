cc = gcc
flags = -ansi -pedantic -Wall -g
libraries = -framework OpenGL -framework Glut

main: main.o texture.o vector.o
	$(cc) $(flags) $(libraries) -o game main.o texture.o vector.o

main.o: main.c
	$(cc) $(flags) -c main.c

texture.o: texture.c
	$(cc) $(flags) -c texture.c

vector.o: vector.c vector.h
	$(cc) $(flags) -c vector.c

clean:
	rm -f *.o game
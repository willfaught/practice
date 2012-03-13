main: wf_al.o wf_lls.o wf_lld.o

wf_al.o: wf_al.c wf_al.h
	gcc -c wf_al.c

wf_lls.o: wf_lls.c wf_lls.h
	gcc -c wf_lls.c

wf_lld.o: wf_lld.c wf_lld.h
	gcc -c wf_lld.c

clean:
	rm -f *.o

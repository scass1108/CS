all: submission

submission: hw2.o
	g++ hw2.o -o submission

hw1.o: hw2.cpp
	g++ -c hw2.cpp

clean:
	rm -f *.o submission

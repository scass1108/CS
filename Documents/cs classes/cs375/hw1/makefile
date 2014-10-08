all: submission

submission: hw1.o
	g++ hw1.o -o submission

hw1.o: hw1.cpp
	g++ -c hw1.cpp

clean:
	rm -f *.o submission

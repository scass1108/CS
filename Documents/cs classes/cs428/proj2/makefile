all: buyer seller

seller: server.o
	g++ server.cpp -o seller -lsocket -lnsl

buyer: client.o
	g++ client.cpp -o buyer -lsocket -lnsl

clean:
	rm -f *.o buyer seller
	

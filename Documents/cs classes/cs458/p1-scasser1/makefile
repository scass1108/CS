all: ftpserv ftpcli

ftpserv: server.o
	g++ server.cpp -o ftpserv -lsocket -lnsl

ftpcli: client.o
	g++ client.cpp -o ftpcli -lsocket -lnsl

clean:
	rm -f *.o ftpserv ftpcli
	

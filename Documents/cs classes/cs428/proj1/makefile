all: tcp udp

tcp: tcpcli tcpserv

tcpcli: tcpcli.cpp
	g++ -o tcpcli tcpcli.cpp -lnsl 
tcpserv: tcpserv.cpp
	g++ -o tcpserv tcpserv.cpp -lnsl 
	
udp: udpcli udpserv

udpcli: udpcli.cpp
	g++ -o udpcli udpcli.cpp -lnsl 

udpserv: udpserv.cpp
	g++ -o udpserv udpserv.cpp -lnsl 
	
clean:
	rm -f select tcpcli tcpserv udpcli udpserv

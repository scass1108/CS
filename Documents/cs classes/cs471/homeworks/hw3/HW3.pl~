/*
      HW3.pl
      Author: Casserly, Shawn
      Date:2-18-2014
 */

/* Question 1 */
	
	numeral(0).
	numeral(succ(X)) :- numeral(X).

	convertToDecimal(0, 0).
	convertToDecimal(succ(S), N) :- convertToDecimal(S, N1), N is N1 + 1.

/*Question 2 */
	
	add(0,0,0).
	add(X, Y, S) :- convertToDecimal(X,X1), convertToDecimal(Y,Y1), S1 is X1 + Y1, convertToDecimal(S, S1).
 

/*Question 3 */

	minus(0,0,0).
	minus(X,Y,S) :- convertToDecimal(X,X1), convertToDecimal(Y,Y1), S1 is X1 - Y1, convertToDecimal(S,S1).

/*Question 4 */

	size([],0).
	size([H|T],N):- size(T,N1), N is N1 + 1. 

	sameLen([],[]).
	sameLen(L1,L2) :- size(L1, A), size(L2, B), A = B.


/*Question 5 */

	hasSubseq([],[]).
	hasSubseq([_|X], Y) :- hasSubseq(X,Y).
	hasSubseq([A|X], [B|Y]):- A = B, hasSubseq(X,Y).

/*Question 6 */
	
	prefix([],_).
	prefix([H|Y], [H|T]) :- prefix(Y,T).
	
/*Question 7 */

origin(P):- P = point(0,0).


/*Question 8 */

inside(P,R) :-  P = point(X,Y),  X^2 + Y^2 < R^2. 

/*Question 9 */

init([_], []).
init([A|X],[A|Y]) :- init(X,Y).

/*Question 10 */

zip(_,[],[]).
zip([],_,[]).
zip([A|X],[B|Y], [(A,B)|Z]) :- zip(X,Y,Z).

/*Question 11*/

gcd(A,0,A).
gcd(0,B,B).
gcd(A,B,R):- R1 is mod(A,B), gcd(B,R1,R).

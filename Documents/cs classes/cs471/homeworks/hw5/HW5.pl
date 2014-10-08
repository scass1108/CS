/*
      HW5.pl
      Author: Casserly, Shawn
      Date:3-4-2014
 */

/* Question 1 */

eval(expTree(lit, V), V).
eval(expTree(O, X), V) :- eval(X, A), U =.. [O, A], V is U.
eval(expTree(O, X, Y), V) :- eval(X, A), eval(Y, B), U =.. [O, A, B], V is U.


eTree1(expTree('+',
	      expTree(lit, 5),
	      expTree('*',
	           expTree(lit, 3),
	           expTree(lit, 2)
	       )
       )
 ).


eTree2(expTree('*',
	expTree('-',
	     expTree(lit, -3),
	     expTree(lit, 2)
	 ),

	expTree('+',
	      expTree(lit, 3),
	      expTree('-',
		     expTree(lit, 2)
		   )
	   )
	 )
 ).

eTree3(expTree('*',
	expTree('min',
	     expTree(lit, -3),
	     expTree(lit, 2)
	 ),

	expTree('+',
	     expTree(lit, 3),
	     expTree('-',
		    expTree(lit, 2)
		    )
	     )
	 )
 ).


eTree4(expTree(float,
	   expTree('sin',
	        expTree('/',
		       expTree(lit, pi),
		       expTree(lit, 2)
		     )
	    )
	   )
 ).



/* Question 2 */

d(x,x,1).
d(C,x,0):-number(C).
d(C*x,x,C):-number(C).
d(-U, X, -DU) :- d(U, X, DU).
d( U + V, x, RU + RV ):-d(U,x,RU), d(V,x,RV).
d( U - V, x, RU - RV ):-d(U,x,RU), d(V,x,RV).
d(U * V,x, U * DV + V * DU):- d(U,x,DU), d(V,x,DV).
d(U ^ N, x, N*U ^ N1*DU) :- integer(N), N1 is N-1, d(U, x, DU).


 evaluate(R, V, Xval) :- member(x:V, Xval). 
/* doesnt work */

/* Question 3 */

numOfAtomsNo([], 0).
numOfAtomsNo(L, 1) :- atom(L).
numOfAtomsNo(L, 1) :- number(L).
numOfAtomsNo([H|T], N) :- numOfAtomsNo(H, X), numOfAtomsNo(T, Y), N is X + Y. 




/* Question 4 */
coin(dollar, 100).
coin(half, 50).
coin(quarter, 25).
coin(dime,10).
coin(nickel,5).
coin(penny,1).


change(0,[]).
change([(Name,Num)|T]):- coin(Name, Val), A >= Val, Num is floor(A / Val), B is A mod Val, change(B,T).

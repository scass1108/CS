Shawn Casserly
Lab 9 
CS 471
5/8/2014

Part B: What do we do with the ??? ?

      Type (Do NOT cut and paste) the following non-recursive definition in a Haskell file and load into hugs/ghci:

> hLen :: (Num u, Eq t) => ([t] -> u) -> [t] -> u 
> hLen = (\f x -> if x == [] then 0 else 1 + (f (tail x)))
> myLength ls = if ls == [] then 0 else 1 + myLength (tail ls)

1.    [10pts] Look at hLen --
      a)  Why is hLen recursive?  Why or why not.
        
#		No it is not recursive, hLen calls the function input on the tail of a list 
		and adds 1 to what it returns 
        
        
      b)  Why is hLen a HOF (higher order function)? Why or why not?

#		No, it just calls a function on the tail of a list and adds 1 to whatever the 			function returns



      c)  What is the value of
      c1)  hLen sum [4,5,6] ?
		
#		12	

      c2)  hLen head [4,5,6] ?
        
#		6
        

    Does hLen have anything to do with sum or head?
    		
#    		No it just operates on the tail of the list passed in unless the list is empty
	


2.    [2pts]What is the value of

        hLen myLength [4,5,6] ?
        
#		3

    [2pts]What is the relationship  between hLen and myLength?

#		hLen is a nonrecursive function and myLength is a recursive functions	


Part C: Factorial
Add the following definition of factorial to your Haskell file and reload into hugs/ghci.

> factorial :: Integral a => a -> a
> factorial n = if n ==0 then 1 else n * (factorial (n - 1))

    [10pts]Define hFact to be a lambda abstraction such that it takes a function as an argument, and returns another function, similar to hLen.  Write this so that hFact factorial = factorial. What is the type of hFact?  (Follow the model of hLen)


> hFact = (\f x -> if x == 0 then 1 else x * (f (x-1)))


    [2pts]Apply hFact to ( ^ 2) -- What is the value of hFact (^ 2) 4?

    [2pts]What is the value of hFact factorial 5? Is it the same value as factorial 5?

Part D: General definition

    Here hFact factorial is factorial, i.e. the factorial function is the “smallest” fixed point of hFact

    In general, to give meaning to the recursive function

    f = (\ x. if (cond(x)) then val(x) else expr(f, x))

    (which cannot be expressed in lambda-notation), we define

    Hf =(\ F -> \ x -> if (cond(x)) then val(x) else expr(F, x))

  Fix ( Haskell's version of the fixed-point combinator)

    [2pts] Add the following definition of factorial' to your haskell file:


> factorial' = hFact factorial'


    Remember that if x = f x then x is the fix point of f  so hFact factorial' equals factorial'

        What is the type of factorial' ?


    [10pts]Now we can define our fix point operator ( Haskells equivalent Y combinator)

> fix f = f (fix f )

        What is the type of fix?

        What is the difference between the code below?


> fix f = f (fix f )


            and


# fix f = f fix f 



    [10pts]Combining all we have done -- What is the value of
        factorial 6   (definition given in part C)
        hFact factorial 6 ( you defined in part C #2 )
        factorial' 6 (definition given in part D #1)
        fix hFact 6 (definition given in part D)

	Hope these examples pique your curiousity

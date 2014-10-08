> module Lab1
>    where

# Define factorial.  Let Haskell infer the type of factorial.

> factorial n = if n == 0 then 1 else n * factorial (n - 1)
> fact1 :: Int -> Int
> fact1 n = if n == 0 then 1 else n * fact1 (n - 1)
> fact2 :: Integer -> Integer
> fact2 n = if n == 0 then 1 else n * fact2 (n - 1)
> factP :: Integer -> Integer
> factP 0 = 1
> factP n = n * factP(n -1)
> factG x
>     | x < 0     = error "neg x"
>     | x == 0    = 1
>     | otherwise = x*factG(x-1)
> factG2 :: Integer -> Integer
> factG2 n
>     | n < 0     = error "neg n"
>     | n == 0    = 1
>     | otherwise = n * factG2 (n - 1)
> factE :: Integer -> Integer
> factE n
>     | n < 0     = error "neg n"
>     | n == 0    = 1
>     | otherwise = n * factE n - 1

# Tuple Data Type

> prodT (a,b,c) = a * b * c

# Curried functions

> prodC a b c = a * b * c
> prodCx :: Num a => a -> (a -> (a->a))
> prodCx a b c = a * b * c 
> prodC1  = prodC 1
> prodC2  = prodC1 2
> prodC3  = prodC2 3
> prodC12 = prodC 1 2

# h and h2

> f x = x * 5 + 3
> g x = (3 * x - 1) * 2
> h = f . g
> h2 = g . f


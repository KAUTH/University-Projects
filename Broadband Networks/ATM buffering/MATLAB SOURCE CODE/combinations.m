function c = combinations(n,r)
% function that calculates all the possible combinations
    c = (factorial(n)) / (factorial(n-r)*factorial(r));
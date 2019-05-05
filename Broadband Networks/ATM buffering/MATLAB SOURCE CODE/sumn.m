function s = sumn (n,N,p,q_n)
% prints the sum for qn
init = 0;
    for k = 3:n+1
        new = a_var(N,k-1,p)/a_var(N,0,p)*q_n(n+2-k);
        init = init + new;
    end
s = init;
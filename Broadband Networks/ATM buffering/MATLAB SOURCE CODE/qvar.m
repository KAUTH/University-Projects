function q = qvar(b,p,N)
% calculates q0, which is the probability of Q = 0 (the cells in the
% examined queue at the end of the mth slot = 0)
q_n = zeros(1, b+1); % preallocating memory,  the code runs much faster because there is 
                   % no need to repeatedly reallocate memory for the growing data structure
q_n(1) = 1;
q_n(2) = (1 - a_var(N,0,p) - a_var(N,1,p)) / a_var(N,0,p); % q_n[1]: q1 divided by q0 
    for n = 2:b+1
        q_n(n+1) = (1 - a_var(N,1,p))/(a_var(N,0,p))*q_n(n) - sumn(n,N,p,q_n);
    end
q = 1 / (1 + sumq(b,q_n));

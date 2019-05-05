function s = sumnq (b,p)
% prints the sum for mean(Q)
q_n = zeros(1, b+1); % preallocating memory,  the code runs much faster because there is 
                     % no need to repeatedly reallocate memory for the growing data structure
q_n(1) = 1;
q_n(2) = (1 - a_var(inf,0,p) - a_var(inf,1,p)) / a_var(inf,0,p); % q_n[1]: q1 divided by q0 
    for n = 2:b+1
        q_n(n+1) = (1 - a_var(inf,1,p))/(a_var(inf,0,p))*q_n(n) - sumn(n,inf,p,q_n);
    end
q = 1 / (1 + sumq(b,q_n));
q_n = q_n * q;
init = 0;
    for k = 2:b+1
        new = q_n(k);
        init = init + (k-1)*new;
    end
s = init;


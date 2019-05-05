function r = rvar(b,p,N)
% calculates the switch throughput r
    r = 1 - qvar(b,p,N)*a_var(N,0,p);

function s = sumq (b,q_n)
% prints the sum for q0
init = 0;
    for k = 2:b+1
        new = q_n(k);
        init = init + new;
    end
s = init;
function w = waiting(b,p)
    % calculates the mean waiting time for infinite N
    if b == inf
        w = p ./ (2*(1-p));
    else
        w = sumnq(b,p) ./ rvar(b,p,inf);
    end
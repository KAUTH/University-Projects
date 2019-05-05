function p = cell_loss(b,p,N)
% calculates the cell loss probability
    p = 1 - rvar(b,p,N)/p;
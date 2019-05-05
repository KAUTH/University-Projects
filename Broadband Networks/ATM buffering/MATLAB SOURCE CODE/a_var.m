function ak = a_var(N,k,p)
% calculates the number of cell arrivals destined for the tagged output in
% a given time slot
    if N == inf
        ak = (p.^k .* exp(-p)) ./ (factorial(k));
    elseif k > N
        ak = 0;
    else
        ak = combinations(N,k) * (p/N)^k * (1 - p/N)^(N-k);
    end
        
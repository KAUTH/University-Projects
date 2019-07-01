function [y, flop] = rec_fast_fourier(x)
% A simple recursive implementation of the Fast Fourier Transform
% 
% input: 
%   x: The input signal
% output:
%   y: The transformed signal
%   flop: The flops of the computation
%
% author: Nikos Sismanis
% date: Apr 2914

n = length(x);
ee = 0:2:n-1; % even indices
oo = 1:2:n; % odd indices

% twiddle factors
w = exp(-2 * pi * 1i / n * [0:ceil(n/2)-1]');


if (n==1)
    y = x;
    flop = 0;
else
    [top flop_t] = rec_fast_fourier(x(ee+1));
    [bot flop_b] = rec_fast_fourier(x(oo+1));
    z = w .* bot;
    y = [top + z, top - z];
    flop = 2*n + 6*n/2 + flop_t + flop_b; 
    y = y(:);
end
end
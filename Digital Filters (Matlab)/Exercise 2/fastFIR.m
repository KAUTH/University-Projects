function y = fastFIR( u, w )
% FASTFIR - FFT-based fast implementation of FIR filter

%% INITIALIZATIONS
n = length(u);
m = length(w);

y = 0;

%% MAIN FUNCTION
y_init = ifft(fft(u,n).*fft(w,n));
y = y_init(m:n);

end
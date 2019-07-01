function [y, e, wo, wt] = noiseCancellation( u, d, m, mu, ws )
% NOISECANCELLATION - Implement noise cancellation for exercise 1
%   
% DESCRIPTION
%
%   [Y,E,WO,WT] = NOISECANCELLATION( U,D,M,MU,WS ) performs steepest
%   descent to locate the optimal Wiener coefficients.
%
% INPUT
%
%   U   Input signal u(n)               [n-by-1]
%   D   Desired signal d(n)             [n-by-1]
%   M   Taps (number of Wiener coeff)   [scalar]
%   MU  Steepest descent step           [scalar]
%   WS  Starting value for adaptation   [m-by-1]
%
% OUTPUT
%
%   Y   Output signal e(n)              [n-by-1]
%   E   Error signal d(n) - y(n)        [n-by-1]
%   WO  Optimal Wiener coefficients     [m-by-1]
%   WT  Coefficients at each step       [m-by-n]
%
  
  n = length(u);
  
  % TODO: implement function
  
  % make sure inputs are column vectors
  u = u(:);
  d = d(:);
  
  % generate Toeplitz matrix
  U = toeplitz(u, [u(1) zeros(1,m-1)]);
  
  %[q,r] = size(d);
  %fprintf('\n *** Dimensions are: %d %d ***\n\n',q,r);
  
  % compute auto-correlation and cross-correlation
  R = U'*U / n;
  p = U'*d / n;
  
  y  = zeros( n, 1 );
  e  = zeros( n, 1 );
  wo = zeros( m, 1 );
  wt = zeros( m, n );
  
  wo = R \ p; % Wiener solution
  
  wt(:,1) = ws;
  
  s = u;
  for i=m:n
    ws = ws + mu*(p-R*ws); % Adaptation steps
    wt(:,i) = ws;
    y(i) = s(i:-1:i-(m-1))' * ws; % filter
  end
  
  e = d-y;
  
end

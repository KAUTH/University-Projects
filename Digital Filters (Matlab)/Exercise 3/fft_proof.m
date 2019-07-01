function [yt, yb] = step1( x, w )
% split output to top and bottom
  
  n = length(x);
  
  k = (0:n-1)';
  
  yb = zeros(n/2,1);
  yt = zeros(n/2,1);
  
  for j = 0:n/2-1
    yt(j +1) = sum(w(n,j*k) .* x(k +1));
  end
  for j = 0:n/2-1
    yb(j +1) = sum(w(n,(j+n/2)*k) .* x(k +1));
  end
  
end

function [yte, yto, ybe, ybo] = step2( x, w )
% split each output to even and odd
  
  n = length(x);
  
  k = (0:n/2-1)';
  
  ybe = zeros(n/2,1);
  ybo = zeros(n/2,1);
  yte = zeros(n/2,1);
  yto = zeros(n/2,1);

  for j = 0:n/2-1
    ybe(j +1) = sum(w(n,j*2*k) .* x(2*k +1));
    ybo(j +1) = sum(w(n,j*(2*k+1)) .* x(2*k+1 +1));
  end
  for j = 0:n/2-1
    yte(j +1) = sum(w(n,(n/2+j)*2*k) .* x(2*k +1));
    yto(j +1) = sum(w(n,(n/2+j)*(2*k+1)) .* x(2*k+1 +1));
  end
  
end


function [xk1, xk2] = step3( x, w )
% apply w identity and finish the proof with
% y = [ xk1+xk2; xk1-xk2 ];
  
  n = length(x);
  
  xk1 = zeros(n/2,1);
  xk2 = zeros(n/2,1);

  k = (0:n/2-1)';
  for j = 0:n/2-1
    xk1(j+1) = sum(w(n/2,j*k) .* x(2*k +1));
    xk2(j+1) = w(n, j) .* sum(w(n/2,j*k) .* x(2*k+1 +1));
    
  end
  
end
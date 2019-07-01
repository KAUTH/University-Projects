function [R,p,w_o] = wiener( a, b, c, sigma_v1, sigma_v2 )
 
    a = [1; a(:)];

    A = [a(1) a(2) a(3) a(4); a(2) a(1)+a(3) a(4) 0; a(3) a(2)+a(4) a(1) 0; a(4) a(3) a(2) a(1)];
    
    B = [sigma_v1; 0; 0; 0];
    
    r = A \ B;
    R = toeplitz(r);
    
    p = R * (-b');
    
    w_o = R \ p;
  
end


function [y,w_out] = lms(u, d, m, mu)
% LMS - Least Mean Square adaptation
%   
 
  persistent w
  persistent u_stream
  
  
  % initialize 
  if isempty(w)
    w = zeros( m, 1 );
    u_stream = zeros(m,1);
  end
  
  u_stream(end+1, 1) = u;
                           
    
    % compute output
    y = w' * u_stream(end:-1:end-m+1);
    
    % get error
    e = d - y;
    
                     
    
      % update weights
      w = w + mu * e * u_stream( end:-1:end-m+1 );

  

  if nargout > 1
    w_out = w;
  end
  
  
end


function [y,w_out] = nlms(u, d, m, mu, alpha)
% LMS - Least Mean Square adaptation
%   
  
  persistent w
  persistent u_stream
  
  
  % initialize 
  if isempty(w)
    w = zeros( m, 1 );
    u_stream = zeros(m,1);
  end
  
  u_stream(end+1, 1) = u;

  
                        
    
    % compute output
    y = w' * u_stream(end:-1:end-m+1);
    
    % get error
    e = d - y;

      % update weights
      w = w + mu * e * u_stream( end:-1:end-m+1 )/ (alpha + u_stream( end:-1:end-m+1 )' * u_stream( end:-1:end-m+1 ));
      

    


  if nargout > 1
    w_out = w;
  end
 
  
end



function [y,w_out] = rls(u, d, m, lambda, delta)
% LMS - RLS adaptation
%   
    
  persistent w
  persistent u_stream
  persistent P
  
  
  % initialize 
  if isempty(w)
    w = zeros( m, 1 );
    u_stream = zeros(m,1);
  P = (1 / delta) * eye(m, m);
      
  end
  
  u_stream(end+1, 1) = u;

                         
    
    % compute output
    y = w' * u_stream(end:-1:end-m+1);
    
    % get error
    e = d - y;
    
 
      %compute k
      k = ((lambda^-1) * P * u_stream( end:-1:end-m+1 ) / (1 + (lambda^-1) * u_stream( end:-1:end-m+1 )'* P * u_stream( end:-1:end-m+1 )));

                               
                                 
      % update weights
      w = w + k * e;
      
      %compute P
      P = (lambda^-1) * P - (lambda^-1) * k * u_stream( end:-1:end-m+1 )' * P;
      


  if nargout > 1
    w_out = w;
  end
 
  
end
function [y,w_out] = leastMeanSquare1(u, d, m, mu)
% LEASTMEANSQUARE - Online LMS implementation
%   
% SYNTAX
%
%   Y = LEASTMEANSQUARE( U, E, M, MU )
%   Y = LEASTMEANSQUARE( U, E, M, MU, D )
%   [Y,W] = LEASTMEANSQUARE( ... )
%
% INPUT
%
%   U   Input signal value              [scalar]
%   E   Error signal value              [scalar]
%   M   Tap length                      [scalar]
%   MU  Convergence rate                [scalar]
% 
% OPTIONAL
% 
%   D   Delay (for w update)            [scalar]
%
% OUTPUT
%
%   Y   Output value of LMS             [scalar]
%   W   Weights (after update)          [m-vector]
%
% DESCRIPTION
%
%   LEASTMEANSQUARE computes one output of LMS algorithm
%
% DEPENDENCIES
%
%   <none>
%
%

  
  %% PERSISTENT VaLUES ACROSS CALLS
  
  persistent w
  persistent u_stream
  

  %% INITIALIZATIONS
  % initialize w
  if isempty(w)
    w = zeros( m, 1 );
  end
  
  u_stream(end+1, 1) = u;
  
  %Mu = length(mu); % my addition
  
  %% CHECK INPUTS (DELTA)
  
  if ~exist( 'delta', 'var' ) || isempty( delta )

    % no delay
    delta = 0;
    
  end
  
  
  %% COMPUTE NEW VALUE & UPDATE WEIGHTS
  %for i = 1:Mu
  if length( u_stream ) < m             % ----- stream not long enough
    
    y = u;
    
  else                                  % ----- calculate y
    
        % compute output
        y = w' * u_stream(end:-1:end-m+1);
    
        if length( u_stream ) > m + delta   % --- stream long enough for
                                        %     weight update
        e = d-y;
        % update weights
        w = w + mu * e * u_stream( (end:-1:end-m+1) - delta);
      
        end  
    
  end
  
  %end % for (i)
 
  %% UPDATE OUTPUT VALUES

  if nargout > 1
    w_out = w;
  end
  
  
end


function [errmat, OA, UA, PA, Khat] = evaluate_class(ypred, ytrue)
  
  % make them column vectors
  ypred = ypred(:);
  ytrue = ytrue(:);

  labels = unique(ytrue);
  k = length(labels);
  
  errmat = zeros(k,k);
 
  for i = 1:k
    for j = 1:k
      errmat(i,j) = sum( ypred == labels(i) & ytrue == labels(j) );
    end
  end
  
  % number of samples
  N = length(ytrue);
  
  % overall accuracy , scalar
  OA = trace(errmat)/N;
  
  % user's accuracy
  UA = diag(errmat)./sum(errmat,1)';
  
  % producer's accuracy
  PA = diag(errmat)./sum(errmat,2);
  
  % k-hat metric
  xir = sum(errmat,1)';
  xic = sum(errmat,2);
  Khat = ( N*trace(errmat) - sum(xir.*xic) ) / (N^2 -  sum(xir.*xic) );
  
  
end
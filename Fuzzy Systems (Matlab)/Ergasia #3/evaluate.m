function [mse, rmse, rsq, nmse, ndei] = evaluate(ypred, ytrue)
  %% Function that calculates and returns the following performance...
  ...metrics: MSE, RMSE, R^2, NMSE, NDEI

  % Mean Square Error 
  mse = sum( (ypred-ytrue).^2 )/size(ypred,1);

  % Root Mean Square Error
  rmse = sqrt(mse);
  
  % R^2
  ss_res = sum( (ytrue-ypred).^2 );
  mean_ytrue = sum(ytrue)/size(ytrue,1);
  ss_tot = sum( (ytrue - mean_ytrue).^2 );
  rsq = 1 - ss_res/ss_tot;
  
  % Normalized Mean Square Error
  nmse = 1 - rsq;
  
  % Non-Dimensional Error Index
  ndei = sqrt(nmse);
  
end

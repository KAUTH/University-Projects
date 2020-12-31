function [data_sampled, data_res] = preprocess_samples(data, sample_num)
  %% Function that implements some basic preprocessing of the given data...
  ...and returns the sampled data and the residual non-sampled data.

  [data_sampled, index_sampled] = datasample(data, sample_num,'Replace',...
      false);
  
  index_res = ones(1,size(data,1)); 
  index_res(index_sampled) = 0;
  
  data_res = data(logical(index_res),:);
  
  for i = 1:size(data,2)
    max_val = max(data(:,i));
    range = 1: size(data_sampled,1);
    indices_samp = range(data_sampled(:,i)==max_val);
    range = 1: size(data_res,1);
    indices_res = range(data_res(:,i)==max_val);

    % swap a random sample from the set lacking a max value with a max
    % value from the other set
    if size(indices_samp,1)==0 % if the set lacking the max value is the...
        ...sampled
      rand_idx = ceil( rand*size(data_sampled,1) );
      temp = data_sampled(rand_idx,:);
      data_sampled(rand_idx,:) = data_res(indices_res(1),:);
      data_res(indices_res(1),:) = temp;
    
    elseif size(indices_res,1)==0 % if the set lacking the max value is...
        ...the residual
      rand_idx = ceil( rand*size(data_res,1) );
      temp = data_res(rand_idx,:);
      data_res(rand_idx,:) = data_sampled(indices_samp(1),:);
      data_sampled(indices_samp(1),:) = temp;
    end
    
  end
  
  for i = 1:size(data,2)
   min_val = min(data(:,i));
   range = 1: size(data_sampled,1);
   indices_samp = range(data_sampled(:,i)==min_val);
   range = 1: size(data_res,1);
   indices_res = range(data_res(:,i)==min_val);
    
    % swap a random sample from the set lacking a min value with a min
    % value from the other set
    if size(indices_samp,1)==0 % if the set lacking the min value is the...
        ...sampled
      rand_idx = ceil( rand*size(data_sampled,1) );
      temp = data_sampled(rand_idx,:);
      data_sampled(rand_idx,:) = data_res(indices_res(1),:);
      data_res(indices_res(1),:) = temp;
    
    elseif size(indices_res,1)==0 % if the set lacking the min value is...
        ...the residual
      rand_idx = ceil( rand*size(data_res,1) );
      temp = data_res(rand_idx,:);
      data_res(rand_idx,:) = data_sampled(indices_samp(1),:);
      data_sampled(indices_samp(1),:) = temp;
    end

  end
  
end
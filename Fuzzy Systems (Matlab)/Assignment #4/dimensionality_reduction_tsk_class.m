%% PART 2 - TSK classification High Dimensionality Dataset

% Dataset: This dataset is a pre-processed and re-structured/reshaped...
...version of a very commonly used dataset featuring epileptic...
...seizure data.
% Link: ...
...https://archive.ics.uci.edu/ml/datasets/Epileptic+Seizure+Recognition

clear
close all;

%% Data preprocessing 

% load Epileptic Seizure Recognition dataset
data = csvread('data.csv',1,1);

[n_s, n_f] = size(data);

% find all the distinct labels of the output feature 
labels = unique(data(:,n_f));

% output feature vector
out = data(:, n_f);

% initialize the three sets
data_tr=[];
data_val=[];
data_test=[];

clust_radius = [0.2 0.7];
epoch = 5;

data_tr_per_label={};
data_val_per_label={};
data_test_per_label={};

bounds_per_label = {}; 

% calculate each label's frequency
n_label = zeros(1, length(labels));
for i=1:length(labels)
  
  % calculate the frequency of each label in the orginal dataset
  n_label(i) = length( out( out==labels(i) ) );
  freq(i) = n_label(i)/n_s;
  
  % number of current label samples to be distributed in each of the 3 sets
  n_samples_tr = floor(freq(i)*0.6*n_s);
  n_samples_val = floor(freq(i)*0.2*n_s);
  n_samples_test = n_label(i) - n_samples_tr - n_samples_val;
  
  % assert that the sum of the 3 sets add up to n_label
  assert( n_samples_tr + n_samples_val + n_samples_test == n_label(i) );
  
  % sample uniformly on random taking into account gloabal min,max value
  % representantion in the three disjoint sets produced
  [data_tr_label, data_res] = ...
      preprocess_samples(data( out==labels(i),: ), n_samples_tr);

  [data_val_label, data_res] = preprocess_samples(data_res, n_samples_val);
  
  [data_test_label, data_res] = ...
      preprocess_samples(data_res, n_samples_test);
  
  data_tr_per_label{end+1} = data_tr_label;
  data_val_per_label{end+1} = data_val_label;
  data_test_per_label{end+1} = data_test_label;
  
  bounds_per_label{end+1} = [min(data_tr_label)-10^(-12);...
      max(data_tr_label)];

  % concatenate the per label disjoint sets to their global sets
  data_tr = [data_tr; data_tr_label];
  data_val = [data_val; data_val_label];
  data_test = [data_test; data_test_label];
end

% assert size of each set add up to n_s
assert( size(data_tr,1) + size(data_val,1) + size(data_test,1) == n_s);

%% Cross validation with class dependent subclustering

% rank features by importance for the default value 10 nearest neighbors
[rank_f,~] = relieff(data_tr(:,1:n_f-1), data_tr(:,n_f), 10);

% define some hyperparameter values to choose from
num_feat = [4 15 32]; % range 0 < num_features < n_f
clust_radius = [0.25 0.3 0.6]; % per data space range
  
meanErr = {};
combination = {};

for kf = 1:length(num_feat)
  % perform cross validation on the training set by output value
  % obtain k=5 disjoint sets, each divided into 80% train subsamples
  % and 20% test 
  csets = cvpartition(data_tr(:,n_f),'kfold',2);

  assert (csets.NumTestSets == 2);

  for kcv = 1:2
    % obtain cross validation training and test sets 
    in_tr_cv = data_tr( csets.training(kcv), rank_f( 1:num_feat(kf) ) );
    in_test_cv = data_tr( csets.test(kcv), rank_f(1:num_feat(kf) ) );

    out_tr_cv = data_tr( csets.training(kcv), n_f);
    out_test_cv = data_tr( csets.test(kcv), n_f);

    for kc = 1:length(clust_radius)
      
      bounds_per_label_mat = [];
      user_centers = [];
      for i=1:length(labels)
        
        intr = in_tr_cv(out_tr_cv == labels(i),:);
        outr = out_tr_cv(out_tr_cv == labels(i),:);
        
        %bounds_per_label = [min(intr) min(outr); max(intr) max(outr)];
        bounds_per_label = [min(intr); max(intr)];
        bounds_per_label_mat = [bounds_per_label_mat ; bounds_per_label];
        bounds_per_label
        % calculate the per label cluster centers
        centers = subclust([intr outr], clust_radius(kc),...
          [],[1.25 0.5 0.15 0]);
        user_centers = [user_centers; centers];
      end
      
      max_o = max(unique(data(:,179)));
      min_o = min(unique(data(:,179)));
      bounds = [min(bounds_per_label_mat) min_o;...
          max(bounds_per_label_mat) max_o]

      % initialization of the model
      model = genfis2( in_tr_cv,out_tr_cv,...
        clust_radius(kc),bounds,[1.25 0.5 0.15 0], user_centers );

      tr_cv = [in_tr_cv out_tr_cv];
      tr_test = [in_test_cv out_test_cv];
      % optimize model using anfis with parameter epoch_n=5
      [model_out, trErr, steps, model_c, testErr]=...
        anfis(tr_cv, model, [epoch 0 0.01 0.9 1.1], NaN,tr_test);
      
      meanErr{end+1} = min(testErr);
      combination{end+1} = [num_feat(kf) clust_radius(kc)];
    end

  end
  
end

% convert cell to mat and then find the minimum mean error index
meanErr_mat = cell2mat(meanErr);
[~, min_ind] = min(meanErr_mat);

% obtain the best hyperparameter values
best_hyp = combination{min_ind};

best_num_feat = best_hyp(1);
best_cluster_rad = best_hyp(2);

%% Train a TSK model on the best hyperparameters

% select the best features
indata_tr_red = data_tr(:, rank_f(1:best_num_feat) );
indata_val_red = data_val(:, rank_f(1:best_num_feat) );
indata_test_red = data_test(:, rank_f(1:best_num_feat) );

in_tr_cv = indata_tr_red;
out_tr_cv = data_tr(:,n_f);

for i=1:length(labels)

  intr = in_tr_cv(out_tr_cv == labels(i),:);
  outr = out_tr_cv(out_tr_cv == labels(i),:);

  bounds_per_label = [min(intr); max(intr)];
  bounds_per_label_mat = [bounds_per_label_mat ; bounds_per_label];

  % calculate the per label cluster centers
  centers = subclust([intr outr], clust_radius(kc),...
    [],[1.25 0.5 0.15 0]);
  user_centers = [user_centers; centers];
end

max_o = max(unique(data(:,179)));
min_o = min(unique(data(:,179)));
bounds = [min(bounds_per_label_mat) min_o; max(bounds_per_label_mat) max_o]


% initialization of the model
model = genfis2( in_tr_cv,out_tr_cv,...
  clust_radius(kc),bounds,[1.25 0.5 0.15 0], user_centers );

tr_cv = [in_tr_cv out_tr_cv];
tr_test = [in_test_cv out_test_cv];
% optimize model using anfis with parameter epoch_n=5
[model_out, trErr, steps, model_c, testErr]=...
  anfis(tr_cv, model, [epoch 0 0.01 0.9 1.1], NaN,...
  [indata_val_red data_val(:,n_f)]);

txt = sprintf('best_model');
writefis(model,txt);

epoch = 5; % training epoch

% evaluate model 
output = evalfis(indata_test_red, model_c);
output = round(output); % round to nearest integer

% calculate metrics on feature reduced test data
[errmat, OA, UA, PA, Khat] = evaluate_class( output, data_test(:,n_f) );



%% PLOTS 
figure();
num_epoch = size(steps,1);
plot(1:epoch, trErr);
text = sprintf('Learning Curve (Num-features=%i, Cluster-Radius=%i)',...
best_num_feat, best_cluster_rad);
title(text);

figure();
plot(1:size(output,1), output - data_test(:,n_f));
text = sprintf('Prediction Error (Num-features=%i, Cluster-Radius=%i)',...
best_num_feat, best_cluster_rad);
title(text);

txt = sprintf('Metrics for independent , Num-features=%i Radius: %f ',...
  best_num_feat, best_cluster_rad);
disp(txt);
errmat
OA
UA
PA
Khat


% plot original membership functions
figure();
subplot(2,2,1);
plotmf(model,'input',1);
title('MF original');
subplot(2,2,2);
plotmf(model,'input',2);
title('MF original');
subplot(2,2,3)
plotmf(model,'input',3);
title('MF original');
subplot(2,2,4)
plotmf(model,'input',4);
title('MF original');

% plot membership final functions
figure();
subplot(2,2,1);
plotmf(model_c,'input',1);
title('MF final');
subplot(2,2,2);
plotmf(model_c,'input',2);
title('MF final');
subplot(2,2,3)
plotmf(model_c,'input',3);
title('MF final');
subplot(2,2,4)
plotmf(model_c,'input',4);
title('MF final');

%% PART 2 - TSK regression High Dimensionality Dataset

% Dataset:  Contains data on 21263 superconductors and their relevant...
...features.
% Link: https://archive.ics.uci.edu/ml/datasets/Superconductivty+Data

clear ;
close all;

% Data preprocessing

% split data into training, validation and testing non-overlapping sets

% read csv from the second line and first column
initial_data = csvread('train.csv',1,0);


% remove samples that have at least on zero as an attribute value...
...(total of 215 samples)
[ini_sample_number, ini_feature_number] = size(initial_data);
indexes_removed_samples = [];

% number of samples that have at least one zero as an attribute value
count = 0;

for i=1:ini_sample_number
   for j=1:ini_feature_number
      if initial_data(i,j) == '0'
          count = count + 1;
          indexes_removed_samples(count) =  i;
          break;
      end
   end
end

initial_data(indexes_removed_samples,:) = [];

data = initial_data;
[sample_num, feature_num] = size(data);

training_num = floor(0.6*sample_num);
testing_num = ceil(0.2*sample_num);
val_num = ceil(0.2*sample_num);

assert(training_num + testing_num + val_num == sample_num);

% preprocess data and create the training set
[training_set, data_res] = preprocess_samples(data, training_num);

% preprocess data and create the validation set
[val_set, data_res] = preprocess_samples(data_res, val_num);

% sample the training set from the
[testing_set, data_res] = preprocess_samples(data_res, testing_num);

% preprocess data and create the testing set
assert( size(data_res,1) == 0);

assert ( size(training_set, 1) + size( val_set , 1) + ...
    size( testing_set , 1) == sample_num);

% rank features by importance (default value of 10 nearest neighbors)
[rank_f,~] = relieff(training_set(:,1:feature_num-1), ...
    training_set(:,feature_num), 10);

% define parameter values to choose from
num_feat = [4 14 33]; % range 0 < num_features < feature_num
cluster_rad = [0.25 0.4 0.6]; % per data space range

mean_err = {};
combination = {};

for kf = 1:length(num_feat)
  % perform cross validation on the training set
  % obtain k=5 disjoint sets, each divided into 80% train subsamples
  % and 20% test 
  csets = cvpartition(training_set(:,feature_num),'kfold',5);

  assert (csets.NumTestSets == 5);

  for kcv = 1:2
    % obtain cross validation training and test sets 
    in_tr_cv = training_set(csets.training(kcv), rank_f(1:num_feat(kf)));
    in_test_cv = training_set(csets.test(kcv), rank_f(1:num_feat(kf)));

    out_tr_cv = training_set( csets.training(kcv), feature_num);
    out_test_cv = training_set( csets.test(kcv), feature_num);

    for kc = 1:length(cluster_rad)
      % generate initial model with genfis2, uses subtractive clustering
      model = genfis2(in_tr_cv, out_tr_cv, cluster_rad(kc) );

      tr_cv = [in_tr_cv out_tr_cv];
      tr_test = [in_test_cv out_test_cv];
      % optimize model using anfis with parameter epoch_n=5
      [model_out, trErr, steps, model_c, testErr]=...
        anfis(tr_cv, model, [5 0 0.01 0.9 1.1], NaN,tr_test);
      
      mean_err{end+1} = min(testErr);
      combination{end+1} = [num_feat(kf) cluster_rad(kc)];
    end

  end
  
end

% convert cell to mat and then find the minimum mean error index
meanErr_mat = cell2mat(mean_err);
[~, min_ind] = min(meanErr_mat);

% obtain the best hyperparameter values
best_hyp = combination{min_ind};

best_num_feat = best_hyp(1);
best_cluster_rad = best_hyp(2);

%% Train a TSK model on the optimal parameters

% select the best features
indata_tr_red = training_set(:,rank_f(1:best_num_feat));
indata_val_red = val_set(:,rank_f(1:best_num_feat));
indata_test_red = testing_set(:,rank_f(1:best_num_feat));

% generate intial model using genfis2 
model = genfis2(indata_tr_red, training_set(:,feature_num), ...
    best_cluster_rad );

data_tr_red = [indata_tr_red training_set(:,feature_num)];
data_val_red = [indata_val_red val_set(:,feature_num)]; 
data_test_red = [indata_test_red testing_set(:,feature_num)];

epoch = 5; % training epoch

% optimize model using anfis, epoch_n = 5
[model_out, trErr, steps, model_c, valErr]=...
anfis(data_tr_red, model, [epoch 0 0.01 0.9 1.1], NaN, data_val_red);

% evaluate model 
output = evalfis(indata_test_red, model_c);

% calculate evaluation metrics on feature reduced test data
[mse, rmse, rsq, nmse, ndei]= evaluate(output, testing_set(:,feature_num));

%% PLOTS 
figure();
num_epoch = size(steps,1);
plot(1:epoch, trErr);
text = sprintf('Learning Curve (Num_features=%i, Cluster_Radius=%i)',...
best_num_feat, best_cluster_rad);
title(text);

figure();
plot(1:size(output,1), output - testing_set(:,feature_num));
text = sprintf('Prediction Error (Num_features=%i, Cluster_Radius=%i)',...
best_num_feat, best_cluster_rad);
title(text);

m_cell = {'MSE','RMSE','R2','NMSE','NDEI'};
disp(table(mse,rmse,rsq,nmse,ndei,'VariableNames',m_cell));

% plot original membership functions
figure();
subplot(2,2,1);
plotmf(model,'input',1);
title('Original MF');
subplot(2,2,2);
plotmf(model,'input',2);
title('Original MF');
subplot(2,2,3)
plotmf(model,'input',3);
title('Original MF');
subplot(2,2,4)
plotmf(model,'input',4);
title('Original MF');

% plot final membership functions
figure();
subplot(2,2,1);
plotmf(model_c,'input',1);
title('Final MF');
subplot(2,2,2);
plotmf(model_c,'input',2);
title('Final MF');
subplot(2,2,3)
plotmf(model_c,'input',3);
title('Final MF');
subplot(2,2,4)
plotmf(model_c,'input',4);
title('Final MF');
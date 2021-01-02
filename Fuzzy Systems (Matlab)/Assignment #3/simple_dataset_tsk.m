%% PART 1 - TSK regression in Simple Dataset

% Dataset:  NASA data set, obtained from a series of aerodynamic and...
...acoustic tests of two and three-dimensional airfoil blade sections...
...conducted in an anechoic wind tunnel.
% Link: https://archive.ics.uci.edu/ml/datasets/Airfoil+Self-Noise

%remove items from workspace
clear ;
% delete all figures
close all;

% Data preprocessing

% split data into training, validation and testing non-overlapping sets
data = load('airfoil_self_noise.dat');

[sample_num, feature_num] = size(data);

training_num = floor(0.6*sample_num);
testing_num = ceil(0.2*sample_num);
val_num = ceil(0.2*sample_num);

assert(training_num + testing_num + val_num == sample_num);

% preprocess data and create the training set
[training_set, data_res] = preprocess_samples(data, training_num);

% preprocess data and create the validation set
[val_set, data_res] = preprocess_samples(data_res, val_num);

% preprocess data and create the testing set 
[testing_set, data_res] = preprocess_samples(data_res, testing_num);

assert( size(data_res,1) == 0);

assert ( size(training_set, 1) + size( val_set , 1) + ...
    size( testing_set , 1)== sample_num);

% #i TSK model
mf_num = [2 3 2 3];
outmf_type = {'constant', 'constant', 'linear', 'linear'};
eval_metrics = zeros(4,5);

epoch_num = 10;

for i=1:4
  % create model
  model = genfis1(training_set, mf_num(i)*ones(1,feature_num-1),...
      'gbellmf', outmf_type{i} );

  % train model
  [model_t, training_err, steps, model_c, val_err] = anfis(training_set,...
      model,[epoch_num 0 0.01 0.9 1.1], NaN, val_set);

  % evaluate model on testing set
  % used chkFis (model_c) which is optimized for non-overfitting
  y = evalfis(testing_set(:,1:feature_num-1), model_c);

  % calculate evalutation metrics on testing set
  [mse, rmse, rsq, nmse, ndei] = evaluate(y, testing_set(:,feature_num));
  
  eval_metrics(i,:) = [mse, rmse, rsq, nmse, ndei];
  
  % plot fuzzy sets
  figure(i);
  for j=1:size(data,2)-1
    subplot(ceil(feature_num/2),floor(feature_num/2),j);
    plotmf(model_c,'input',j);
  end
  
  % 2nd requirement
  figure(i+4);
  num_epoch = size(steps,1);
  plot(1:num_epoch, training_err);
  text = sprintf('Learning Curve of TSK Model %i',i); 
  title(text);
  
  % 3rd requirement
  figure(i+8);
  plot(1:testing_num, y - testing_set(:,feature_num));
  text = sprintf('Prediction Error for TSK Model %i',i);
  title(text);
  
end

% 4th requirement
% display evaluation metrics table
m_cell = {'MSE','RMSE','R2','NMSE','NDEI'};
tsk_cell = {'TSK_model_1','TSK_model_2','TSK_model_3','TSK_model_4'};
disp(table(eval_metrics(:,1),eval_metrics(:,2),eval_metrics(:,3),...
    eval_metrics(:,4),eval_metrics(:,5),'VariableNames',m_cell,...
    'RowNames',tsk_cell));

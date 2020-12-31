function fis_model = controller(MAX_STEER, fig_num)

%% Fuzzy controller

% generate new fis fis_model
fis_model = newfis('car_cntlr','mamdani','min','max','prod','max','centroid');

MAX_THETA = 180;


 % add variables
  fis_model = addvar(fis_model,'input','dv',[0 1]);
  fis_model = addvar(fis_model,'input','dh',[0 1]);
  fis_model = addvar(fis_model,'input','theta',[-MAX_THETA MAX_THETA]);
  fis_model = addvar(fis_model,'output','delta-theta',[-MAX_STEER,MAX_STEER]);
  
  sensor_mf_names = {};
  sensor_mf_names{end+1} = 'S';
  sensor_mf_names{end+1} = 'M';
  sensor_mf_names{end+1} = 'L';
  
  steering_mf_names = {};
  steering_mf_names{end+1} = 'N';
  steering_mf_names{end+1} = 'ZE';
  steering_mf_names{end+1} = 'P';

% membership function names
sin_d = 'SML';
sin_th = 'NZP';

% add membership functions
  for i=1:3
    bs = (i-1)*0.5;
    as = bs - 0.5;
    cs = bs + 0.5;

    fis_model = addmf(fis_model,'input',1,sensor_mf_names{i},'trimf',[as bs cs]);
    fis_model = addmf(fis_model,'input',2,sensor_mf_names{i},'trimf',[as bs cs]);
    
    bt = -MAX_THETA + (i-1)*MAX_THETA;
    at = bt - MAX_THETA;
    ct = bt + MAX_THETA;
    
    fis_model = addmf(fis_model,'input',3,steering_mf_names{i},'trimf',[at bt ct]);
    
    bdt = -MAX_STEER + (i-1)*MAX_STEER;
    adt = bdt - MAX_STEER;
    cdt = bdt + MAX_STEER;
    
    fis_model = addmf(fis_model,'output',1,steering_mf_names{i},'trimf',[adt bdt cdt]);
  end

% rules
rules= [ 3 1 1 2 1 1; 3 2 1 2 1 1; 3 3 1 2 1 1;
       2 1 1 3 1 1; 2 2 1 2 1 1; 2 3 1 3 1 1;
       1 1 1 3 1 1; 1 2 1 3 1 1; 1 3 1 3 1 1;
 
       3 1 2 1 1 1; 3 2 2 2 1 1; 3 3 2 2 1 1;
       2 1 2 2 1 1; 2 2 2 1 1 1; 2 3 2 2 1 1;
       1 1 2 3 1 1; 1 2 2 2 1 1; 1 3 2 3 1 1; 
 
       3 1 3 1 1 1; 3 2 3 2 1 1; 3 3 3 2 1 1;
       2 1 3 1 1 1; 2 2 3 1 1 1; 2 3 3 3 1 1;
       1 1 3 2 1 1; 1 2 3 1 1 1; 1 3 3 3 1 1; ];

fis_model=addrule(fis_model,rules);
writefis(fis_model,'fis_model');

%% PLOTS

% plot membership functions
figure();
subplot(2,2,1)
plotmf(fis_model,'input',1);

subplot(2,2,2)
plotmf(fis_model,'input',2);

subplot(2,2,3)
plotmf(fis_model,'input',3);

subplot(2,2,4)
plotmf(fis_model,'output',1);

end

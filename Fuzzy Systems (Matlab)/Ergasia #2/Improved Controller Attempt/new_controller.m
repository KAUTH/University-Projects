function fis_model = controller(MAX_STEER, fig_num)

%% Fuzzy controller

% read new fis fis_model
fis_model = readfis('new_fis_model.fis');

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

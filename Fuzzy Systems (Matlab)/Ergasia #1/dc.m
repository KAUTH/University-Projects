clear 
close all

%% DC System Implementation

% assuming T_L = 0 (no disturbances), Motor = Omega/V_a
Motor = tf([18.69],[1 12.064]);

% distrurbance transfer function, consider Va=0
Pdist = tf([2.92 2.92*440],[1 12.064]);

% initial PI controller gains and zero
a=0.01;
Ki=1;
Kp=1;

% break flag
flag=0;

for a=0.01:0.01:0.1
  for Kp = 0.1:0.2:10
    % PI Controller
    Ki = a*Kp;
    Ctlr = tf(Kp*[1 a],[1 0]);
    
    
    % Open loop transfer function
    Ho = Ctlr*Motor;

    % Closed loop transfer function
    Hc = Ho/(1+Ho);

    % step response info
    step_results = stepinfo(Hc);
    
    Ht = Pdist / (1 + Ho);

    if (step_results.RiseTime < 160 && step_results.Overshoot < 5 &&...
            20*log10(abs(evalfr(Ht, j))) < 20)
      txt = sprintf('Gain values are KI=%.2f and KP=%.2f, zero at -%.2f (a)',Ki,Kp,a);
      disp(txt);
      flag=1;
      break
    end
  end
  if flag
    break
  end
end

% PI Controller
Ctlr = tf(Kp*[1 a],[1 0]);

% Open loop transfer function
Ho = Ctlr*Motor;

% Closed loop transfer function
Hc = Ho/(1+Ho);

% step response info
% figure(5); % restore to uncomment later
% step(Hc)

%% Disturbance Rejection

% PI Controller
Ki = 6.3;
Kp = 0.04;

a = Ki / Kp;
Ctlr = tf(Kp*[1 a],[1 0]);


% Open loop transfer function
Ho = Ctlr*Motor;

% Closed loop transfer function
Hc = Ho/(1+Ho);

Ht = Pdist / (1 + Ho);

figure(20);
bode(Ht)

%%  Fuzzy Controller Design

% new model
model = newfis('model','mamdani','min','max','prod','max','centroid');

% add variables

% range of e variable
A = 1;

%range of de variable
Ade = 1;

model = addvar(model,'input','e',[-A,A]);
model = addvar(model,'input','de',[-Ade,Ade]);
model = addvar(model,'output','du',[-A,A]);

mf_names = {};

mf_names{end+1}='NV';
mf_names{end+1}='NL';
mf_names{end+1}='NM';
mf_names{end+1}='NS';
mf_names{end+1}='ZR';
mf_names{end+1}='PS';
mf_names{end+1}='PM';
mf_names{end+1}='PL';
mf_names{end+1}='PV';

for i=0:8
  b = -Ade + i*Ade/4;
  a = b - Ade/4 ;
  c = b + Ade/4 ;
  model = addmf(model,'input',2,mf_names{i+1},'trimf',[a,b,c]);
  model = addmf(model,'output',1,mf_names{i+1},'trimf',[a,b,c]);
end

for i=0:6
  b = -A + i*A/3;
  a = b - A/3;
  c = b + A/3;
  model = addmf (model, 'input',1,mf_names{i+2},'trimf',[a b c]);
end

figure(1);
for i = 1:2
  subplot(2,2,i);
  plotmf(model,'input',i);
end

i = i+1;
subplot(2,2,i);
plotmf(model,'output',1);

rule_tab = toeplitz([6 5 4 3 2 ones(1,4)],[6 7 8 9*ones(1,4)]);

n_r = 9*7;
rule_tab = reshape(rule_tab,[1 n_r]);
rules = zeros(n_r, 5);

for i=1:n_r
  rules(i,:) = [floor((i-1)/9)+1, 9-mod(i-1,9), rule_tab(i), 1 , 1 ];
end


% add the rule base to the model
model=addrule(model,rules);

% generate surface
figure(2);
gensurf(model);

% show rule firing
ruleview(model);

% write model to dc.fis
writefis(model,'model');
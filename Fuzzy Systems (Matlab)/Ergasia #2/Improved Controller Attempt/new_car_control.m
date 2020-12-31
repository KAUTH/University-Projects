function car(theta_initial)
  % car_control  Simulate the route of a fuzzy controlled car.
  %   car_control(theta)
  %
  %   INPUT
  %     theta_initial:
  %       The initial angle ,in degrees, of the car's direction vector.
  %   
  %   OUTPUT
  %     None
  %
  
  %% Figure utility
  map = 1;
  
  %% PLOT MAP

  figure(map);
  
  hold on;

  % plot wall 

  x_wall = [5 5 6 6 7 7 10];
  y_wall = [0 1 1 2 2 3 3];

  plot(x_wall, y_wall,'Color',[0.15 0.15 0.15],'LineWidth',2.75);

  bcolor = [0.75 0.75 0.75];
  for i=1:3
    xc = [5 6 7 10];
    xw = [1 1 3]; 
    rectangle('Position',[ xc(i) 0 xw(i) i],'FaceColor',bcolor,...
      'EdgeColor',bcolor);
  end

  % 45 degree hatch with dhatch = 0.1
  area = (10-5)*(3-0);
  t1 = 5:0.01:6;
  t2 = 6.01:0.01:7;
  t3 = 7.01:0.01:10;
  for i =-area*10:area*10
    l1 = i*0.1 + sqrt(2)/2*(t1-5);
    l2 = i*0.1 + sqrt(2)/2*(t2-6);
    l3 = i*0.1 + sqrt(2)/2*(t3-7);
    l1r = l1(l1<=1);
    l2r = l2(l2<=2);
    l3r = l3(l3<=3);
    t1r = t1(l1<=1);
    t2r = t2(l2<=2);
    t3r = t3(l3<=3);
    hatch_color = [1 1 1];
    plot(t1r,l1r,'Color',hatch_color,'LineWidth',1.2);
    plot(t2r,l2r,'Color',hatch_color,'LineWidth',1.2);
    plot(t3r,l3r,'Color',hatch_color,'LineWidth',1.2);
  end

  text(6.5,1.5,'OBSTRUCTED AREA','Color',bcolor-0.45,...
    'BackgroundColor',[237, 119, 92]./255);

  axis([0 10 0 10]); % set axes limits
  grid on;

  msg = sprintf('Live Route Map, theta-initial = %d',theta_initial);
  title(msg,'FontSize',14);

  % plot initial and destination points
  x_init = 4;
  y_init = 0.4;

  % destination
  x_dest = 10;
  y_dest = 3.2;

  plot(x_init,y_init,'g.','MarkerSize',20);
  plot(x_dest,y_dest,'r.','MarkerSize',20);

  %% SYSTEM AND CONTROLLER

  % generate controller 
  car_cntlr = new_controller(130,map+1);
  
  u = 0.05; % car speed [meters per second]

  Tstep = 0.5; % timestep

  x = x_init;
  y = y_init;
  theta = theta_initial;
  
  error = 0.05;
  
  % loop until arrival to the target point's local region
  while abs(x - x_dest) > error
       
    % get sensor readings for current position 
    [dh, dv] = distance_sensors(x,y);

    % evaluate fuzzy controller on current input values
    dtheta = evalfis([dv dh theta], car_cntlr);
    
    % update the car's direction vector angle
    theta = theta + dtheta;
    
  % wrap up to correct range
    if theta > 180
      theta = theta - 360;
    elseif theta < -180
      theta = 360 + theta;
    end

    % Continuous time equations of motion of the car (modeled as a point)
    % dx/dt = ux = constant => x = xo + t*ux => x = xo + (t-to)*u*cos(theta)
    % dy/dt = uy = constant => y = yo + t*uy => y = yo + (t-to)*u*sin(theta)
    % Discretizing time (i.e. t = n*Tstep) leads to 
    % the following modified equations of motion (forward difference method)
    % x(n+1) = x(n) + Tstep * u * cos(theta(n))
    % y(n+1) = y(n) + Tstep * u * sin(theta(n)))
    x = x + Tstep*u*cosd(theta);
    y = y + Tstep*u*sind(theta);

    i=i+1;
    
    figure(map);    % specify map figure
    plot(x,y,'b.','MarkerSize',5);  % update map with the car's new position
    
  end
  
  hold off;

end








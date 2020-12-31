%% Draw a region map to help the rule design process

% create a colormap for the 9 dv,dh permutaions
c = colormap(hsv(900));

i=1;

hold on
  
x=[5 5 6 6 7 7 10];
y=[0 1 1 2 2 3 3];

plot(x,y,'k','LineWidth',1.5);
axis([0 10 0 4]);

for xd=0:0.1:10
  for yd=0:0.1:4
    
    [dh, dv]= distance_sensors(xd,yd);
    
    if dv < 0.25
      if dh < 0.25
          i=100;
      elseif dh < 0.75
          i=200;
      else
          i=300;
      end
    elseif dv < 0.75  
      if dh < 0.25
       i=400;   
      elseif dh < 0.75
        i=500;  
      else    
        i=600;
      end
    else
      if dh < 0.25
          i=700;
      elseif dh < 0.75
          i=800;

      else
          i=900;

      end
    end

    if xd<5 || (xd<6 && yd > 1) || (xd<7 && yd>2) || (xd<10 && yd>3) 
      plot(xd,yd,'Color',c(i,:,:),'Marker','.','MarkerSize',10);
    end
  end
end
plot(10,3.2,'r.','MarkerSize',20);
plot(4,0.4,'g.','MarkerSize',20);

hold off

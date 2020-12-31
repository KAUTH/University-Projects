function [dh,dv] = distance_sensors(x,y)
    
    % vertical distance from obstacle
    if (x >= 0 && x < 5)
        dv = y;
    elseif (x >= 5 && x < 6)
        dv = y - 1;
    elseif (x >= 6 && x < 7)
        dv = y - 2;
    else
        dv = y - 3; 
    end

    % horizontal distance from obstacle
    if (y >= 0 && y < 1)
        dh= 5 - x;
    elseif (y >= 1 && y < 2)
        dh= 6 - x;
    elseif (y >= 2 && y < 3)
        dh= 7 - x;
    else
        dh= 10 - x;
    end

   dv = abs(dv) / 4;
   dh = abs(dh) / 10;
    
end
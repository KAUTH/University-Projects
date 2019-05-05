x = 0:0.05:1;
b = [1 2 4 8 inf];
y = zeros(1,21);    % preallocating memory,  the code runs much faster because there is 
                    % no need to repeatedly reallocate memory for the growing data structure

for i = b
    k = 0;
    for m = x 
        y(k+1) = waiting(i,m);
        k = k + 1;
    end
    plot(x,y);
    hold on;
    ylim([0,10]);   % sets max/min limit in the y axis
    xlim([0,1]);       % sets max/min limit in the x axis
end
x = 0:50;
p = [0.7 0.75 0.8 0.85 0.9 0.95];
y = zeros(1,51);    % preallocating memory,  the code runs much faster because there is 
                    % no need to repeatedly reallocate memory for the growing data structure
for i = p
    for k = 0:50
        y(k+1) = cell_loss(k,i,inf);
    end
    plot(x,y);
    hold on;
    ylim([10^-12,1]);   % sets max/min limit in the y axis
    xlim([0,50]);       % sets max/min limit in the x axis
    set(gca, 'YScale', 'log')   % sets logarithmic scale for axis
end
    
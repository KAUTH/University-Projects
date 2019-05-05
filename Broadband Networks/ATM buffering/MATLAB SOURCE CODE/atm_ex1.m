x = 0:80;
N = [2 4 8 16 32 inf];
p = 0.9;            % or p = 0.9
y = zeros(1,81);    % preallocating memory,  the code runs much faster because there is 
                    % no need to repeatedly reallocate memory for the growing data structure
for i = N
    for k = 0:80
        y(k+1) = cell_loss(k,p,i);
    end
    plot(x,y);
    hold on;
    ylim([10^-12,1]);   % sets max/min limit in the y axis
    xlim([0,80]);       % sets max/min limit in the x axis
    set(gca, 'YScale', 'log')   % sets logarithmic scale for axis
end
    
k = 0:0.01:10;     % x coordinated of plot

y = zeros(1,1000);    % preallocating memory,  the code runs much faster because there is 
                    % no need to repeatedly reallocate memory for the growing data structure

z = 1;          % initial value of counter      

for i = k
   y(z) = spikenum(data8,i);  
   z = z + 1;
end

plot(k,y);
xlabel('k')
ylabel('# of spikes')
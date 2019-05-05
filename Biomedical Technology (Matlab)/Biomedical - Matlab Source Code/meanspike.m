function num = meanspike(data)

for i = 1:1440000
        if data(i) > 0
            datapos(i) = data(i);
        end
end
    m = mean(datapos);
    
    num = 0;                        % initializing spikes to zero
    for i = 1:length(data)
        if data(i) > m
            num = num + 1;
        end
    end
    
    
function num = spikenum(data,k)
    s = median(abs(data)) / 0.6745; % calculatin of standard deviation of noise
    T = k*s;                        % T: the threashold
    len = length(data);
    num = 0;                        % initializing spikes to zero
    i = 1;                          % i variable used to parse through the data
    flag = 1;                       % flag used to spot the correct # of spikes
 
    while i <= len
            if data(i) > T && flag ==1
                    num = num + 1;
                    flag = 0;
            elseif data(i) < T
                flag = 1;
            end
            i = i + 1;
    end
    
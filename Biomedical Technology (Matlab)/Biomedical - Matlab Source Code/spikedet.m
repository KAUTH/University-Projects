function spikeTimeEst = spikedet(data,k)
    s = median(abs(data)) / 0.6745; % calculation of standard deviation
    T = k*s;                        % T: the threashold
    len = length(data);
    spikeTimeEst = zeros(1,len);    % preallocating memory 
    num = 0;                        % initializing spikes to zero
    i = 1;                          % i variable used to parse through the data
    flag = 1;                       % flag used to spot the correct # of spikes
 
    while i <= len
            if data(i) > T && flag ==1
                    num = num + 1;
                    spikeTimeEst(num) = i;
                    flag = 0;
            elseif data(i) < T
                flag = 1;
            end
            i = i + 1;
    end
    spikeTimeEst = spikeTimeEst(1:num); % removes the rest of non used array
function SpikeEst = isolate(data, spikeTimesEst)
     len = length(spikeTimesEst);   % length of spikeTimeEst
     i = 1;                         % initializing counter
     SpikeEst = zeros(len, 64);     % preallocating 2d matrix
     hold on;
     
     while i <= len
         temp = data(spikeTimesEst(i)-32:spikeTimesEst(i)+31);  % finding min and max indexes in data for the 
                                                                % 64 samples window
         maximum = max(temp);                                  
         max_i = find(temp == maximum);                         % index (in the temp array) of max
         minimum = min(temp);
         min_i = find(temp == minimum);                         % index (in the temp array) of min
         first = min(max_i,min_i);                              % index (in the temp array) of the first extremum
         first_val = temp(first);                               % value of the first extremum
         index = find(data == first_val);                       % index (in the data array) of the first extremum 
         
         SpikeEst(i,:) = data(index - 22:index + 41);           % chose 64 sample window 
                                                                % waveforms are alligned with index (the first extremum)
         i = i + 1;
     end
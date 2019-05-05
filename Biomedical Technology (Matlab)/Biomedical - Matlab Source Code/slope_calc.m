%SpikeEst = isolate(data1, spikeTimesEst1);
cols = length(SpikeEst(1,:));
rows = length(SpikeEst(:,1)); 
SpikeSlope = zeros(rows,1);

for i = 1:rows
    maximum = max(SpikeEst(i,:));                                  
    max_i = find(SpikeEst(i,:) == maximum);                  % index (in the temp array) of max
    minimum = min(SpikeEst(i,:));
    min_i = find(SpikeEst(i,:) == minimum);
    
    SpikeSlope(i) = (maximum - minimum) / (max_i - min_i);   % find the slope between the two extremums
end
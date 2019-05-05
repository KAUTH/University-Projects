%SpikeEst = isolate(data1, spikeTimesEst1);
cols = length(SpikeEst(1,:));
rows = length(SpikeEst(:,1)); 
SpikeMaxInd = zeros(rows,1);

for i = 1:rows
    maximum = max(SpikeEst(i,:));                                  
    max_i = find(SpikeEst(i,:) == maximum);                  % index (in the temp array) of max
    minimum = min(SpikeEst(i,:));
    min_i = find(SpikeEst(i,:) == minimum);
    
    if max_i < min_i
        max_first = 1;                                       % finds where max is first
    else
        max_first = 0;
    end
    
    SpikeMaxInd(i) = max_first;   
end
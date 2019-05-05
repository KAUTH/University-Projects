%SpikeEst = isolate(data1, spikeTimesEst1);
cols = length(SpikeEst(1,:));
rows = length(SpikeEst(:,1)); 
SpikeStd = zeros(rows,1);

for i = 1:rows
    SpikeStd(i) = std(SpikeEst(i,:));       % find the standard deviation of spike array
end
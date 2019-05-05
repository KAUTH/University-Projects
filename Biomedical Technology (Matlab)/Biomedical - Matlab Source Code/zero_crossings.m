%SpikeEst = isolate(data1, spikeTimesEst1);
cols = length(SpikeEst(1,:));
rows = length(SpikeEst(:,1)); 
Zeros = zeros(rows,1);

for i = 1:rows
   Zeros(i) = spikenum(SpikeEst(i,:),0);        % find how many times each spike has crossed zero
end

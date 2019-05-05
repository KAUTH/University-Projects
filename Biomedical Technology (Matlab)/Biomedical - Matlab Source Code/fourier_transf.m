SpikeEst = isolate(data1, spikeTimesEst1);
cols = length(SpikeEst(1,:));
rows = length(SpikeEst(:,1)); 
SpikeTransform = zeros(rows);

for i = 1:rows
   SpikeTransform(i) = fft([SpikeEst(i,:)]);
end
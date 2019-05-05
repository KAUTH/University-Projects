%SpikeEst = isolate(data1, spikeTimesEst1);
cols = length(SpikeEst(1,:));
rows = length(SpikeEst(:,1)); 
SpikeEnergy = zeros(rows,1);

for i = 1:rows
   F = fft(SpikeEst(i,:));
   pow = F.*conj(F);
   SpikeEnergy(i) = sum(pow) / cols;   % find how much energy the spike has with DFT
end

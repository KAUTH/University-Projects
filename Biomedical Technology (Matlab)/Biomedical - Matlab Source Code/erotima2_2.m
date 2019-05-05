x = 1:1:64;
SpikeEst = isolate(data4,spikeTimesEst4);
len = length(spikeTimesEst4);   % length of spikeTimeEst

for i = 1:len
   plot(x,SpikeEst(i,:),'color','blue');  
   i = i + 1;
end
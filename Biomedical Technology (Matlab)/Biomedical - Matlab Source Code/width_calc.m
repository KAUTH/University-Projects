%SpikeEst = isolate(data1, spikeTimesEst1);
cols = length(SpikeEst(1,:));
rows = length(SpikeEst(:,1)); 
SpikeWidth = zeros(rows,1);

for i = 1:rows
    %{
   [c,l] = wavedec(SpikeEst(i,:),3,'db2');
   SpikeWavelet(i) = mean(c);   % find std of discrete wavelet analysis coeffs
    %}
    
    %{
    maximum = max(SpikeEst(i,:));   
    minimum = min(SpikeEst(i,:));
    SpikeWavelet(i) = abs(maximum - minimum);
    %}
    
    maximum = max(SpikeEst(i,:));                                  
    max_i = find(SpikeEst(i,:) == maximum);                  % index (in the temp array) of max
    minimum = min(SpikeEst(i,:));
    min_i = find(SpikeEst(i,:) == minimum);
    
    SpikeWidth(i) = abs(max_i - min_i);
end

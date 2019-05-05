spikeTimesEst = spikeTimesEst1;
spikeTimes = spikeTimes1;

len = length(spikeTimesEst);   % length of spikeTimeEst
len2 = length(spikeTimes);     % length of spikeTimes
displacement = 40;             % how much shifting of the two spikes in time we consider to be the same spike
correct = 0;                   % number of correct spikes detected
false = 0;                     % number of wrong spikes detected
y = 1;
init = 1;                      % where to start checking from in spikeTimes
index_pos_real = zeros(1,len2);     % the index position of the spikeTimes array, where a spike was correctly found
index_pos_est = zeros(1,len);       % the index position of the spikeTimesEst array, where a spike was correctly found
recorded_shift = zeros(1,len);      % what was the displacement/shift for the different samples
flag = 1;
for i = 1:len
    while y <= len2
        if abs(spikeTimesEst(i) - spikeTimes(y)) < displacement && index_pos_real(y) ~= 1
            correct = correct + 1;
            index_pos_real(y) = 1;      % denotes which particular spike was found (from spikeTimes)
            index_pos_est(i) = 1;       % denotes if particular spike was correctly found (from spikeTimesEst)
            recorded_shift(correct) = spikeTimesEst(i) - spikeTimes(y);
            break;
        else
            y = y + 1;
        end
    end
    y = 1;
    i = i + 1;
end

false = len - correct;


fprintf('The number of correctly detected spikes is: ');
correct
fprintf('\n');
fprintf('The number of falsely detected spikes is: ');
length(find(index_pos_est == 0))        % or just false
fprintf('\n');

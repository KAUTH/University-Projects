
    Char1Match = transpose(SpikeSlope) .* index_pos_est;      % to only get the correctly found spikes
    Char2Match = transpose(SpikeStd) .* index_pos_est; 
    Char3Match = transpose(Zeros) .* index_pos_est;
    Char4Match = transpose(SpikeEnergy) .* index_pos_est;
    Char5Match = transpose(SpikeMaxInd) .* index_pos_est;
    Char6Match = transpose(SpikeWidth) .* index_pos_est;
    spikeClassMatch = spikeClass4 .* index_pos_real; 
    
    empty1 = find(Char1Match == 0);                      % findind zeros to get rid of the spikes that are false
    empty2 = find(spikeClassMatch == 0);
    
    Char1Match(empty1) = [];                             % getting rid of the spikes that are false
    Char2Match(empty1) = [];
    Char3Match(empty1) = [];
    Char4Match(empty1) = [];
    Char5Match(empty1) = [];
    Char6Match(empty1) = [];

    spikeClassMatch(empty2) = [];
    
    group = transpose(spikeClassMatch);
    
    T1 = transpose(Char1Match);
    T2 = transpose(Char2Match);
    T3 = transpose(Char3Match);
    T4 = transpose(Char4Match);
    T5 = transpose(Char5Match);
    T6 = transpose(Char6Match);
    Data = [T1 T2 T3 T4 T5 T6];
function scat_plot(Char1, Char2, spikeClass, index_pos_est, index_pos_real)
    %erotima2_3;                            % run the script with the corresponding spikeTimesEst, spikeTimes
                                            % to get the index_pos_est, index_pos_real variables
    hold on
    
    Char1Match = transpose(Char1) .* index_pos_est;      % to only get the correctly found spikes
    Char2Match = transpose(Char2) .* index_pos_est; 
    spikeClassMatch = spikeClass .* index_pos_real; 
    
    empty1 = find(Char1Match == 0);                      % finding zeros to get rid of the spikes that are false
    empty2 = find(spikeClassMatch == 0);
    
    Char1Match(empty1) = [];                             % getting rid of the spikes that are false
    Char2Match(empty1) = [];

    spikeClassMatch(empty2) = [];

    indexes1 = find(spikeClassMatch == 1);               % finding at which indexes there are spikes of class 1, class 2, class 3
    indexes2 = find(spikeClassMatch == 2);
    indexes3 = find(spikeClassMatch == 3);
    
    k = 1;
    
    for i = indexes1                                     % saving the spikes of only one neuron that are correct according to spikeTimes
        char1_c1(k) = Char1Match(i);
        char2_c1(k) = Char2Match(i);
        k = k + 1;
    end
    
    scatter(char1_c1, char2_c1, '*', 'blue')
    
    %---------------------
    k = 1;
    
    for i = indexes2
        char1_c2(k) = Char1Match(i);
        char2_c2(k) = Char2Match(i);
        k = k + 1;
    end
    
    scatter(char1_c2, char2_c2, '*', 'red')
    
     %---------------------
    k = 1;
    
    for i = indexes3
        char1_c3(k) = Char1Match(i);
        char2_c3(k) = Char2Match(i);
        k = k + 1;
    end
    
    scatter(char1_c3, char2_c3, '*', 'yellow')
    
    
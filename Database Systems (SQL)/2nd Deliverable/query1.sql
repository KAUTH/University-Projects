SELECT species_name FROM SPECIES 
WHERE (reproduction_period_start = 'JUN' AND reproduction_period_end = 'JUL') 
OR (reproduction_period_start = 'JUL' AND reproduction_period_end = 'AUG') 
OR (reproduction_period_start = 'JUN' AND reproduction_period_end = 'AUG')
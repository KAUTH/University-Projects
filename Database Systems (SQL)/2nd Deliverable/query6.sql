SELECT 
species_name,region_id,energy_footprint
FROM 
(SELECT * FROM species WHERE migration=FALSE) a JOIN 
(SELECT lives_in.region_id,lives_in.registry_id,location.energy_footprint FROM location JOIN lives_in 
ON location.region_id=lives_in.region_id) b
ON a.registry_id=b.registry_id 
WHERE  energy_footprint>10;
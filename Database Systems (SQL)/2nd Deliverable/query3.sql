SELECT species_name FROM 
(SELECT registry_id,region_id FROM lives_in 
WHERE region_id=5 ) a 
JOIN 
(SELECT species_name,registry_id FROM species 
WHERE eating_habits='herbivorous' 
UNION 
SELECT species_name,registry_id FROM species 
WHERE eating_habits='omnivorous' ) b 
ON (a.registry_id=b.registry_id);
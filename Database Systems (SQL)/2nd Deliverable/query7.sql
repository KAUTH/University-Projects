SELECT d.species_name , c.area_characteristics FROM 
(SELECT  b.registry_id , a.area_characteristics  FROM 
(SELECT region_id , area_characteristics FROM location WHERE habitat='Prespes_SouthWest' ) a 
JOIN 
( SELECT registry_id , region_id  FROM lives_in ) b 
ON (a.region_id=b.region_id)) c 
JOIN ( SELECT registry_id , species_name FROM species ) d 
ON (c.registry_id=d.registry_id) 
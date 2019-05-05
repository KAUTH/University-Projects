SELECT species_name, weight FROM characteristics 
JOIN species 
ON species.registry_id=characteristics.registry_id 
WHERE characteristics.gender='MALE' AND characteristics.weight<10

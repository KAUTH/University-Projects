# CREATING VOLUNTEER USER
CREATE USER 'volunteer'@'localhost' IDENTIFIED BY 'password'; 
CREATE USER 'volunteer'@'%' IDENTIFIED BY 'password';
# GIVING PRIVILEGES TO VOLUNTEER USER
GRANT SELECT ON mydb.species TO 'volunteer'@'localhost';
GRANT SELECT ON mydb.population TO 'volunteer'@'localhost';
GRANT SELECT ON mydb.location TO 'volunteer'@'localhost';
GRANT SELECT ON mydb.lives_in TO 'volunteer'@'localhost';
GRANT SELECT ON mydb.characteristics TO 'volunteer'@'localhost';
GRANT SELECT ON mydb.user TO 'volunteer'@'localhost';
GRANT SELECT ON mydb.equipment TO 'volunteer'@'localhost';
GRANT SELECT ON mydb.program TO 'volunteer'@'localhost';

GRANT SELECT ON mydb.species TO 'volunteer'@'%';
GRANT SELECT ON mydb.population TO 'volunteer'@'%';
GRANT SELECT ON mydb.location TO 'volunteer'@'%';
GRANT SELECT ON mydb.lives_in TO 'volunteer'@'%';
GRANT SELECT ON mydb.characteristics TO 'volunteer'@'%';
GRANT SELECT ON mydb.user TO 'volunteer'@'%';
GRANT SELECT ON mydb.equipment TO 'volunteer'@'%';
GRANT SELECT ON mydb.program TO 'volunteer'@'%';
#
GRANT INSERT, UPDATE ON mydb.species TO 'volunteer'@'localhost';
GRANT INSERT, UPDATE ON mydb.population TO 'volunteer'@'localhost';
GRANT INSERT, UPDATE ON mydb.location TO 'volunteer'@'localhost';
GRANT INSERT, UPDATE ON mydb.lives_in TO 'volunteer'@'localhost';
GRANT INSERT, UPDATE ON mydb.characteristics TO 'volunteer'@'localhost';

GRANT INSERT, UPDATE ON mydb.species TO 'volunteer'@'%';
GRANT INSERT, UPDATE ON mydb.population TO 'volunteer'@'%';
GRANT INSERT, UPDATE ON mydb.location TO 'volunteer'@'%';
GRANT INSERT, UPDATE ON mydb.lives_in TO 'volunteer'@'%';
GRANT INSERT, UPDATE ON mydb.characteristics TO 'volunteer'@'%';

# CREATING GUEST USER
CREATE USER 'guest'@'localhost';
CREATE USER 'guest'@'%';
# GIVING PRIVILEGES TO GUEST USER
GRANT SELECT ON mydb.species TO 'guest'@'localhost';
GRANT SELECT ON mydb.population TO 'guest'@'localhost';  
GRANT SELECT ON mydb.lives_in TO 'guest'@'localhost'; 
GRANT SELECT ON mydb.location TO 'guest'@'localhost'; 
GRANT SELECT ON mydb.characteristics TO 'guest'@'localhost'; 

GRANT SELECT ON mydb.species TO 'guest'@'%';
GRANT SELECT ON mydb.population TO 'guest'@'%';  
GRANT SELECT ON mydb.lives_in TO 'guest'@'%'; 
GRANT SELECT ON mydb.location TO 'guest'@'%'; 
GRANT SELECT ON mydb.characteristics TO 'guest'@'%';

# CREATING ADMIN USER
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'superadmin'; 
# GIVING PRIVILEGES TO ADMIN USER
GRANT ALL PRIVILEGES ON mydb.* TO 'admin'@'localhost'; 


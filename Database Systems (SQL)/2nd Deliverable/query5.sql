SELECT b.user_name FROM 
(SELECT * FROM program 
WHERE program.area='Axios' AND program.year=2020) a 
JOIN (SELECT user_program.user_name,user_program.description_code FROM user_program) b 
ON (a.description_code=b.description_code);
SELECT user_name , area, program.description_code FROM program 
JOIN user_program 
ON program.description_code = user_program.description_code 
WHERE program.day=7 AND program.month=10 AND program.year=2021

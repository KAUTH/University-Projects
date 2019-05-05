BirdwatchingDB web app 
----------------------

The application utilizes the python microframework Flask in addition to the database management system MySQL.

-Prerequisites
    1. Python 2.7 (pip and python must be installed, the executables' paths must be listed in the PATH variable) 
        Libraries: 
          
NOTE:(you can create a virtual environment, although it is not required,follow this guide: flask.pocoo.org/docs/1.0/installation/#virtual-environments.To access the virtual environment run . venv/bin/activate ,then install flask in the virtual environment,YOU MAY SKIP THIS INSTRUCTION)

            1) Flask installation without venv:
            
                pip install Flask (may require administrator privileges)

            For a proper installation follow: 
            http://flask.pocoo.org/docs/1.0/installation/

            2) mysql-connector installation:

            python -m pip install mysql-connector or
            pip install mysql-connector
    
    2.MySQL Workbench and MariaDB

-Unzip 3rd_deliverable.zip 

-Running the web app
    -import the file dbdump.sql in  MySQL workbench, then start the database server
    - cd to FlaskApp folder 
    
NOTE: (if a venv folder exists run . venv/bin/activate(linux default terminal , if you are using a different shell  e.g. fish  then type . venv/bin/activate.fish ) venv\Scripts\activate (windows), YOU MAY SKIP THIS INSTRUCTION)

    - open app.py (FlaskApp folder) in a text editor and change the following variables according to your MySQL setup:

        # change the name of your database
        dbName=

        # change the username of your database
        dbUser=

        #change the password of your database
        dbPwd=

    - type python app.py;flask run
        or > python app.py 
           > flask run
    - open your browser and navigate to localhost:5000    address

-Use case scenarios
    1) Search species reproduction card inputs
        Reproduction Period Start: JUN
        Reproduction Period Start: AUG
    2) Search species weight card inputs
        Species gender: MALE 
        Max Weight: 10
    3) Search user programs inputs
        Program Due Day: 7
        Program Due Month: 10
        Program Due Year: 2021
    4) sign in as admin inputs
        click the sign in button
        in the modal window input:
	    *** Admin ***
            Username: Administropoulos
            Password: admin1
	    
	    *** Volunteer ***
            Username: Ethelontidis
            Password: ethelontis1	    


Note: in case of an error it is recommended to clean your browser's cache,then run the app again.

The MySQL Workbench model file is provided as well (birdwatching_db.mwb) .

Mozilla Firefox is recommended for better support.
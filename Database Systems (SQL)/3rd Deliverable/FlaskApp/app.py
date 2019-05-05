from flask import Flask,render_template,request,session
import mysql.connector

users=[['Administropoulos','admin1','admin'],['Ethelontidis','ethelontis1','volunteer']]

# change the name of your database
dbName='mydb'
# change the username of your database
dbUser='root'
#change the password of your database
dbPwd='root'

app=Flask(__name__)
app.secret_key='key'

@app.route("/")
def main():
	return render_template("index_en.html")

@app.route("/index_en")
def index_en():
	return render_template("index_en.html")

@app.route("/contact_en")
def contact_en():
	return render_template("contact_en.html")

@app.route("/about_en")
def about_en():
	return render_template("about_en.html")

@app.route("/index_el")
def index_el():
	return render_template("index_el.html")

@app.route("/contact_el")
def contact_el():
	return render_template("contact_el.html")

@app.route("/about_el")
def about_el():
	return render_template("about_el.html")

@app.route("/result1",methods=['GET','POST'])
def result1():
	if request.method=='POST':
		result=request.form
		db=mysql.connector.connect(
			host="localhost",
			database=dbName,
			user=dbUser,
			passwd=dbPwd,
		)
		cur=db.cursor()
		cur.execute("SELECT registry_id,species_name FROM SPECIES WHERE (reproduction_period_start ='"+result['rep_start']+"' AND reproduction_period_end ='"+result['rep_end'] +"') OR (reproduction_period_start ='"+result['rep_start'] +"' AND reproduction_period_end = '"+result['rep_end'] +"') OR (reproduction_period_start ='"+result['rep_start'] +"' AND reproduction_period_end ='"+result['rep_end']+"')" )
		myresult=cur.fetchall()
		db.close()
		return render_template('result1.html',result=[myresult,[result['rep_start'],result['rep_end']],len(myresult)%2])

@app.route("/result2",methods=['GET','POST'])
def result2():
	if request.method=='POST':
		result=request.form
		db=mysql.connector.connect(
			host="localhost",
			database=dbName,
			user=dbUser,
			passwd=dbPwd,
		)
		cur=db.cursor()
		cur.execute("SELECT species_name, weight FROM characteristics JOIN species ON species.registry_id=characteristics.registry_id WHERE characteristics.gender='"+result['gender']+"' AND characteristics.weight<"+result['max_weight'] )
		myresult=cur.fetchall()
		db.close()
		return render_template('result2.html',result=[myresult,[result['gender'],result['max_weight']],len(myresult)%2])

@app.route("/result3",methods=['GET','POST'])
def result3():
	if request.method=='POST':
		result=request.form
		db=mysql.connector.connect(
			host="localhost",
			database=dbName,
			user=dbUser,
			passwd=dbPwd,
		)
		cur=db.cursor()
		cur.execute("SELECT user_name , area, program.description_code FROM program JOIN user_program ON program.description_code = user_program.description_code WHERE program.day="+result['day']+" AND program.month="+result['month']+" AND program.year="+result['year'] )
		myresult=cur.fetchall()
		db.close()
		return render_template('result3.html',result=[myresult,[result['day'],result['month'],result['year']],len(myresult)%2])


@app.route("/signin",methods=['GET','POST'])
def signin():
	if request.method=='POST':
		result=request.form
		for user in users:
			if (result['uname']==user[0] and result['pwd']==user[1] ):
				session['username']=user[0]
				return render_template('index_en.html')

@app.route("/signout",methods=['GET','POST'])
def signout():
	if request.method=='POST':
		result=request.form
		session.clear()
		return render_template('index_en.html')


if __name__=="__main()__":
	app.run(debug=TRUE)


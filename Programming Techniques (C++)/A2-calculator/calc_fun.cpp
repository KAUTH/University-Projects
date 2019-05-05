void add(float a,float b)
{
	float result;
	result=a+b;
	cout << result;
}

void mult(float a,float b)
{
	float result;
	result=a*b;
	cout << result;
}

void add(float a,float b,float c,float d)
{
	float r_result,img_result;
	r_result=a+c;
	img_result=b+d;
	cout << r_result << "+" << img_result << "i\n";			//ektupwnei ton migadiko se morfi a+bi
}

void mult(float a,float b,float c,float d)
{
	float r_result,img_result;
	r_result=a*c-b*d;
	img_result=a*d+c*b;
	cout << r_result << "+" << img_result << "i\n";
}

void div(float a,float b,float c,float d)
{
	float r_result,img_result;
	r_result=(a*c+b*d)/(c*c+d*d);
	img_result=(b*c-a*d)/(c*c+d*d);
	cout << r_result << "+" << img_result << "i\n";
}

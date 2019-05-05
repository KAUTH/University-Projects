import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

import ithakimodem.*;

/*
*
* Δίκτυα Υπολογιστών I
*
* Experimental Virtual Lab
*
* Παπαδόπουλος Κωνσταντίνος, Α.Ε.Μ. 8677
*
*/
public class virtualModem {
	
	
/////////////////////////////////////////// initializing values


String echo="E7281\r";	// Echo request code

String imgfree="M4464\r";	// Image request code (Tx/Rx error free)

String imgerror="G5996\r";	// Image request code (Tx/Rx with errors)

String gps="P2812R=1000099\r";	// GPS request code 

String gps2="P2812R=1011099\r";	// GPS request code 2

String gps_plain="P2812";  

String ack="Q9734\r";	// ACK result code 

String nack="R9531\r";	// NACK result code 
 

///////////////////////////////////////////



	
	
 public static void main(String[] param) {		
 //(new virtualModem()).nechopackets(5);
 //(new virtualModem()).imagepackets("error");
//(new virtualModem()).chronoechopackets(480000);
//(new virtualModem()).gpspackets();
 //(new virtualModem()).acknackpackets(480000);
 }
 
 ////////////////////////////////////////////////// // creates and initializes modem
 public Modem setmodem(int speed, int timeout){  
	 Modem modem;
	 modem=new Modem();
	 modem.setSpeed(speed);            // set new speed at 8000 (old speed was at 1000)
	 modem.setTimeout(timeout);
	 return modem;
 }
 
 //////////////////////////////////////////////////
 /////////////////////////////////////////////////// creates files for input data
 public FileOutputStream makefile(String filename, String extension){
	
	 FileOutputStream fout = null;
	 
	 try {
		fout = new FileOutputStream(filename
				+ ""
				+ extension);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 	 
	 return fout;
 }
 
 
 public FileOutputStream makefileappend(String filename, String extension){	// creates appended files for input data
		
	 FileOutputStream fout = null;
	 
	 try {
		fout = new FileOutputStream(filename
				+ ""
				+ extension, true);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 	 
	 return fout;
 }
 ///////////////////////////////////////////////////
 
 public void nechopackets(int number) {		// for requesting n number of echo packets
 int k;
 Modem modem = setmodem(10000, 2000); // creates and initializes modem

 FileOutputStream fout = makefile("necho", ".txt");	// opening file to store input data

  
 modem.open("ithaki");

 for ( ; ; ) {       //// it's for emptying the initial message
	try {
	k=modem.read();
	if (k==-1){ break;}
 
		System.out.print((char)k);
 
	} catch (Exception x) {
		break;
 
	}
 
 }
//////////////////////////////////

 for (int i = 0; i<number; i++){	
	
	modem.write(echo.getBytes()); 

	for ( ; ; ) {       //// it's for reading the characters
	
		try {
	 
			k=modem.read();
			if (k==-1){fout.write("\r\n".getBytes()); break;}
 
			System.out.print((char)k);
 
			fout.write(k);
 
		} catch (Exception x) {
			break;
 
		}
 
	}
 
 }
modem.close();

}// end of nechopackets
 
 
 
 
 
 /////////////////////////////////////////////////////////////////////////////////
 
 public void imagepackets(String quality) {		// error or free
	int k;
	Modem modem = setmodem(10000, 2000); // creates and initializes modem

	FileOutputStream fout = makefile("image", ".jpeg");	// opening file to store input data

	String img; 
	 
	if (quality == "error")
		img = imgerror;
	else img = imgfree;
	 
	modem.open("ithaki");
	
	for ( ; ; ) {       //// it's for emptying the initial message
		try {
		k=modem.read();
		if (k==-1){ break;}
	 
			System.out.print((char)k);
	 
		} catch (Exception x) {
			break;
	 
		}
	 
	}
	
	for (int i = 0; i<1; i++){	
		
		modem.write(img.getBytes()); 

		for ( ; ; ) {       //// it's for reading the characters
		
			try {
		 
				k=modem.read();
				if (k==-1){fout.write("\r\n".getBytes()); break;}
	 
				System.out.print((char)k);
	 
				fout.write(k);
	 
			} catch (Exception x) {
				break;
	 
			}
	 
		}
	 
	 }
	modem.close();
 }// end of imagepackets
 
 
  
 
 public void acknackpackets(long acktime) {
	 int k; 
	 Modem modem = setmodem(10000, 2000); // creates and initializes modem
	 FileOutputStream fout = makefile("ack", ".txt");	// opening file to store input data
	 modem.open("ithaki");
	 
	 for ( ; ; ) {       //// it's for emptying the initial message
		try {
		k=modem.read();
		if (k==-1){ break;}
			System.out.print((char)k);
		} catch (Exception x) {
			break;
		}
	 }
	//////////////////////////////////
	 String[] fcs;	// it is the FCS string in the packet
	 int[] fcsint = new int[1000];	// it is the FCS string in the packet converted to int
	 int[] fcsxor = new int[1000];	// it is the FCS in decimal that comes from the XOR between the characters
	 byte[][] fcsxorbyte = new byte[1000][1000];	// it turns every sequence into bytes
	 byte[] fcsxorbinary = new byte[1000];	// the outcome of every packet that is XORed that needs to become from binary to decimal
	 int whilecounter = 0;	// how many times in the while loop
	 int kmscounter = 0;	// how many times k==-1
	 int[] nackpackets = new int[1000]; // for every ack requests how many nack there are
	 for(int i = 0; i <1000; i++){
		 nackpackets[i] = 0;
	 }
	 int requests = 0;	//number of ack requests 
	 long[] responsetime = new long[1000];	// measures response time
	 responsetime[0] = 0;
	 long[] tmp = new long[1000];	// counts from ack to ack
	 long tmp2 = 0;	// counts inside nack
	 
	 String[] sequence = new String[1000];	// the string sequence of every packet
	 int ackresponseflag = 0;				// flag to measure time between two ack packets
	 
	 long[][] tmpn = new long[1000][1000];		//--new
	 long[] responsetimen = new long[1000];	// measures response time //--new
	 responsetimen[0] = 0; //--new
	 long[] responsetimetotal = new long[1000]; //--new
	 
	 long start= System.currentTimeMillis();
	 long end = start + acktime;
	 
	 while (System.currentTimeMillis() < end){	

			if ( fcsxor[kmscounter] != fcsint[kmscounter] ){				// result -> ack or nack
				tmpn[requests][nackpackets[requests]] = System.currentTimeMillis(); //--new counts nack time to receive as well
				modem.write(nack.getBytes());
				ackresponseflag = 0;
				nackpackets[requests]++;				
			}
			else{
				tmp[requests] = System.currentTimeMillis();
				modem.write(ack.getBytes());
				ackresponseflag = 1;
				requests++;
			}
			
			
			for ( ; ; ) {       //// it's for reading the characters
				
				try {
			 		k=modem.read();		
					System.out.print((char)k);
					 				
					if (k==-1){
						
						fout.write("\r\n".getBytes()); 
						
						if (ackresponseflag == 0) //--new counts nack time to receive as well
							responsetimen[requests] += System.currentTimeMillis() - tmpn[requests][nackpackets[requests]-1]; //--new
						
						if (ackresponseflag == 1)
							responsetime[requests] = System.currentTimeMillis() - tmp[requests-1];
						
						responsetimetotal[requests] = responsetimen[requests] + responsetime[requests]; //--new
																
						////////////////////////////////////////////////////////////////////////					
						kmscounter++;
						break;
					} // end of if k==-1
									
					fout.write(k);
					
				} catch (Exception x) {
					break;
				}
			}	// end of infinite for loop
				
			
			BufferedReader in = null;										// opens reader for the file to be scanned
			try {
				in = new BufferedReader(new FileReader("ack.txt"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String str;

			ArrayList list = new ArrayList();								// puts every line of the file into list
			try {
				while((str = in.readLine()) != null){
				    list.add(str);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			String[] stockArr = new String[list.size()];				// converts list into String array
			String[] stringArr = (String[]) list.toArray(stockArr);
			
			
			String temp = "";
			for(int y = 49; y <=51 ; y++){
				
				temp += Character.toString(stringArr[kmscounter-1].charAt(y));																
			}
			
			//System.out.print("---fcsint---" + temp + "\n");
			
			fcsint[kmscounter] = Integer.parseInt(temp);	// gets integer value of fcs
			
			temp ="";
			for(int y = 31; y <=46 ; y++){
				
				temp += Character.toString(stringArr[kmscounter-1].charAt(y));																
			}
			sequence[kmscounter] = temp;
			
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
		    fcsxorbyte[kmscounter] = sequence[kmscounter].getBytes();	// turns every sequence into bytes
		    byte tmpbyte = fcsxorbyte[kmscounter][0];	// initializes tmpbyte
			
			for(int y = 0; y<15 ; y++){	// do xor to all characters
				tmpbyte = (byte)(tmpbyte^fcsxorbyte[kmscounter][y+1]);
				fcsxorbinary[kmscounter] = tmpbyte;
				//System.out.print("--sequence--" + kmscounter + " is " + (int)fcsxorbinary[kmscounter]  + "\n");
			}
			
			fcsxor[kmscounter] = (int) fcsxorbinary[kmscounter];
			
			//System.out.print("--THE fcsxor[kmscounter]-- " + fcsxor[kmscounter] + "\n");
			//System.out.print("--fcsxorbyte[kmscounter][0]--" + fcsxorbyte[kmscounter][0]  + "\n");
			//System.out.print("--fcsxorbyte[kmscounter][1]--" + fcsxorbyte[kmscounter][1]  + "\n");
			//byte test = (byte)(fcsxorbyte[kmscounter][2]^((byte) (fcsxorbyte[kmscounter][0]^fcsxorbyte[kmscounter][0+1])));
			//System.out.print("--test--" + test  + "\n");
			//System.out.print("--final--" + fcsxor[kmscounter]  + "\n");
 
			whilecounter++;
	 } // end of while
	 
	 
	 modem.close();
	 
	 	 
	 String[] str = new String[requests];
		for (int y=1;y<requests;y++){	// write input data of response times into file, start from 1 (0+1)
										// because if you send n ack packets there are n-1 response times
			//str[y] = Long.toString(responsetime[y]); //--new bring back
			str[y] = Long.toString(responsetimetotal[y]); //--new
			
			try (FileWriter out = new FileWriter("response_ack_nack.txt",true))
			{		    
			    out.write(str[y]);  
			    out.write("\r\n");
			}
			catch (IOException exc)
			{
			    System.out.println("Exception ");

			}
		
		}
		
		System.out.print("\n"+"--NUMBER OF REQUESTS (ACK PACKETS)--" + requests  + "\n");
		for(int i=0; i<requests; i++)
			System.out.print("--NUMBER OF NACK (NACK PACKETS)--" + (i+1) + "  " + nackpackets[i]  + "\n");
  
 } // end of acknackpackets2
 
 
 
 public void chronoechopackets(long echotime) {
	int k;
	 
	String[] str = new String[200];	// to save response times as strings
	long[] responsetime = new long[200];	// to save response times
	 
	Modem modem = setmodem(10000, 2000); // creates and initializes modem

	FileOutputStream fout = makefile("chronoecho", ".txt");	// opening file to store input data
	
	int t = 0;
	 
	modem.open("ithaki");

	for ( ; ; ) {       //// it's for emptying the initial message
		try {
		k=modem.read();
		if (k==-1){ break;}
	 
			System.out.print((char)k);
	 
		} catch (Exception x) {
			break;
	 
		}
	 
	}
	//////////////////////////////////
	
	long start= System.currentTimeMillis();
	long end = start + echotime;
	 
	long timesent;

	while (System.currentTimeMillis() < end){	
		
		timesent = System.currentTimeMillis(); // start of measuring response time
		modem.write(echo.getBytes()); 
		
		for ( ; ; ) {       //// it's for reading the characters
		
			try {
		        
				k=modem.read();	// while k!=0
				
				if (k==-1){
					responsetime[t] = System.currentTimeMillis() - timesent; // end of measuring response time
					fout.write("\r\n".getBytes()); 
					
					t++;
					
					break;}
	 
				System.out.print((char)k);
	 
				fout.write(k);
	 
			} catch (Exception x) {
				break;	 
			}
		}
	 }
	modem.close();
	
	
	for (int y=0;y<t;y++){	// write input data of response times into file
		
		str[y] = Long.toString(responsetime[y]);
		
		try (FileWriter out = new FileWriter("echoresponse.txt",true))
		{
		    out.write(str[y]);  
		    out.write("\r\n");
		}
		catch (IOException exc)
		{
		    System.out.println("Exception ");
		}
		
				
		
	}
	
 }// end of chronoechopackets2
 
 
 
 
 public void gpspackets() {
	
	int k;
	
	Modem modem = setmodem(10000, 2000); // creates and initializes modem

	FileOutputStream fout = makefile("gps", ".txt");	// opening file to store input data
   
	 
	int l = 0; // lines of N,E arrays, increases every time we find a packet with the correct format
	 
	modem.open("ithaki");

	for ( ; ; ) {       //// it's for emptying the initial message
		try {
		k=modem.read();
		if (k==-1){ break;}
	 
			System.out.print((char)k);
	 
		} catch (Exception x) {
			break;
	 
		}
	 
	}
	//////////////////////////////////

	for (int i = 0; i<1; i++){	
					
			modem.write(gps.getBytes()); 
			modem.write(gps2.getBytes()); // two different gps routes for the data files

			for ( ; ; ) {       //// it's for reading the characters
			
				try {
			 
					k=modem.read();
					if (k==-1){fout.write("\r\n".getBytes()); break;}
		 
					System.out.print((char)k);
		 
					fout.write(k);
		 
				} catch (Exception x) {
					break;
		 
				}
		 
			}
		
	 
	 }
	modem.close();
		
	//blankremover("gpstest.txt");
	
	///////////////////////////////////////////////////////////////this part scans for the coordinates
	
	BufferedReader in = null;										// opens reader for the file to be scanned
	try {
		in = new BufferedReader(new FileReader("gps.txt"));
	} catch (FileNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	String str;

	ArrayList list = new ArrayList();								// puts every line of the file into list
	try {
		while((str = in.readLine()) != null){
		    list.add(str);
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	String[] stockArr = new String[list.size()];				// converts list into String array
	String[] stringArr = (String[]) list.toArray(stockArr);
	
	
	int numtraces=0;	// traces that have 4" difference
	
	
	String[] North = new String[1000];							//initialize Strings for North and South coordinates
	for(int i = 0; i < 1000; i++){
		North[i] = "";
	  }
	
	
	String[] East = new String[1000];
	for(int i = 0; i < 1000; i++){
		East[i] = "";
	  }
	
	String[] Northconvert = new String[198];	//initialize Strings for North and South coordinates to be converted
	for(int i = 0; i < 198; i++){
		Northconvert[i] = "";
	  }
	
	String[] Eastconvert = new String[198];  // 198 because that's the lines of the gps file
	for(int i = 0; i < 198; i++){
		Eastconvert[i] = "";
	  }
	
	
	/////////////////////////////////////////////////////// initialize Strings for Hours, Minutes, Seconds
	String[] Hours = new String[1000];					//initialize Strings for North and South coordinates
	for(int i = 0; i < 1000; i++){
		Hours[i] = "0";
	  }
	String[] Minutes = new String[1000];				//initialize Strings for North and South coordinates
	for(int i = 0; i < 1000; i++){
		Minutes[i] = "0";
	  }
	String[] Seconds = new String[1000];				//initialize Strings for North and South coordinates
	for(int i = 0; i < 1000; i++){
		Seconds[i] = "0";
	  }
	
	int[] conversioneast= new int[1000];	// converts to " of degrees
	int[] conversionnorth= new int[1000];	// converts to " of degrees
	
	///////////////////////////////////////////////////////
	String[] trace = new String[6];  // the final traces 
	
	for (int i = 1; i<(stringArr.length-2); i++){
		
		
		if (stringArr[i].charAt(4) == 'G' ){	// checking for correct gps format
			for(int y = 18; y<=21 ; y++){
				
				North[l] += Character.toString(stringArr[i].charAt(y));
				
			}
			for(int y = 23; y<=24 ; y++){
				
				Northconvert[l] += Character.toString(stringArr[i].charAt(y));
				
			}			
			
			
			for(int y = 31; y<=34 ; y++ ){
				
				East[l] += Character.toString(stringArr[i].charAt(y));
			}
			for(int y = 36; y<=37; y++){
				
				Eastconvert[l] += Character.toString(stringArr[i].charAt(y));
			}
			
					
			////////////////////////////////////////get time
			
			for(int y = 7; y<=8 ; y++){		// hours
				Hours[l] += Character.toString(stringArr[i].charAt(y));	
			}
			
			for(int y = 9; y<=10 ; y++){		// minutes
				Minutes[l] += Character.toString(stringArr[i].charAt(y));	
			}
					
			for(int y = 10; y<=11 ; y++){		// seconds
				Seconds[l] += Character.toString(stringArr[i].charAt(y));	
			}
									
			l++;
			
		}
			
	}
	
	/////////////////////////////////////////////////////////// conversions
	for (int y = 0; y < Northconvert.length; y++){
	conversionnorth[y] = (int) (0.6 * Integer.valueOf(Northconvert[y]));	// converts to "
	Northconvert[y] = String.valueOf(conversionnorth[y]);					// converts to String again
	North[y] += Northconvert[y];											// re-attaches to String
	
	conversioneast[y] = (int) (0.6 * Integer.valueOf(Eastconvert[y]));
	Eastconvert[y] = String.valueOf(conversioneast[y]);
	East[y] += Eastconvert[y];
	}
	
	
//////////////////////////////////////////// making arrays for reading time
	
	int[] hoursint = new int[Hours.length];
	int[] minutesint = new int[Minutes.length];
	int[] secondsint = new int[Seconds.length];
	int[] timeint = new int[Hours.length];
	
	for (int y = 0; y < Hours.length; y++){
		hoursint[y] = 3600 * Integer.valueOf(Hours[y]);
		minutesint[y] = 60 * Integer.valueOf(Minutes[y]);
		secondsint[y] = 1 * Integer.valueOf(Seconds[y]);
		timeint[y] = hoursint[y] + minutesint[y] + secondsint[y];  
	}
	
	
	////////////////////////////////////////////////////////
	int z;							// trying to find traces that are 4" away, the numbers of z and y are random
	for (z = 20; z < l; z++){
		for(int y= z + 100; y<l;y=y+30, z += 50){
			if ((Math.abs(timeint[z] - timeint[y])) >= 50
			    && numtraces!=4){
				   trace[numtraces] =  East[z] + North[z];
				   trace[numtraces+1] = East[y] + North[y];
				   numtraces=numtraces+2;
				   System.out.print(z  + "\n");
			   }
		}
		
	}
	
	/*
	trace[0] =  East[0] + North[0];			// since the time in the data is increasing by 1 sec in every packet
	trace[1] =  East[50] + North[50];		// the traces are definitely 4" away (50 secs to be exact)
	trace[2] =  East[100] + North[100];
	trace[3] =  East[150] + North[150];
	*/
	
	
/////////////////////////////////////////////////////////////////////////new image
	
	String newgpscode = new String();
	
	newgpscode = gps_plain + "T=" + trace[0] +
						     "T=" + trace[1] +
						     "T=" + trace[2] +
						     "T=" + trace[3] + "\r";
	
	System.out.print("THIS IS THE NEW GPS CODE " + newgpscode +"\n");
	
		
	int k2;
	Modem modem2 = setmodem(10000, 2000); // creates and initializes modem

	FileOutputStream fout2 = makefile("gps", ".jpeg");	// opening file to store input data

	 	
	modem2.open("ithaki");
	
	for ( ; ; ) {       //// it's for emptying the initial message
		try {
		k2=modem2.read();
		if (k2==-1){ break;}
	 
			System.out.print((char)k2);
	 
		} catch (Exception x) {
			break;
	 
		}
	 
	}
	
	for (int i = 0; i<1; i++){	
		
		modem2.write(newgpscode.getBytes()); 

		for ( ; ; ) {       //// it's for reading the characters
		
			try {
		 
				k2=modem2.read();
				if (k2==-1){fout2.write("\r\n".getBytes()); break;}
	 
				System.out.print((char)k2);
	 
				fout2.write(k2);
	 
			} catch (Exception x) {
				break;
	 
			}
	 
		}
	
	 }
	modem2.close();
	
	

 }// end of gpspackets
 
 
 
 
 
 //////////////////removes empty lines from files	(it isn't used eventually)
 
 public void blankremover(String args) {

     Scanner file;
     PrintWriter writer;

     try {

         file = new Scanner(new File(args));
         writer = new PrintWriter("gpstest100.txt");

         while (file.hasNext()) {
             String line = file.nextLine();
             if (!line.isEmpty()) {
                 writer.write(line);
                 writer.write("\n");
             }
         }

         file.close();
         writer.close();

     } catch (FileNotFoundException ex) {
         //Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
    	 
     }     
     
}	// end of blankremover
 
 
 
 

 
}	// end of class
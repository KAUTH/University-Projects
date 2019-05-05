package diktya2new;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
//import java.io.*;
//import java.net.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;


/*
*
* Δίκτυα Υπολογιστών IΙ
*
* Student: Papadopoulos Konstantinos, A.E.M. 8677
*
* Experimental Virtual Lab
*
* Java Application
*
*/


public class javaApplication {
	
/////////////////////////////////////////// initializing values
	int clientPort = 48022;				// Client listening port - 48038 for the Ithakicopter
	int serverPort = 38022;				// Server listening port - 38048 for the Ithakicopter
	
	String echoCode = "E0000";								// Echo request code numbers
	String echo = "echo_request_code=" + echoCode + "T00"; 	// Echo request code
	
	String imageCode = "M7584";												//Image request code numbers
	String image = "image_request_code=" + imageCode + "UDP=1024CAM=PTZ";	//Image request code (CAM=PTZ or CAM=FIX)
	
	String audioCode = "A4716";												// Audio request code numbers
	String audio = "audio_request_code" + audioCode + /*"=L07" +*/ "=";		// Audio request code
	
	String copterCode = "Q6910";								// Ithakicopter code numbers
	String copter = "Ithakicopter_code=" + copterCode;			// Ithakicopter code
	
	String vehicleCode = "V4015";								// Vehicle OBD-II code numbers
	String vehicle = "Vehicle_OBD-II_code=" + vehicleCode;		// Vehicle OBD-II code	

/////////////////////////////////////////// MAIN FUNCTION (choose one of the below functions in main())	
	public static void main(String[] args) {
		//(new javaApplication()).nechopackets(100);
		//(new javaApplication()).chronoechopackets(300000);
		(new javaApplication()).imagepackets();
		//(new javaApplication()).vehicleDiagnostics("01", "05", 270000);
		//(new javaApplication()).ithakicopter(120000);
		//(new javaApplication()).audiopackets(8, 8192, 900, "F", "DPCM");
	}
	
//////////////////////////////////////////// creates files for input data
	public FileOutputStream makefile(String filename, String extension){

		FileOutputStream fout = null;

		try {
			fout = new FileOutputStream(filename
					+ ""
					+ extension);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fout;
	} // end of makefile() function
	
	 public FileOutputStream makefileappend(String filename, String extension){	// creates appended files for input data
			
		 FileOutputStream fout = null;
		 
		 try {
			fout = new FileOutputStream(filename
					+ ""
					+ extension, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		 	 
		 return fout;
	 } // end of makefileappend() function
	
	
	 public void packetTransmit(String packetInfo, int serverPort) {	// prepares socket and packet for transmission 
		DatagramSocket s = null;
		try {
			s = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		byte[] txbuffer = packetInfo.getBytes();
	
		byte[] hostIP = {(byte)155, (byte)207, (byte)18, (byte)208};	// IP address of Ithaki
	
		InetAddress hostAddress = null;
		try {
			hostAddress = InetAddress.getByAddress(hostIP);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	
		DatagramPacket p = new DatagramPacket(txbuffer, txbuffer.length, hostAddress, serverPort);
	
		try {
			s.send(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end of packetTransmit() function
	 
	 public int hex2decimal(String s) {	// turns hexadecimal string into decimal integer
		 String digits = "0123456789ABCDEF";
         s = s.toUpperCase();
         int val = 0;
         for (int i = 0; i < s.length(); i++) {
             char c = s.charAt(i);
             int d = digits.indexOf(c);
             val = (int) (val + d*(Math.pow(16,i)));
         }
         return val;
	 } // end of hex2decimal() function
	
/////////////////////////////////////////////////// Basic functions for the application
	
	public void nechopackets(int number) {	// sends n number of echo packets
											// gets answers of echo packets and temperatures from telemetry stations
	
		FileOutputStream fout = makefile("necho_2", ".txt");	// opening file to store input data
	
		DatagramSocket r = null;
		try {
			r = new DatagramSocket(clientPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		try {
			r.setSoTimeout(5000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		byte[] rxbuffer = new byte[2048];
	
		DatagramPacket q = new DatagramPacket(rxbuffer, rxbuffer.length);
	
		for(int stopper=0 ; stopper < number ; ){	// "stopper" is a flag to trigger stopping the transmission of packets
			packetTransmit(echo, serverPort);
			stopper++;	// stops when it transmits "number" amount of packets
			try {
				r.receive(q);
				// stopper++;	if placed here would stop when it receives "number" packets
				String message = new String(rxbuffer, 0, q.getLength());
				System.out.println(message);
				fout.write((message + "\r\n").getBytes());	// write message into file (\r\n places newline)
			} catch (Exception x) {
				System.out.println(x);
			}
		}
		
	} // end of nechopackets(int number) function
	

///////////////////////////////////////////////////	// gets response times of echo packets
	public void chronoechopackets(long echotime) {	// sends packets for "echotime" ms
		FileOutputStream fout = makefile("echopacket_2", ".txt");	// opening file to store input data
		
		String[] str = new String[20000];			// to save response times as strings
		long[] responsetime = new long[20000];	// to save response times
		
		int t = 0;								// index of responsetime table
		
		long start= System.currentTimeMillis();
		long end = start + echotime;	 
		long timesent;
		
		DatagramSocket r = null;
		try {
			r = new DatagramSocket(clientPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		try {
			r.setSoTimeout(5000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		byte[] rxbuffer = new byte[2048];
	
		DatagramPacket q = new DatagramPacket(rxbuffer, rxbuffer.length);
		
		while (System.currentTimeMillis() < end){
			timesent = System.currentTimeMillis(); // start of measuring response time
			packetTransmit(echo, serverPort);
			
			try {
				r.receive(q);
				responsetime[t] = System.currentTimeMillis() - timesent; // end of measuring response time
				
				String message = new String(rxbuffer, 0, q.getLength());
				System.out.println(message);
				
				fout.write((message + "\r\n").getBytes());	// write message into file (\r\n places newline)
				t++;
			} catch (Exception x) {
				System.out.println(x);
			}
			
		}
		
		for (int y=0;y<t;y++){	// write input data of response times into file
			
			str[y] = Long.toString(responsetime[y]);
			
			try (FileWriter out = new FileWriter("echoresponse_2.txt",true))
			{
			    out.write(str[y]);  
			    out.write("\r\n");
			}
			catch (IOException exc)
			{
			    System.out.println("Exception ");
			}	
		}
			
	} // end of chronoechopackets() function
	
	
///////////////////////////////////////////////////	 gets images from the two requested cameras
	public void imagepackets() {
		FileOutputStream fout = makefile("imagepacket_2", ".jpeg");	// opening file to store input data
		
		DatagramSocket r = null;
		try {
			r = new DatagramSocket(clientPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		try {
			r.setSoTimeout(800);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		byte[] rxbuffer = new byte[1024];	// buffer size set to match length of packets described in the image request code
	
		DatagramPacket q = new DatagramPacket(rxbuffer, rxbuffer.length);
			
		packetTransmit(image, serverPort);
		
		String message = null;
		for(int stopper = 0 ; stopper !=1 ; ) {
			try {
				r.receive(q);
				message = new String(rxbuffer, 0, q.getLength());
				System.out.println(message);
				for(int i = 0; i < rxbuffer.length;i++){
					fout.write((int)rxbuffer[i]);	
				}
			} catch (Exception x) {
				System.out.println(x);
				stopper = 1;			// could have just used break and not the "stopper" flag
			}
		}
		
	} // end of imagepackets() function 
	
	
///////////////////////////////////////////////////	gets diagnostics from vehicle experiment
	public void vehicleDiagnostics(String mode, String pid, long time) {
		FileOutputStream fout = makefile("vehiclehex_2", ".txt");	// opening file to store input data
		FileOutputStream fout1 = makefile("vehicledec_2", ".txt");	// opening file to store input data
		
		long start= System.currentTimeMillis();
		long end = start + time;	
		
		DatagramSocket r = null;
		try {
			r = new DatagramSocket(clientPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		try {
			r.setSoTimeout(1500);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		byte[] rxbuffer = new byte[11];
	
		DatagramPacket q = new DatagramPacket(rxbuffer, rxbuffer.length);
		
		String stringvalue1 = null;		// the XX string value
		String stringvalue2 = null;		// the YY string value
		int decvalue1 = 0;				// the XX integer value
		int decvalue2 = 0;				// the YY integer value
		int formula;					// the formula that computes the real measurement values
		String description = null;		// description of the diagnostics
			
		while (System.currentTimeMillis() < end){
			packetTransmit(vehicle + "OBD=" + mode + " " + pid, serverPort);
			
			try {
				r.receive(q);
				
				String message = new String(rxbuffer, 0, q.getLength());
				
				stringvalue1 = Character.toString(message.charAt(6)) + Character.toString(message.charAt(7));		
				decvalue1 = hex2decimal(stringvalue1);
				
				// chooses specific case according to given MODE and PID		
				switch(pid) {
					case "1F":
						stringvalue2 = Character.toString(message.charAt(9)) + Character.toString(message.charAt(10));
						decvalue2 = hex2decimal(stringvalue2);
						formula = 256*decvalue1 + decvalue2;
						description = "Engine run time (sec):" + formula; 
						break;
					case "0F":
						formula = decvalue1 - 40;
						description = "Intake air temperature (deg C):" + formula; 
						break;
					case "11":
						formula = decvalue1*100/255;
						description = "Throttle position (%):" + formula; 
						break;
					case "0C":
						stringvalue2 = Character.toString(message.charAt(9)) + Character.toString(message.charAt(10));
						decvalue2 = hex2decimal(stringvalue2);
						formula = ((decvalue1*256) + decvalue2)/4;
						description = "Engine RPM (RPM):" + formula; 
						break;
					case "0D":
						formula = decvalue1;
						description = "Vehicle Speed (Km/h):" + formula; 
						break;
					case "05":
						formula = decvalue1 - 40;
						description = "Coolant temperature (deg C):" + formula; 
						break;		
				}
				
				
				System.out.println(message);
				
				fout.write((message + "\r\n").getBytes());		// write message into file (\r\n places newline)
				fout1.write((description + "\r\n").getBytes());	// write message into file (\r\n places newline)
				
			} catch (Exception x) {
				System.out.println(x);
			}
		}
	
	} // end of vehicleDiagnostics() function
	
	
////////////////////////////////////////////// gets telemetry values from the ithakicopter	
	public void ithakicopter(long time) {
		FileOutputStream fout = makefile("ithakicopter_2", ".txt");			// opening file to store input data
		FileOutputStream fout1 = makefile("ithakicopter_lmotor_2", ".txt");	// opening file to store input data
		FileOutputStream fout2 = makefile("ithakicopter_rmotor_2", ".txt");	// opening file to store input data
		FileOutputStream fout3 = makefile("ithakicopter_altitude_2", ".txt");		// opening file to store input data
		FileOutputStream fout4 = makefile("ithakicopter_temperature_2", ".txt");	// opening file to store input data
		FileOutputStream fout5 = makefile("ithakicopter_pressure_2", ".txt");		// opening file to store input data
		
		long start= System.currentTimeMillis();
		long end = start + time;	 
	
		DatagramSocket r = null;
		try {
			r = new DatagramSocket(48038);		// Client listening port fixed at 48038 
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		try {
			r.setSoTimeout(2000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		byte[] rxbuffer = new byte[10000];
	
		DatagramPacket q = new DatagramPacket(rxbuffer, rxbuffer.length);
		
		while (System.currentTimeMillis() < end){
			packetTransmit(copterCode, 38048);	// Server listening port fixed at 38048 
			
			try {
				r.receive(q);
				
				String message = new String(rxbuffer, 0, q.getLength());
				System.out.println(message);
				
				String lmotor = "";			// String value of left motor
				String rmotor = "";			// String value of right motor
				String altitude = "";		// String value of altitude
				String temperature = "";	// String value of temperature
				String pressure = "";		// String value of pressure

				// gets the values of specific characters of the message to form the real values				
				for(int y = 40; y<=42 ; y++){			
					lmotor += Character.toString(message.charAt(y));
				}
				for(int y = 51; y<=53 ; y++){	
					rmotor += Character.toString(message.charAt(y));
				}
				for(int y = 64; y<=67 ; y++){	
					altitude += Character.toString(message.charAt(y));
				}
				for(int y = 80; y<=86 ; y++){	
					temperature += Character.toString(message.charAt(y));
				}
				for(int y = 96; y<=103 ; y++){	
					pressure += Character.toString(message.charAt(y));
				}
								
				fout1.write((lmotor + "\r\n").getBytes());
				fout2.write((rmotor + "\r\n").getBytes());
				fout3.write((altitude + "\r\n").getBytes());
				fout4.write((temperature + "\r\n").getBytes());
				fout5.write((pressure + "\r\n").getBytes());
				
				fout.write((message + "\r\n").getBytes());	// write message into file (\r\n places newline)
			} catch (Exception x) {
				System.out.println(x);
			}
			
		}
		
	} // end of ithakicopter() funtion
	
//////////////////////////////////////////////gets audio from ithaki
	public void audiopackets(int Q, int sampfreq, int packets, String source, String encoding) {
		// Q is the quantizer (bits/samples), it should be 8 or 10 for DPCM and only 16 in AQ-DPCM
		// sampfreq is the sampling frequency (samples/sec)
		// packets is the number of packets we want the server to send (from 000-999)
		// source: "T" -> virtual frequency generator or "F" -> audio clip
		// encoding is the type of encoding, DPCM or AQ-DPCM
		String strpack = String.valueOf(packets);	// string with the number of packets
		
		FileOutputStream fout = makefile("audio_diffs_2", ".txt");	// opening file to store input data
		FileOutputStream fout1 = makefile("audio_samples_2", ".txt");	// opening file to store input data
		FileOutputStream fout2 = makefile("audio_test_2", ".txt");
		FileOutputStream fout3 = makefile("audio_baq_2", ".txt");
		FileOutputStream fout4 = makefile("audio_m_2", ".txt");
		FileOutputStream fout5 = makefile("audio_meansnew_2", ".txt");
		FileOutputStream fout6 = makefile("audio_bnew_2", ".txt");
		
		/*// other way to calculate b and means
		ArrayList<Integer> means = new ArrayList<Integer>();
		ArrayList<Integer> bs = new ArrayList<Integer>();
		byte[] meanByte = new byte[4];
	    byte[] bByte = new byte[4];
	    byte sign;
	    int mean = 0;
	    //////////////////////////////////////////////////////
		*/
		int enctype = 0; 						// 0 if DMPCM, 1 if AQ-DPCM
		int header = 0;							// 0 if DMPCM, 4 if AQ-DPCM (to start matrix from the correct index)
												// (and to adjust to the new length of matrix)
		int s_elbuffer = 1; 					// multiplying the size of elastic buffer
		
		if (encoding == "AQ-DPCM") {
			enctype = 1;
			header = 4;
			Q = 16;
			s_elbuffer = 2;
		}	
		
		DatagramSocket r = null;
		try {
			r = new DatagramSocket(clientPort);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		try {
			r.setSoTimeout(5000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	
		byte[] rxbuffer = new byte[128+header];
		int[] nibblesamples = new int[256];		// stores the nibble values of bytes from rxbuffer
		int[] demodx = new int [257];			// demodulated packets, decoded packets
		demodx[0] = 0;
		
		byte[][] elbuffer = new byte[packets][s_elbuffer*256];	// it's the elastic buffer, 2D matrix //here * 2
		
		int b = 1;								// b is beta, the quantizer step
		int mask1 = 240;						// 240 decimal = 11110000 binary to get first nibble
		int mask2 = 15;							// 15 decimal = 00001111 binary to get second nibble
		int nibble1 = 0;						// first nibble
		int nibble2 = 0;						// second nibble
		
		int blsb;								// LSB byte of beta
		int bmsb;								// MSM byte of beta
		int baq;								// value of beta in AQ-DPCM
		
		int mlsb;								// LSB byte of m average
		int mmsb;								// MSM byte of m average
		int maq;								// value of m average in AQ-DPCM
	
		DatagramPacket q = new DatagramPacket(rxbuffer, rxbuffer.length);
		
	
		if (enctype == 0) {
			packetTransmit(audio + source + strpack, serverPort);
		}
		else packetTransmit("audio_request_code" + "AQ" + audioCode + "=L07" + "F" + strpack, serverPort);
		
		for(int stopper=0 ; stopper < packets ; stopper++) {	// "stopper" is a flag to trigger stopping the transmission of packets
			
			
			try {
				r.receive(q);
				
				blsb = (int) (rxbuffer[2] & 0xFF);	// chooses the LSB byte of beta from header, &0xFF: to make variable unsigned 
				bmsb = (int) (rxbuffer[3] & 0xFF);	// chooses the MSB byte of beta from header
				baq = bmsb * 256 + blsb;			// 256 because it is 2^8=256
				
				//short int16 = (short)(((rxbuffer[3] & 0xFF) << 8) | (rxbuffer[2] & 0xFF));
				//baq = int16;
				
				mlsb = (int) (rxbuffer[0] & 0xFF);	
				mmsb = (int) (rxbuffer[1]);	
				maq = mmsb * 128 + mlsb;	
				
				/*// other way to calculate b and means
				sign = (byte)( ( rxbuffer[1] & 0x80) !=0 ? 0xff : 0x00);//if rxbuffer[1]&10000000=0 then sign =0 else =01111111 , we take the compliment of this number
		        meanByte[3] = sign;
		        meanByte[2] = sign;
		        meanByte[1] = rxbuffer[1];
		        meanByte[0] = rxbuffer[0];
		        mean = ByteBuffer.wrap(meanByte).order(ByteOrder.LITTLE_ENDIAN).getInt(); //convert the array into integer number using LITTLE_ENDIAN format
		        means.add(mean);
		        
		        sign = (byte)( ( rxbuffer[3] & 0x80) !=0 ? 0xff : 0x00);
		        bByte[3] = sign;
		        bByte[2] = sign;
		        bByte[1] = rxbuffer[3];
		        bByte[0] = rxbuffer[2];
		        b = ByteBuffer.wrap(bByte).order(ByteOrder.LITTLE_ENDIAN).getInt();
		        bs.add(b);
				////////////////////////////////////////////////////////////////////////
				*/
				if (enctype == 1) {
					b = baq;
					fout3.write((baq + "\r\n").getBytes());
					fout4.write((maq + "\r\n").getBytes());
					//fout5.write((means.get(stopper) + "\r\n").getBytes());
					//fout6.write((bs.get(stopper) + "\r\n").getBytes());
				}

				for(int i = 0 + header; i < rxbuffer.length; i++) {
					nibble1 = (byte) ((rxbuffer[i] & mask1) >> 4);			// masking out first nibble and shifting 4 places to the right to have correct 8 bit number
					nibble2 = (byte) (rxbuffer[i] & mask2);					// masking out second nibble
					nibblesamples[i-header] = (int) nibble1;				// storing nibbles into new nibblesamples array
					nibblesamples[i+1-header] = (int) nibble2;
					
					
					
					demodx[i+1-header] = b*(nibblesamples[i-header] - 8) + demodx[i-header];		// we should add maq, which is the mean value (makes little difference if we do not)
					demodx[i+2-header] = b*(nibblesamples[i+1-header] - 8) + demodx[i+1-header];
					
					fout.write(((demodx[i+1-header] - demodx[i-header]) + "\r\n" + 					// saves the differences into a file
								(demodx[i+2-header] - demodx[i+1-header]) + "\r\n").getBytes());
					
					fout1.write((demodx[i+1-header]+ "\r\n" + 					// saves the samples into a file
								 demodx[i+2-header]  + "\r\n").getBytes());
					
					
					//System.out.println("Here are the nibble samples " + (int) nibblesamples[i] + " " + (int) nibblesamples[i+1]);
					//System.out.println("Here are the demodulated packets " + (int) demodx[i+1] + " " + (int) demodx[i+2]);
					
				}
				
				if (enctype == 1) {
					for(int i = 0; i < rxbuffer.length; i++) {
						elbuffer[stopper][i] = (byte) ((demodx[i+1] >> 8) & 0xFF);
						elbuffer[stopper][i+1] = (byte) (demodx[i+1] & 0xFF);
						elbuffer[stopper][i+2] = (byte) ((demodx[i+2] >> 8) & 0xFF);
						elbuffer[stopper][i+3] = (byte) (demodx[i+2] & 0xFF);
					}
				}
				else {
					for(int i = 0; i < rxbuffer.length; i++) {
						elbuffer[stopper][i] = (byte) demodx[i+1];
						elbuffer[stopper][i+1] = (byte) demodx[i+2];				
					}
				}
			
				
			} catch (Exception x) {
				System.out.println(x);
			}
			
		}	// end of for
		
		AudioFormat linearPCM = new AudioFormat(sampfreq, Q, 1, true, false);	
		
		SourceDataLine lineOut = null;
		try {
			lineOut = AudioSystem.getSourceDataLine(linearPCM);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		
		try {
			lineOut.open(linearPCM, s_elbuffer*256*packets);					//here * 2
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		lineOut.start();
		byte[] audioBufferOut = new byte[s_elbuffer*256*packets];			//here * 2
		
		
		for (int j=0; j<packets ; j++) {		
			for (int z = 0; z < s_elbuffer*256; z++){						//here * 2
				audioBufferOut[s_elbuffer*256*j+z] = (byte) elbuffer[j][z];	//here * 2
			}
		}
		
		for(int i = 0; i < audioBufferOut.length; i++) {
			//System.out.println(audioBufferOut[i]);
			try {
				fout2.write((i + "is " + audioBufferOut[i] + "\r\n").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		lineOut.write(audioBufferOut, 0, s_elbuffer*256*packets);	//here * 2
		
		
		lineOut.stop();				
		lineOut.close();
		
		
	} // end of audiopackets() function

} // end of class javaApplication


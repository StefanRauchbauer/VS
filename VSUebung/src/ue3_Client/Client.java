package ue3_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner	scanner =	new	Scanner(System.in);
		try	{	
			Socket	s	=	new	Socket(args[2],	Integer.parseInt(args[3]));	
			
			InputStream	in	=	s.getInputStream();	
			BufferedReader reader	=	new BufferedReader(	new InputStreamReader(in)	);	
			
			OutputStream out				=	s.getOutputStream();	
			PrintWriter		writer	=	new PrintWriter(out);
			
			System.out.println("enter some sht");
			String	eingabe	 =	scanner.nextLine();
			//senden an server
			writer.println(eingabe);
			writer.flush();	
			
			String	eingang	=	reader.readLine();	
			System.out.println(eingang);
				
			
			
}	catch	(IOException e)	{	
			e.printStackTrace();	
}	
	}

}

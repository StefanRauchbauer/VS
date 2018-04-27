package ue3_Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try	(ServerSocket servSocket	=	new ServerSocket(Integer.parseInt(args[2])))	{	
			while(true)	{	
				System.out.println("Waiting for connection");
						//	blockiert	bei	accept()	bis	Client::connect:	
						try	(Socket	s	=	servSocket.accept())	{	
							System.out.println("connected");
									//	Senden	und	Empfangen	über	Streams	und	Reader/Writer		
								InputStream				in					=	s.getInputStream();	
								BufferedReader reader	=	new BufferedReader(new InputStreamReader(in));																			
								OutputStream out				=	s.getOutputStream();	
								PrintWriter		writer	=	new PrintWriter(out);	
								
						//		writer.println("Hallo");	//	oder	print(("Hallo\n");	
						//		writer.flush();	
								String	antwort	=	reader.readLine();	
								System.out.println(antwort);	
								
								writer.println("Vom server " + antwort);	//	oder	print(("Hallo\n");	
								writer.flush();
								
						}	catch	(IOException e)	{		
								e.printStackTrace();	
						}	
			}	
}	catch	(IOException e)	{		
			e.printStackTrace();	
}	

	}

}

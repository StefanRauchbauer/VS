package ue3_Web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Worker implements Runnable {
		
	private Socket	s;

	public Worker(Socket s)
	{
		this.s = s;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String antwort = getInput(); 
		String fileName = cutString(antwort);
		System.out.println(antwort);
		System.out.println(fileName);
		sendAnswer(fileName);
	}
	
	private String getInput()
	{
		
		
		try {
			
			InputStream in = s.getInputStream();
			BufferedReader reader =	new BufferedReader(new InputStreamReader(in));
			String antwort	= reader.readLine();
			return antwort;

			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;				
	}
	private void sendAnswer(String fileName)
	{
		String x="";
		try {
			List<String> zeilen	= Files.readAllLines(Paths.get(fileName));
			x = connectString(zeilen);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
		}
		try {
			OutputStream out = s.getOutputStream();
			PrintWriter		writer	=	new PrintWriter(out);
			writer.println("HTTP/1.1 200 OK\r\nContent-Type: text/html\r\nContent-Length:"+x.length()+"\r\n");
			writer.println("\r\n");
			writer.println(x);
			writer.flush();	
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	private String connectString(List<String> list)
	{
		String gesamtString="";
		for(String s : list)
		{
			gesamtString = gesamtString.concat(s);
		}
		return gesamtString;
		
	}
	
	private String cutString(String s)
	{
		s = s.substring(s.indexOf("/")+1, s.lastIndexOf(" "));
		
		return s;
		
	}

}

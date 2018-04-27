package ue3_Web;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService es;	
		es	=	Executors.newFixedThreadPool(4);
		
		try	(ServerSocket servSocket	=	new ServerSocket(Integer.parseInt(args[0])))	{	
			while(true)	{	
						//	blockiert	bei	accept()	bis	Client::connect:	
						
							Worker w = new Worker(servSocket.accept());
							es.execute(w);
						
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

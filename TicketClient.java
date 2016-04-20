package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//class to handle client ticket requests and connections to server
class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1"; 
	String threadname = "X";
	int portNum; //port that client should try to connect to

	/**
	 * creates a new Thread that attempts to connect to server to handle ticket request
	 * @param hostname 
	 * @param threadname
	 * @param port - port client thread should try to connect to
	 */
	public ThreadedTicketClient(String hostname, String threadname, int port) {
		this.hostname = hostname;
		this.threadname = threadname;
		this.portNum = port;
	}

	public void run() {
		System.out.flush();
		boolean flag = true; //handles failed connection attempts
		while(flag){
    		try {
    		    flag = false;
    			Socket echoSocket = new Socket(hostname, portNum);
    			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); //get the inputStream from the server
    			System.out.println(in.readLine() + " for " + threadname); //print the message that the server sent to client
    			echoSocket.close();
    			
    		} catch (Exception e) {
    			flag = true; //if unable to make connection -> retry
    		}
		}
		
		
	}
}

//class to represent a single client requesting a ticket
public class TicketClient {
	
	ThreadedTicketClient tc;
	String hostName = "";
	String threadName = "";
	

	/**
	 * creates a new client thread to handle the ticket request
	 * @param hostname - name of host
	 * @param threadname - name of thread
	 * @param port - port client thread should try to connect to
	 */
	TicketClient(String hostname, String threadname, int port) {
		tc = new ThreadedTicketClient(hostname, threadname, port);
		hostName = hostname;
		threadName = threadname;
	}
	
	TicketClient(String threadname, int port) {
	    this("localHost", threadname, port);
	}
	
	TicketClient(int port) {
	    this("localHost", "unnamed client", port);
	}
	
	/**
	 * runs the ThreadedTicketClient, which tries to connect to server
	 * in order to handle ticket request
	 */
	void requestTicket() {
		tc.run();
	}

	void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

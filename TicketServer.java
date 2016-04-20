package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketServer {
	//public static int PORT = 2222;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;

	/**
	 * creates and starts a ThreadedTicketServer thread which listens to 
	 * and accepts client connection requests on the specified port
	 * @param port - the port the ThreadedTicketServer should accept connection
	 *               requests from
	 * @param threadName - the name of the ThreadedTicketServer
	 * @throws IOException
	 */
	public static void start(int port, String threadName) throws IOException {	
		Runnable serverThread = new ThreadedTicketServer(port, threadName);
		Thread t = new Thread(serverThread);
		t.start();
		
	}
}

//class that listens to client connection requests and handles them
class ThreadedTicketServer implements Runnable {
	String hostname = "127.0.0.1"; //name of host
	String threadname = "X"; //name of thread
	int portNum; //port number that thread should listen to and accept requests on
	static ConcertHallSeats seatsLeft = new ConcertHallSeats();	//all available seats in the concert hall. 
	                                                            //static since there is only one concert hall, 
	                                                            //even if there are multiple threads accessing it
	
	/**
	 * creates a ThreadedTicketServer which listens to and accepts connections on specified port
	 * @param port - port that thread should accept connections from
	 * @param threadName - name of thread
	 */
	public ThreadedTicketServer(int port, String threadName) {
	    this.portNum = port;
	    this.threadname = threadName;
	}

	
	public void run() {	
		ServerSocket serverSocket = null;
		try {
			while(true){
				if (serverSocket == null){
					serverSocket = new ServerSocket(portNum);
				}
				Socket clientSocket = serverSocket.accept();	//accepts a client
				new ServeOneTicket(clientSocket, threadname);   //create Thread to handle ticket request
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//class to handle each ticket request in a separate Thread
class ServeOneTicket extends Thread{
	Socket clientSocket; //socket that client is connected to
	PrintWriter out; //used to print whether reserving seat was successful or not
	String threadName; //name of thread
	
	/**
	 * creates a new Thread to handle a single ticket request on specified port
	 * @param s - socket that client is connected to
	 * @param name - thread name
	 */
	public ServeOneTicket(Socket s, String name){
		clientSocket = s;
		threadName = name;
		try{
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			start(); //start the Thread
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void run() {
		seatsLeft.seatLock.lock(); // we want to lock here so only 1 client gets a seat at a time
		Seat bestSeat = seatsLeft.getBest(); //find best available seat
		
		//if a seat was found, print it to output stream
		if(seatsLeft.removeSeat(bestSeat)){
		    out.println(threadname + ": Reserved " + bestSeat.section + ", " + bestSeat.num + bestSeat.row +  "\n");
		}
		//else all tickets are sold out -> print message and terminate program
		else{
			
			out.println(threadname + ": Unable to reserve ticket: All tickets are sold out! Sorry!");
			//seatsLeft.seatLock.unlock(); 
			//System.exit(-1);
		}
		seatsLeft.seatLock.unlock(); //unlock after thread has finished finding a seat
		try {
			clientSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
	
}


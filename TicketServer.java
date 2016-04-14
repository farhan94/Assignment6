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
	static int PORT = 2222;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;

	public static void start(int portNumber) throws IOException {
		PORT = portNumber;
		Runnable serverThread = new ThreadedTicketServer();
		Thread t = new Thread(serverThread);
		t.start();
		
	}
}

class ThreadedTicketServer implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;
	ConcertHallSeats seatsLeft = new ConcertHallSeats();	// dont make this static, create a new instance for every threadedticketserver?
	static Lock one = new ReentrantLock();
	static Lock two = new ReentrantLock();
	public void run() {	//while loop- while clients != 0 keep running (old code instead of while true)
		// synchronize methods if needed
		ServerSocket serverSocket = null;
		try {
			while(true){
				if (serverSocket == null){
					serverSocket = new ServerSocket(TicketServer.PORT);
				}
				Socket clientSocket = serverSocket.accept();	//accepts a client
				new ServeOneTicket(clientSocket);
			}
			/*PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while(!seatsLeft.seatLock.tryLock()){	//lock the seating data structure so nothing else can access it
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	//Sleep if cant get lock
			}
			Seat bestSeat = seatsLeft.getBest();
			if(seatsLeft.removeSeat(bestSeat)){
				out.println("Seat Section: " +bestSeat.section);
				out.println("Seat Row: " +bestSeat.row);
				out.println("Seat Number: " +bestSeat.num);
				}
			else{
				out.println("All tickets are sold out! Sorry!");
			}
			serverSocket.close();
			seatsLeft.seatLock.unlock();
					//print seat info
					
				*/
			}
			catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		
	}
class ServeOneTicket extends Thread{
	Socket clientSocket;
	PrintWriter out;
	BufferedReader in;
	
	public ServeOneTicket(Socket s){
		clientSocket = s;
		try{
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			start();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	@Override
	public void run() {
		seatsLeft.seatLock.lock(); // we want to lock here so only 1 client gets a seat at a time
		Seat bestSeat = seatsLeft.getBest();
		if(seatsLeft.removeSeat(bestSeat)){
			out.println("Seat Section: " +bestSeat.section);
			out.println("Seat Row: " +bestSeat.row);
			out.println("Seat Number: " +bestSeat.num);
			}
		else{
			out.println("All tickets are sold out! Sorry!");
		}
		seatsLeft.seatLock.unlock();
				//print seat info
			try {
				clientSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
}
	
}


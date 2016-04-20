package assignment6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcertHallSeats {
	List<Seat> seats; // stores all available seats
	Lock seatLock; //lock to only allow one thread to access seats at a given time
	
	/**
	 * Creates an ArrayList that holds all availabe seats for the concert hall
	 */
	public ConcertHallSeats(){
		
		seats = new ArrayList<Seat>();
		seatLock = new ReentrantLock();
		ArrayList<Character> row = new ArrayList<Character>();
		// add all possible rows to list
		for (char i = 'A'; i <='Z'; i++){
			row.add(i);
		}
		
		// iterate through each row (A-Z) and add seats to ArrayList
		Iterator<Character> r = row.iterator();
		while (r.hasNext()){
			char x = r.next();
			// add all available seats (101-128) to ArrayList
			for (int i = 101; i <129; i++){
				if(i >= 101 && i<=108){
					seats.add(new Seat("Right", x, i));
				}
				else if(i >= 109 && i<=121){
					seats.add(new Seat("Middle", x, i));
				}
				else if(i >109){
					seats.add(new Seat("Left", x, i));
				}
			}
		}
		
	}

	/**
	 * finds the best seat available (middle section before left and right, rows closer to stage before rows farther away)
	 * @return Seat object representing best seat available
	 */
	public Seat getBest(){
		Iterator<Seat> i= seats.iterator();
		Seat result = null;
		if(i.hasNext()){
			result = i.next();
		}
		// iterate through all available seats and find best by comparing each 
		while (i.hasNext()){
			Seat next = i.next();
			result = compareSeat(result, next);
		}
		return result;
	}
	
	/**
	 * compares two seats to determine which is better
	 * @param x - first seat to be compared
	 * @param y - second seat to be compared
	 * @return Seat object representing better of two Seats
	 */
	public Seat compareSeat(Seat x, Seat y){
	    // the seat with the lower row number is closer and therefore better
		if (x.row < y.row){
			return x;
		}
		else if (x.row > y.row){
			return y;
		}
		// middle section is better than right or left section
		if (x.section.equals("Middle") && !(x.section.equals(y.section))){
			return x;
		}
		else if (y.section.equals("Middle") && !(y.section.equals(x.section))){
			return y;
		}
		return x; 
	}

	/**
	 * Removes a Seat from ArrayList
	 * @param x - Seat to be removed
	 * @return true if Seat was removed successfully, false otherwise
	 */
	protected boolean removeSeat(Seat x){
		return seats.remove(x);
	}
}


class Seat {
	String section; //section (middle, left, or right)
	char row; //seat row (A-Z)
	int num; //seat number (101-128)
	
	public Seat(String s, char r, int n){
		section = s;
		row = r;
		num = n;
	}
}
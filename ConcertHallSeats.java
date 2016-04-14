package assignment6;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcertHallSeats {
	List<Seat> seats;
	Lock seatLock; 
	public ConcertHallSeats(){
		
		seats = new ArrayList<Seat>();
		seatLock = new ReentrantLock();
		ArrayList<Character> row = new ArrayList<Character>();
		ArrayList<String> sect = new ArrayList<String>();
		ArrayList<Integer> num = new ArrayList<Integer>();
		for (int i = 101; i<129; i++){
			num.add(i);
		}
		for (char i = 'A'; i <='Z'; i++){
			row.add(i);
		}
		sect.add("Right");
		sect.add("Middle");
		sect.add("Left");
		Iterator<Character> r = row.iterator();
		while (r.hasNext()){
			char x = r.next();
			//Iterator<String> s = sect.iterator();
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

	public Seat getBest(){
		Iterator<Seat> i= seats.iterator();
		Seat result = null;
		if(i.hasNext()){
			result = i.next();
		}
		while (i.hasNext()){
			Seat next = i.next();
			result = compareSeat(result, next);
		}
		return result;
	}
	
	public Seat compareSeat(Seat x, Seat y){
		if (x.row < y.row){
			return x;
		}
		else if (x.row > y.row){
			return y;
		}
		if (x.section.equals("Middle") && !(x.section.equals(y.section))){
			return x;
		}
		else if (y.section.equals("Middle") && !(y.section.equals(x.section))){
			return y;
		}
		return x; //add other checks (middle of middle section, sides of left and right
	}

	protected boolean removeSeat(Seat x){
		return seats.remove(x);
	}
}

class Seat {
	String section;
	char row;
	int num;
	
	public Seat(String s, char r, int n){
		section = s;
		row = r;
		num = n;
	}
}
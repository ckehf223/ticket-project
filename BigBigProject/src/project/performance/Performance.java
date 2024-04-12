package project.performance;

import java.io.Serializable;
import java.util.Objects;

public class Performance implements Serializable,Comparable<Performance>{
	private String performanceID;// key값으로 할 예정
	private String name; // 공연명
	private String genre; // 장르(뮤지컬, 연극, 콘서트)
	private String dayOfPerformance; // 공연일
	private String venue; // 장소
	private int limitAge;	//관람제한연령
	private String totalSeats; // 총좌석수
	private int[][] seat; //좌석지정
	private int soldSeats; // 판매좌석수
	private int ticketPrice; // 티켓가격,compare
	
	public Performance() {
		
	}
	
	public Performance(String performanceID, String name, String genre, String dayOfPerformance, String venue,
			int limitAge, String totalSeats,int ticketPrice) {
		super();
		this.performanceID = performanceID;
		this.name = name;
		this.genre = genre;
		this.dayOfPerformance = dayOfPerformance;
		this.venue = venue;
		this.limitAge = limitAge;
		this.totalSeats = totalSeats;
		this.ticketPrice = ticketPrice;
		makeSeatsArray();
	}


	public String getPerformanceID() {
		return performanceID;
	}

	public void setPerformanceID(String performanceID) {
		this.performanceID = performanceID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDayOfPerformance() {
		return dayOfPerformance;
	}

	public void setDayOfPerformance(String dayOfPerformance) {
		this.dayOfPerformance = dayOfPerformance;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public int getLimitAge() {
		return limitAge;
	}

	public void setLimitAge(int limitAge) {
		this.limitAge = limitAge;
	}

	public String getTotalSeats() {
		return totalSeats;
		
	}

	public void setTotalSeats(String totalSeats) {
		this.totalSeats = totalSeats;
		makeSeatsArray();
	}

	public int[][] getSeat() {
		return seat;
	}

	public void setSeat(int[][] seat) {
		this.seat = seat;
		makeSeatsArray();
	}

	public int getSoldSeats() {
		return soldSeats;
	}

	public void setSoldSeats(int soldSeats) {
		this.soldSeats = soldSeats;
	}

	public int getTicketPrice() {
		return ticketPrice;
	}


	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public void makeSeatsArray() {
		String seat = this.totalSeats;
		int a = 0;
		int b =0;
//		String str = seat.substring(1);
//		a = (seat.charAt(0))-48;
		if(seat.length() == 1) {
			a =0;
			b = Integer.parseInt(seat);
		}
		if(seat.length() == 2) {
			a = seat.charAt(0)-48;
			b = (Integer.parseInt(seat.substring(1))+1)*10;
		}
		if(seat.length() == 3) {
			a = Integer.parseInt(seat.substring(0,2));
			b = (Integer.parseInt(seat.substring(2))+1)*10;
		}
		int[][] s= new int[a][b];
		for(int i=0;i<s.length;i++) {
			for(int j=0;j<s[i].length;j++) {
				s[i][j] = 0;
			}
		}
		this.seat = s;
	}

	@Override
	public int hashCode() {
		return Objects.hash(performanceID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Performance other = (Performance) obj;
		return Objects.equals(performanceID, other.performanceID);
	}

	@Override
	public int compareTo(Performance o) {
		return this.performanceID.compareToIgnoreCase(o.performanceID);
	}


	
	
	
}

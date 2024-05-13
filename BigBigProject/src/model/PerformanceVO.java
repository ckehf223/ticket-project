package model;

public class PerformanceVO {
	private int pf_no;					// 번호
	private String pf_id;				// 공연아이디
	private String pf_name; 			// 공연명
	private String pf_genre; 			// 장르(뮤지컬, 연극, 콘서트)
	private String pf_date; 			// 공연일
	private String pf_venue; 			// 장소
	private int pf_limitAge;			// 관람제한연령
	private String pf_totalSeats;		// 총좌석수
	private int[][] seat; 				// 좌석지정
	private int soldSeats;				// 판매좌석수
	private int pf_price;				// 티켓가격,compare
	
	public PerformanceVO() {
		
	}
	
	
	public PerformanceVO(String pf_id, String pf_name, String pf_genre, String pf_date, String pf_venue,
			int pf_limitAge, String pf_totalSeats, int pf_price) {
		super();
		this.pf_id = pf_id;
		this.pf_name = pf_name;
		this.pf_genre = pf_genre;
		this.pf_date = pf_date;
		this.pf_venue = pf_venue;
		this.pf_limitAge = pf_limitAge;
		this.pf_totalSeats = pf_totalSeats;
		this.pf_price = pf_price;
		makeSeatsArray();
	}


	public PerformanceVO(int pf_no, String pf_id, String pf_name, String pf_genre, String pf_date, String pf_venue,
			int pf_limitAge, String pf_totalSeats, int pf_price) {
		super();
		this.pf_no = pf_no;
		this.pf_id = pf_id;
		this.pf_name = pf_name;
		this.pf_genre = pf_genre;
		this.pf_date = pf_date;
		this.pf_venue = pf_venue;
		this.pf_limitAge = pf_limitAge;
		this.pf_totalSeats = pf_totalSeats;
		this.pf_price = pf_price;
		makeSeatsArray();
	}

	public int getPf_no() {
		return pf_no;
	}
	public void setPf_no(int pf_no) {
		this.pf_no = pf_no;
	}
	public String getPf_id() {
		return pf_id;
	}
	public void setPf_id(String pf_id) {
		this.pf_id = pf_id;
	}
	public String getPf_name() {
		return pf_name;
	}
	public void setPf_name(String pf_name) {
		this.pf_name = pf_name;
	}
	public String getPf_genre() {
		return pf_genre;
	}
	public void setPf_genre(String pf_genre) {
		this.pf_genre = pf_genre;
	}
	public String getPf_date() {
		return pf_date;
	}
	public void setPf_date(String pf_date) {
		this.pf_date = pf_date;
	}
	public String getPf_venue() {
		return pf_venue;
	}
	public void setPf_venue(String pf_venue) {
		this.pf_venue = pf_venue;
	}
	public int getPf_limitAge() {
		return pf_limitAge;
	}
	public void setPf_limitAge(int pf_limitAge) {
		this.pf_limitAge = pf_limitAge;
	}
	public String getPf_totalSeats() {
		return pf_totalSeats;
	}
	public void setPf_totalSeats(String pf_totalSeats) {
		this.pf_totalSeats = pf_totalSeats;
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
	public int getPf_price() {
		return pf_price;
	}
	public void setPf_price(int pf_price) {
		this.pf_price = pf_price;
	}
	public void makeSeatsArray() {
		String seat = this.pf_totalSeats;
		int a = 0;
		int b =0;
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
	
	public void printPerformance() {
		System.out.printf("%-16s", pf_id);
		System.out.printf("%-9s", "  " + pf_genre);
		System.out.printf("%-11s", "  " + pf_name);
		System.out.printf("%-10s", " " + pf_date);
		System.out.printf("%-10s", "  " + pf_venue);
		System.out.printf("%-7s", "   " + pf_limitAge+"세");
		System.out.printf("%-7s", "  " + pf_price);
		System.out.printf("%-8s", " " + soldSeats+"/"+pf_totalSeats);
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------");
	}
}

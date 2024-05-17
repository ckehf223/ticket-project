package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PerformanceVO;

public class SeatDAO {

	// 공연 좌석정보 가져오기
	public static PerformanceVO getPerformanceSeats(String pf_id) throws Exception {
		String sql = "select * from seat S inner join performance P on S.pf_id = P.pf_id where P.pf_id =?";
		int check = getSeatCheck(pf_id);
		if (check == 0) {
			sql = "select * from seat S right outer join performance P on S.pf_id = P.pf_id where P.pf_id =?";
		}
//		String sql ="SELECT P.pf_totalseats,s.seat_location FROM performance P left outer JOIN seat S ON P.pf_id = S.pf_id where P.pf_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PerformanceVO pvo = new PerformanceVO();
		String seatAll = "";
		int x = 0;
		int y = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pf_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pvo.setPf_totalSeats(rs.getString("pf_totalseats"));
				if(rs.getString("seat_location")==null) {
					seatAll = null;
				}else {
					seatAll += rs.getString("seat_location");
				}
			}

			if (seatAll != null) {
				String[] seats = new String[seatAll.length()];
				seats = seatAll.split(",");
				for (int i = 0; i < seats.length; i++) {
					String s = seats[i].substring(1);
					x = seats[i].charAt(0) - 65;
					y = Integer.parseInt(s) - 1;
					pvo.getSeat()[x][y] = 1;
				}
			}
		} catch (SQLException se) {
//			se.printStackTrace();
			System.out.println("..");
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("..");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
		return pvo;
	}// end of getPerformanceSeats

	// 좌석예약 시 중복체크
	public static boolean getPerformanceSeatCheck(String pf_id, String seat) throws Exception {
		String sql = "select S.seat_location,p.pf_totalseats"
				+ " from seat S inner join performance P on s.pf_id = P.pf_id where S.pf_id =?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean success = true;
		String seatAll = null;
		String[] seats = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pf_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				seatAll += rs.getString("seat_location");
			}
			if (seatAll != null) {
				seats = seatAll.split(",");
				for (int i = 0; i < seats.length; i++) {
					if (seats[i].equals(seat)) {
						System.out.println(seat);
						success = false;
					}
				}
			}
		} catch (SQLException se) {
//			se.printStackTrace();
			System.out.println("..");
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("..");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
		return success;
	}

	// 좌석 정보 null 체크
	public static int getSeatCheck(String pf_id) {
		String sql = "select count(*) as cnt from seat where pf_id=?";
		int checkNum = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pf_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				checkNum = rs.getInt("cnt");
			}
		} catch (SQLException se) {
//			se.printStackTrace();
			System.out.println("..");
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("..");
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
		return checkNum;
	}
}

package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PerformanceVO;

public class PerformanceDAO {
	// 공연 등록
	public static void setPerformanceRegister(PerformanceVO pvo) throws Exception {
		String sql = "insert into performance values (performance_seq.nextval,?,?,?,to_date(?),?,?,?,?)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getPf_id());
			pstmt.setString(2, pvo.getPf_name());
			pstmt.setString(3, pvo.getPf_genre());
			pstmt.setString(4, pvo.getPf_date());
			pstmt.setString(5, pvo.getPf_venue());
			pstmt.setInt(6, pvo.getPf_limitAge());
			pstmt.setString(7, pvo.getPf_totalSeats());
			pstmt.setInt(8, pvo.getPf_price());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(pvo.getPf_name() + "공연 등록 완료");
			} else {
				System.out.println("공연 등록 실패");
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
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}// end of setPerformanceRegister()

	// 공연 정보 리스트
	public static void getPerformanceTotalList() throws Exception {
		String sql = "select pf_no,pf_id,pf_name,pf_genre,to_char(pf_date) as pf_date,pf_venue,pf_limitage,pf_totalseats,pf_price from performance order by pf_no asc";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PerformanceVO pvo = null;
		int soldSeats = 0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("   공 연 ID    │ 공연  장르  │   공연명    │  공연일  │  공연 장소   │관람연령│  가 격  │  좌 석   ");
			System.out.println("────────────────────────────────────────────────────────────────────────────────────");
			while (rs.next()) {
				
				pvo = new PerformanceVO();
				pvo.setPf_no(rs.getInt("pf_no"));
				pvo.setPf_id(rs.getString("pf_id"));
				pvo.setPf_name(rs.getString("pf_name"));
				pvo.setPf_genre(rs.getString("pf_genre"));
				pvo.setPf_date(rs.getString("pf_date"));
				pvo.setPf_venue(rs.getString("pf_venue"));
				pvo.setPf_limitAge(rs.getInt("pf_limitage"));
				pvo.setPf_totalSeats(rs.getString("pf_totalseats"));
				pvo.setPf_price(rs.getInt("pf_price"));
				soldSeats = getPerformanceSeatCount(pvo.getPf_id());
				pvo.setSoldSeats(soldSeats);
				pvo.printPerformance();
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
	}// end getPerformanceTotalList

	// 공연 정보 찾기
	public static boolean getPerformanceSearch(String id) throws Exception {
		String sql = "select pf_no,pf_id,pf_name,pf_genre,to_char(pf_date) as pf_date,pf_venue,pf_limitage,pf_totalseats,pf_price from performance where pf_id=? order by pf_no asc";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PerformanceVO pvo = null;
		int soldSeats = 0;
		boolean success = false;
		
		try {
			con = DBUtil.getConnection();
			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			System.out.println("------------------------------------------------------------------------------------");
			System.out.println("   공 연 ID    │ 공연  장르  │   공연명    │  공연일  │  공연 장소   │관람연령│  가 격  │  좌 석   ");
			System.out.println("────────────────────────────────────────────────────────────────────────────────────");
			if (rs.next()) {
				pvo = new PerformanceVO();
				pvo.setPf_no(rs.getInt("pf_no"));
				pvo.setPf_id(rs.getString("pf_id"));
				pvo.setPf_name(rs.getString("pf_name"));
				pvo.setPf_genre(rs.getString("pf_genre"));
				pvo.setPf_date(rs.getString("pf_date"));
				pvo.setPf_venue(rs.getString("pf_venue"));
				pvo.setPf_limitAge(rs.getInt("pf_limitage"));
				pvo.setPf_totalSeats(rs.getString("pf_totalseats"));
				pvo.setPf_price(rs.getInt("pf_price"));
				soldSeats = getPerformanceSeatCount(pvo.getPf_id());
				pvo.setSoldSeats(soldSeats);
				pvo.printPerformance();
				success = true;
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
	}// end of getPerformanceSearch()
	
	// 공연삭제
	public static void setDeletePerformance(String pf_id) throws Exception {
		String sql = "delete from performance where pf_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pf_id);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(pf_id + "공연 삭제 완료");
			} else {
				System.out.println("공연 삭제 실패");
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
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}// end of setDeletePerformance
	
	//공연 좌석 카운트
	public static int getPerformanceSeatCount(String pf_id) {
		String sql ="select seat_location from seat where pf_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PerformanceVO pvo = null;
		String seatAll = null;
		int count = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pf_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				seatAll += (rs.getString("seat_location")) ;
			}
			if(seatAll != null) {
				String[] seatArray = seatAll.split(",");
				count = seatArray.length;
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
		return count;
	}

}// end of class

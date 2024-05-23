package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CartVO;
import model.PerformanceVO;

public class ReservationDAO {
		
	//예매하기
	public static void getReservationPerformance(CartVO cartvo) throws Exception {
		String sql = "{CALL cart_add_proc(?,?,?,?,?,?)}";
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, cartvo.getCt_id());
			cstmt.setString(2, cartvo.getPf_id());
			cstmt.setInt(3, cartvo.getCart_quantity());
			cstmt.setInt(4, cartvo.getCart_totalPrice());
			cstmt.setString(5, cartvo.getSeat_location());
			cstmt.setInt(6, cartvo.getPayment_check());
			
			int i = cstmt.executeUpdate();
			
			if (i != 0) {
				System.out.println("공연 예매 완료");
			} else {
				System.out.println("공연 예매 실패");
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
				if (cstmt != null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}//end of getReservationPerformance()
	
	//관람연령 체크
	public static int getCheckLimitAge(String pf_id) throws Exception{
		String sql = "select pf_limitage from performance where pf_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int limitAge = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pf_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				limitAge = rs.getInt("pf_limitage");
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
		return limitAge;
	}
	
	public static PerformanceVO getPerformanceVO(String pf_id) throws Exception {
		String sql = "select pf_no,pf_id,pf_name,pf_genre,to_char(pf_date) as pf_date,pf_venue,pf_limitage,pf_totalseats,pf_price from performance where pf_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PerformanceVO pvo = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pf_id);
			rs = pstmt.executeQuery();
			
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
	}// end getPerformanceTotalList
}

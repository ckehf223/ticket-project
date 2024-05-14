package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletePerformanceDAO {
	
	// 예매내역 삭제
	public static void getCartPerformanceDelete(String ct_id) throws Exception {
		String sql = "delete from cart where ct_id=? and payment_check=0";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ct_id);
			int i = pstmt.executeUpdate();
		
			if (i == 1) {
				System.out.println("예매내역 삭제 완료");
			} else {
				System.out.println("예매내역 삭제 실패");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	}// end of getReservationPerformance()
	
	//예매내역과 같은 좌석 정보 삭제
	public static void getSeatDelete(String ct_id) throws Exception {
		String sql = "delete from seat where (seat_location || pf_id) in(select seat_location || pf_id  from cart where ct_id =? and payment_check=0)";
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ct_id);
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("예매공연 좌석 삭제 완료");
			} else {
				System.out.println("예매공연 좌석 실패");
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	}
}

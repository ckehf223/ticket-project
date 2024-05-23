package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeletePerformanceDAO {
	
	// 예매내역 삭제
	public static void setCartPerformanceDelete(String ct_id) throws Exception {
		String sql = "{Call cart_delete_proc(?)}";
		Connection con = null;
		CallableStatement cstmt = null;

		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, ct_id);
			
			int i = cstmt.executeUpdate();
			if (i != 0) {
				System.out.println("예매내역 삭제 완료");
			} else {
				System.out.println("예매내역 삭제 실패");
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
	}// end of getReservationPerformance()
	
	//예매한 항목 존재 여부
	public static boolean getCartCount(String ct_id) {
		boolean success = false;
		String sql = "select count(*) as cnt from cart where ct_id=? and payment_check=0";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check =0;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareCall(sql);
			pstmt.setString(1, ct_id);
			
			rs = pstmt.executeQuery();
			check= rs.getInt("cnt");
			
			if(check !=0) {
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
	}
	
}

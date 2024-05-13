package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.CartVO;

public class CartDAO {
	
	//예매내역 확인 
	public static void getCartList(String id) {
		String sql = "select * from cart C inner join performance P on C.pf_id = P.pf_id where C.ct_id=? and payment_check=0";
		CartVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.printf("%-16s",rs.getString("P.pf_id"));
				System.out.printf("%-9s", "  " + rs.getString("P.pf_name"));
				System.out.printf("%-10s", "  " + rs.getString("P.venue"));
				System.out.printf("%-8s", " " +  rs.getString("P.pf_date"));
				System.out.printf("%-8s", " " + rs.getString("C.seat_location"));
				System.out.printf("%-6s", "  " + rs.getInt("C.cart_quantity"));
				System.out.printf("%-8s", " " + rs.getInt("C.cart_totalprice"));
				System.out.println();
				System.out.println("-----------------------------------------------------------------------");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	}//end of getCartList
	
	//결제내역 리스트
	public static void getPaymentCartList(String id) {
		String sql = "select * from cart C inner join performance P on C.pf_id = P.pf_id where ct_id=? and payment_check=1";
		CartVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.printf("%-16s",rs.getString("P.pf_id"));
				System.out.printf("%-9s", "  " + rs.getString("P.pf_name"));
				System.out.printf("%-10s", "  " + rs.getString("P.venue"));
				System.out.printf("%-8s", " " +  rs.getString("P.pf_date"));
				System.out.printf("%-8s", " " + rs.getString("C.seat_location"));
				System.out.printf("%-6s", "  " + rs.getInt("C.cart_quantity"));
				System.out.printf("%-8s", " " + rs.getInt("C.cart_totalprice"));
				System.out.println();
				System.out.println("-----------------------------------------------------------------------");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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
	}//end of getPaymentCartList
	
}

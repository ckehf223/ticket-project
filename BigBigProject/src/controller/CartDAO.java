package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import oracle.jdbc.OracleTypes;

public class CartDAO {

	// 예매내역 확인
	public static void getCartList(String id) {
		String sql = "{CALL CART_LIST(?,?)}";
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);
			while (rs.next()) {
				System.out.printf("%-16s", rs.getString("pf_id"));
				System.out.printf("%-9s", "  " + rs.getString("pf_name"));
				System.out.printf("%-10s", "  " + rs.getString("pf_venue"));
				System.out.printf("%-8s", " " + rs.getString("pf_date"));
				System.out.printf("%-8s", " " + rs.getString("seat_location"));
				System.out.printf("%-6s", "  " + rs.getInt("cart_quantity"));
				System.out.printf("%-8s", " " + rs.getInt("cart_totalprice"));
				System.out.println();
				System.out.println("-----------------------------------------------------------------------");
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
				if (cstmt != null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}// end of getCartList

	// 결제내역 리스트
	public static void getPaymentCartList(String id) {
		String sql = "{CALL PAYMENT_CART_LIST(?,?)}";
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);
			while (rs.next()) {
				System.out.printf("%-16s", rs.getString("pf_id"));
				System.out.printf("%-9s", "  " + rs.getString("pf_name"));
				System.out.printf("%-10s", "  " + rs.getString("Pf_venue"));
				System.out.printf("%-8s", " " + rs.getString("pf_date"));
				System.out.printf("%-8s", " " + rs.getString("seat_location"));
				System.out.printf("%-6s", "  " + rs.getInt("cart_quantity"));
				System.out.printf("%-8s", " " + rs.getInt("cart_totalprice"));
				System.out.println();
				System.out.println("-----------------------------------------------------------------------");
			}
		} catch (SQLException se) {
//			se.printStackTrace();
			System.out.println("..");
		} catch (Exception e) {
			System.out.println("..");
//			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if (rs != null) {
					rs.close();
				}
				if (cstmt != null) {
					cstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}// end of getPaymentCartList

}

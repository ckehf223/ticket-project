package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerVO;
import oracle.jdbc.OracleTypes;

public class CartPaymentDAO {
	
	//결제하기
	public static void getCartPayment(String ct_id) throws Exception {
		String sql = "{CALL cart_payment_proc(?)}";
		Connection con = null;
		CallableStatement cstmt = null;
		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, ct_id);
			int i= cstmt.executeUpdate();
			
			if(i != 0) {
				System.out.println("결제 성공");
			}else {
				System.out.println("결제 실패");
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
	}//결제 끝
	
	//결제 전 총금액 확인
	public static int getCartTotalPrice(String ct_id) throws Exception{
		String sql = " {CALL CART_TOTALPRICE(?,?)}";
		int totalPrice = 0;
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, ct_id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);
			
			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);
			while(rs.next()) {
				totalPrice += rs.getInt("cart_totalprice");
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
				if(rs != null) {
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
		return totalPrice;
	}
	//결제후 고객정보 바꿔주기
	public static void setCustomerChange(CustomerVO cvo) {
		String sql = "{CALL cart_payment_customer_proc(?,?,?,?,?,?)}";
		Connection con = null;
		CallableStatement cstmt = null;
		
		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, cvo.getCt_grade());
			cstmt.setDouble(2, cvo.getCt_saleRatio());
			cstmt.setInt(3, cvo.getCt_totalamount());
			cstmt.setInt(4, cvo.getCt_mileage());
			cstmt.setDouble(5, cvo.getCt_mileageSale());
			cstmt.setString(6, cvo.getCt_id());
			
			int i= cstmt.executeUpdate();
			if(i != 0) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패");
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
	}
}

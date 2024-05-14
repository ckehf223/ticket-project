package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerVO;

public class CartPaymentDAO {
	
	//결제하기
	public static void getCartPayment(String ct_id) throws Exception {
		String sql = "update cart set payment_check=1 where ct_id=? and payment_check=0";
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ct_id);
			int i= pstmt.executeUpdate();
			if(i == 1) {
				System.out.println("결제 성공");
			}else {
				System.out.println("결제 실패");
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
	}//결제 끝
	
	//결제 전 총금액 확인
	public static int getCartTotalPrice(String ct_id) throws Exception{
		String sql = "select cart_totalprice from cart where ct_id=?";
		int totalPrice = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ct_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				totalPrice += rs.getInt("cart_totalprice");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// 데이터베이스와의 연결에 사용되었던 오브젝트를 해제
				if(rs != null) {
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
		return totalPrice;
	}
	//결제후 고객정보 바꿔주기
	public static void setCustomerChanger(CustomerVO cvo) {
		String sql = "update customer set ct_grade=?,ct_saleratio=?,ct_totalamount=?,ct_mileage=?,ct_mileageratio=? where ct_id=?";
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getCt_grade());
			pstmt.setDouble(2, cvo.getCt_saleRatio());
			pstmt.setInt(3, cvo.getCt_totalamount());
			pstmt.setInt(4, cvo.getCt_mileage());
			pstmt.setDouble(5, cvo.getCt_mileageSale());
			pstmt.setString(6, cvo.getCt_id());
			
			int i= pstmt.executeUpdate();
			if(i == 1) {
				System.out.println("업데이트 성공");
			}else {
				System.out.println("업데이트 실패");
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

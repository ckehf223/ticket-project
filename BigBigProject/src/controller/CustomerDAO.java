package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.CustomerVO;

public class CustomerDAO {

	// 로그인
	public static CustomerVO getCustomerLogin(String id, String pw) throws Exception {
		String sql = "select * from customer where ct_id=? and ct_pw=?";
		CustomerVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				cvo = new CustomerVO(rs.getInt("ct_no"), rs.getString("ct_id"), rs.getString("ct_pw"),
						rs.getString("ct_name"), rs.getInt("ct_age"), rs.getString("ct_phone"),
						rs.getString("ct_address"), rs.getString("ct_grade"), rs.getDouble("ct_saleratio"),
						rs.getInt("ct_totalamount"), rs.getInt("ct_mileage"), rs.getDouble("ct_mileageratio"));
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
		return cvo;
	}// end of getCustomerLogin()

	// 회원가입
	public static void setCustomerRegister(CustomerVO cvo) throws Exception {
		String sql = "insert into customer (ct_no,ct_id,ct_pw,ct_name,ct_age,ct_phone,ct_address) values (customer_seq.nextval,?,?,?,?,?,?)";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getCt_id());
			pstmt.setString(2, cvo.getCt_pw());
			pstmt.setString(3, cvo.getCt_name());
			pstmt.setInt(4, cvo.getCt_age());
			pstmt.setString(5, cvo.getCt_phone());
			pstmt.setString(6, cvo.getCt_address());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println("회원가입 완료");
			} else {
				System.out.println("회원가입 실패");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}// end of setCustomerJoin

	// 회원 정보 수정
	public static void setCustomerUpdate(CustomerVO cvo) throws Exception {
		String sql = "update customer set ct_pw=?, ct_phone=?, ct_address=? where ct_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getCt_pw());
			pstmt.setString(2, cvo.getCt_phone());
			pstmt.setString(3, cvo.getCt_address());
			pstmt.setString(4, cvo.getCt_id());

			int i = pstmt.executeUpdate();
			if (i == 1) {
				System.out.println(cvo.getCt_id()+"회원 정보 수정 완료");
			} else {
				System.out.println("회원정보 수정 실패");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}// end of setCustomerUpdate()
	
	//회원 ID/PW 찾기
	public static void getCustomerSearch(String id,String phone) throws Exception{
		String sql = "select * from customer where ct_id=? and ct_phone=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("아이디: "+rs.getString("ct_id"));
				System.out.println("비밀번호: "+rs.getString("ct_pw"));
			}else {
				System.out.println("찾지 못했습니다. /n등록된 정보를 확인해 주세요.");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null) {
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
	}//end of getCustomerSearch()
	
	//회원 아이디 중복 체크
	public static boolean getCustomerIdOverlap(String idOverlap) throws Exception{
		String sql = "select * from customer where ct_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean idCheck = false;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, idOverlap);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				idCheck = true;
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if( rs != null) {
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
		return idCheck;
	}//end of getCustomerIdOverlap
	
	//회원 삭제
	public static void setCustomerDelete(String id) throws Exception{
		String sql = "delete customer where ct_id=?";

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int i= pstmt.executeUpdate();
			if(i == 1) {
				System.out.println(id+"회원 삭제 완료");
			}else {
				System.out.println(id+"회원 삭제 실패/n 다시 확인 바랍니다.");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
			}
		}
	}//end of setCustomerDelete()
	
	//모든 회원 보기
	public static void getCustomerTotalList() throws Exception {
		String sql = "select * from customer where ct_id !='admin' order by ct_no asc";
		CustomerVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				cvo = new CustomerVO(rs.getInt("ct_no"), rs.getString("ct_id"), rs.getString("ct_pw"),
						rs.getString("ct_name"), rs.getInt("ct_age"), rs.getString("ct_phone"),
						rs.getString("ct_address"), rs.getString("ct_grade"), rs.getDouble("ct_saleratio"),
						rs.getInt("ct_totalamount"), rs.getInt("ct_mileage"), rs.getDouble("ct_mileageratio"));
				System.out.println(cvo.toString());
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
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
	}//end of getCustomerTotalList()
	
}// end of class

package controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class ServerDAO {
	
	//1대1문의 내용 DB저장
	public static void setServiceContents(String ct_id, String question, String answer) {
		Connection con = null;
		CallableStatement cstmt = null;
		String sql = "{CALL service_save_proc(?,?,?)}";
		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1,ct_id );
			cstmt.setString(2, question);
			cstmt.setString(3, answer);
			
			//실행하는 명령
			cstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection 실패");
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try{
				if(cstmt != null) {
					cstmt.close();
				}
				if(con != null) {
					con.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

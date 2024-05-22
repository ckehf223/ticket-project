package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServiceVO;

public class ClientDAO {
	// 1대1 문의 내역 보기
	public static void inquiryServiceList(String ct_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from service where ct_id=?";
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ct_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ServiceVO svo = new ServiceVO();
				svo.setService_no(rs.getInt("service_no"));
				svo.setCt_id(ct_id);
				svo.setTitle(rs.getString("title"));
				svo.setContents(rs.getString("contents"));

				svo.printService();
			}
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection 실패");
		} catch (IOException e) {
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

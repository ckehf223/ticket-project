package controller;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ServiceVO;
import oracle.jdbc.OracleTypes;

public class ClientDAO {
	// 1대1 문의 내역 보기
	public static void inquiryServiceList(String ct_id) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		String sql = "{CALL INQUIRY_LIST(?,?)}";
		try {
			con = DBUtil.getConnection();
			cstmt = con.prepareCall(sql);
			cstmt.setString(1, ct_id);
			cstmt.registerOutParameter(2, OracleTypes.CURSOR);

			cstmt.executeQuery();
			rs = (ResultSet)cstmt.getObject(2);
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
				if (cstmt != null) {
					cstmt.close();
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

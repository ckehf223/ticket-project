package controller;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil {
	public static final String driver = "oracle.jdbc.driver.OracleDriver";
	public static final String filePath = "D:/git/ticket-repository/BigBigProject/src/controller/performancemarket.properties";
	
	public static Connection getConnection() throws IOException{
		Properties properties = new Properties();
		properties.load(new FileReader(filePath));
		String url = properties.getProperty("url");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패");
		}
		return con;
	}
	public static void main(String[] args) throws IOException {
		Connection con =getConnection();
	}
}

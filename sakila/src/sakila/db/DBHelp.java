package sakila.db;

import java.sql.*;

public class DBHelp {
	// 다른곳에서 객체생성을 자유롭게 하기위해 static를 붙여줌
	
	// db를 연결해주는 메소드
	public static Connection getConncetion() throws Exception{
		 Class.forName("org.mariadb.jdbc.Driver");
		 Connection conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/sakila","root","java1234");
		 return conn;
	}
	
	// db를 종료하는 메소드
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

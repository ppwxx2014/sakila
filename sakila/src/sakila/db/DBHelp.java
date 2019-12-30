package sakila.db;

import java.sql.*;

public class DBHelp {
	
	// db연결 메서드
	public static Connection getConncetion() throws Exception{
		 Class.forName("org.mariadb.jdbc.Driver");
		 // Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/maintain0105","maintain0105","maintain0105!");
		 Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		 return conn;
	}
	
	// db 종료 메서드
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

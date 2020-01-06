package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelp;

public class CustomerDao {
	// 추가
	public void insertCustomer(Connection conn, Customer customer) throws SQLException {
		// Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO customer(store_id, first_name, last_name, email, address_id, active, create_date, last_update) VALUES (?, ?, ?, ?, ?, 1, now(), now())";
		
			// conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getStore().getStoreId());
			stmt.setString(2, customer.getFirstName());
			stmt.setString(3, customer.getLastName());
			stmt.setString(4, customer.getEmail());
			stmt.setInt(5, customer.getAddress().getAddressId());
			stmt.executeUpdate();
		
	}
	
	// 전체 행의 갯수
	public int selectCustomerCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM customer";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("COUNT(*)");
			}
		} catch(Exception e) {
			
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return count;
	}
	
	// 탈퇴한 회원 수
	public int selectLeaveCustomer() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(customer_id) customerId from customer WHERE active = 0";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("customerId");
			}
		} catch(Exception e) {
			
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		System.out.println("count: " + count);
		return count;
	}
	
	// 사원의 리스트 출력(간략정보)
	public List<Customer> selectCustomerList(Connection conn, int currentPage) throws SQLException {
		List<Customer> list = new ArrayList<Customer>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage -1) * ROW_PER_PAGE;
		System.out.println("beginRow : " + beginRow);
		
		String sql = "SELECT customer_id, first_name, last_name, email FROM customer ORDER BY customer_id DESC limit ?, ?";
		stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);	
		stmt.setInt(2, ROW_PER_PAGE);
		rs = stmt.executeQuery();
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt("customer_id"));
			customer.setFirstName(rs.getString("first_name"));
			customer.setLastName(rs.getString("last_name"));
			customer.setEmail(rs.getString("email"));
			list.add(customer);
		}
		
		return list;
	}
}








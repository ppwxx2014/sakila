package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.Address;
import sakila.customer.model.Customer;
import sakila.db.DBHelp;
import sakila.inventory.model.Category;
import sakila.inventory.model.Film;

public class PaymentDao {
	
	// 매장별 매출액
	public List<Payment> selectTotalPayment() {
		
		List<Payment> list = new ArrayList<Payment>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT\r\n" + 
				"CONCAT(c.city, _utf8mb4',', cy.country) AS store, \r\n" + 
				"CONCAT(st.first_name, _utf8mb4' ', st.last_name) AS staffName, \r\n" + 
				"SUM(p.amount) AS totalSales\r\n" + 
				"FROM payment AS p\r\n" + 
				"INNER JOIN rental AS r ON p.rental_id = r.rental_id\r\n" + 
				"INNER JOIN inventory AS i ON r.inventory_id = i.inventory_id\r\n" + 
				"INNER JOIN store AS s ON i.store_id = s.store_id\r\n" + 
				"INNER JOIN address AS a ON s.address_id = a.address_id\r\n" + 
				"INNER JOIN city AS c ON a.city_id = c.city_id\r\n" + 
				"INNER JOIN country AS cy ON c.country_id = cy.country_id\r\n" + 
				"INNER JOIN staff AS st ON s.manager_staff_id = st.staff_id\r\n" + 
				"GROUP BY s.store_id\r\n" + 
				"ORDER BY cy.country, c.city";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Payment payment = new Payment();
				
				payment.setStore(new Store());
				payment.getStore().setAddress(new Address());
				payment.getStore().getAddress().setDistrict(rs.getString("store"));
				
				payment.setStaff(new Staff());
				payment.getStaff().setName(rs.getString("staffName"));
				
				payment.setAmount(rs.getDouble("totalSales"));
				list.add(payment);
			}
			System.out.println("list: " + list.toString());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	// 카테고리별 매출액
	public List<Payment> selectPaymentByCategory() {
		List<Payment> list = new ArrayList<Payment>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT\r\n" + 
					"c.name AS category, \r\n" + 
					"SUM(p.amount) AS totalSales\r\n" + 
					"FROM payment AS p\r\n" + 
					"INNER JOIN rental AS r ON p.rental_id = r.rental_id\r\n" + 
					"INNER JOIN inventory AS i ON r.inventory_id = i.inventory_id\r\n" + 
					"INNER JOIN film AS f ON i.film_id = f.film_id\r\n" + 
					"INNER JOIN film_category AS fc ON f.film_id = fc.film_id\r\n" + 
					"INNER JOIN category AS c ON fc.category_id = c.category_id\r\n" + 
					"GROUP BY c.name\r\n" + 
					"ORDER BY totalSales DESC";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Payment payment = new Payment();
				
				payment.setCategory(new Category());
				payment.getCategory().setName(rs.getString("category"));
				
				payment.setAmount(rs.getDouble("totalSales"));
				list.add(payment);
			}
			System.out.println("list: " + list.toString());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	// 전체 행의 갯수
	public int selectPaymentCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM payment";
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
	
	// 리스트 출력
	public List<Payment> selectRentalList(int currentPage) {
		
		List<Payment> list = new ArrayList<Payment>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage -1) * ROW_PER_PAGE;
		
		System.out.println("beginRow : " + beginRow);
		String sql = "SELECT sto.store_id, CONCAT(st.first_name, ' ', st.last_name) staffName,\r\n" + 
					"CONCAT(cus.first_name, ' ', cus.last_name) customerName, fi.film_id,\r\n" +
					"fi.title,max(ren.rental_date) rentalDate, MAX(pay.payment_date) paymentDate, \r\n" + 
					"pay.amount, pay.last_update\r\n" + 
					"FROM payment pay\r\n" + 
					"INNER JOIN store sto\r\n" + 
					"INNER JOIN staff st\r\n" + 
					"INNER JOIN rental ren\r\n" + 
					"INNER JOIN customer cus\r\n" + 
					"INNER JOIN inventory inv\r\n" + 
					"INNER JOIN film fi\r\n" + 
					"ON\r\n" + 
					"pay.rental_id = ren.rental_id\r\n" + 
					"AND\r\n" + 
					"pay.staff_id = sto.manager_staff_id\r\n" + 
					"AND\r\n" + 
					"pay.customer_id = cus.customer_id\r\n" + 
					"AND\r\n" + 
					"ren.customer_id = cus.customer_id\r\n" + 
					"AND\r\n" + 
					"ren.inventory_id = inv.inventory_id\r\n" + 
					"AND\r\n" + 
					"inv.film_id = fi.film_id\r\n" + 
					"GROUP BY customerName\r\n" + 
					"ORDER BY customerName ASC LIMIT ?, ?";
		
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Payment payment = new Payment();
				
				payment.setStore(new Store());
				payment.getStore().setStoreId(rs.getInt("sto.store_id"));
				
				payment.setStaff(new Staff());
				payment.getStaff().setName(rs.getString("staffName"));
				
				payment.setCustomer(new Customer());
				payment.getCustomer().setFullname(rs.getString("customerName"));
				
				payment.setFilm(new Film());
				payment.getFilm().setFilmId(rs.getInt("fi.film_id"));
				payment.getFilm().setTitle(rs.getString("fi.title"));
				
				payment.setRental(new Rental());
				payment.getRental().setRentalDate(rs.getString("rentalDate"));
				
				payment.setPaymentDate(rs.getString("paymentDate"));
				payment.setAmount(rs.getDouble("pay.amount"));
				payment.setLastUpdate(rs.getString("pay.last_update"));
				list.add(payment);
			}
			System.out.println("list: " + list.toString());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	// 고객의 대여료 리스트 (중복제거)
	public int selectPaymentCountGroupByName() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(DISTINCT customer_id) as count FROM payment";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
			}
		} catch(Exception e) {
			
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		System.out.println("count : " + count);
		return count;
	}
	
}

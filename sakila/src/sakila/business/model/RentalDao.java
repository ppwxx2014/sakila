package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.Address;
import sakila.customer.model.Customer;
import sakila.db.DBHelp;
import sakila.iventory.model.Film;

public class RentalDao {
	// 전체 행의 갯수
	public int selectRentalCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM rental";
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
	
	// 고객의 필름대여 리스트(중복제거)
	public int selectRentalCountGroupByName() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT( DISTINCT customer_id ) as count FROM rental";
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
	
	// 리스트 출력
	public List<Rental> selectRentalList(int currentPage) {
		
		List<Rental> list = new ArrayList<Rental>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage -1) * ROW_PER_PAGE;
		
		System.out.println("beginRow : " + beginRow);
		String sql = "SELECT sto.store_id, ad.address, CONCAT(st.first_name, ' ', st.last_name) staffName, CONCAT(cus.first_name, ' ', cus.last_name) customerName, fi.film_id, fi.title, max(ren.rental_date) as rentalDate, ren.return_date, ren.last_update\r\n" + 
				"FROM rental ren \r\n" + 
				"INNER JOIN store sto\r\n" + 
				"INNER JOIN staff st\r\n" + 
				"INNER JOIN address ad\r\n" + 
				"INNER JOIN customer cus\r\n" + 
				"INNER JOIN inventory inv\r\n" + 
				"INNER JOIN film fi\r\n" + 
				"ON\r\n" + 
				"ren.staff_id = sto.store_id\r\n" + 
				"AND\r\n" + 
				"ren.staff_id = st.staff_id\r\n" + 
				"AND\r\n" + 
				"sto.address_id = ad.address_id\r\n" + 
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
				Rental rental = new Rental();
				
				rental.setStore(new Store());
				rental.getStore().setStoreId(rs.getInt("sto.store_id"));
				
				rental.setStaff(new Staff());
				rental.getStaff().setAddress(new Address());
				rental.getStaff().getAddress().setAddress(rs.getString("ad.address"));
				rental.getStaff().setName(rs.getString("staffName"));
				
				rental.setCustomer(new Customer());
				rental.getCustomer().setFullname(rs.getString("customerName"));
				
				rental.setFilm(new Film());
				rental.getFilm().setFilmId(rs.getInt("fi.film_id"));
				rental.getFilm().setTitle(rs.getString("fi.title"));
				
				rental.setRentalDate(rs.getString("rentalDate"));
				rental.setReturnDate(rs.getString("ren.return_date"));
				rental.setLastUpdate(rs.getString("ren.last_update"));
				list.add(rental);
			}
			System.out.println("list: " + list.toString());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
}

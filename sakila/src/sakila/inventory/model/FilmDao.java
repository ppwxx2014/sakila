package sakila.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.business.model.Rental;
import sakila.business.model.Staff;
import sakila.business.model.Store;
import sakila.customer.model.Address;
import sakila.customer.model.Customer;
import sakila.db.DBHelp;

public class FilmDao {
	// 전체 행의 갯수
	public int selectFilmCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM film";
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
	
	// 전체 리스트
	public List<Film> selectFilmList(int currentPage, String rating) {
		
		List<Film> list = new ArrayList<Film>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		
		System.out.println("beginRow : " + beginRow);
		
		String sql ="SELECT fi.film_id, fi.title, fi.description, fi.release_year, \r\n" + 
					"lan.name, fi.rental_duration, fi.rental_rate, fi.length,\r\n" + 
					"fi.replacement_cost, fi.rating, fi.special_features, fi.last_update from film fi\r\n" + 
					"INNER JOIN\r\n" + 
					"language lan\r\n" + 
					"ON fi.language_id = lan.language_id\r\n" +
					"ORDER BY fi.film_id ASC LIMIT ?, ?";
		
		String sql2 = "SELECT fi.film_id, fi.title, fi.description, fi.release_year, \r\n" + 
				"		lan.name, fi.rental_duration, fi.rental_rate, fi.length,\r\n" + 
				"		fi.replacement_cost, fi.rating, fi.special_features, fi.last_update from film fi\r\n" + 
				"INNER JOIN\r\n" + 
				"language lan\r\n" + 
				"ON fi.language_id = lan.language_id\r\n" + 
				"WHERE fi.rating = ?\r\n" + 
				"ORDER BY fi.film_id ASC LIMIT ?, ?";
		try {
			conn = DBHelp.getConncetion();
			if(rating.equals("")) {
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginRow);
				stmt.setInt(2, ROW_PER_PAGE);
			} else {
				stmt = conn.prepareStatement(sql2);
				stmt.setString(1, rating);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, ROW_PER_PAGE);
			}
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Film film = new Film();
				film.setFilmId(rs.getInt("fi.film_id"));
				film.setTitle(rs.getString("fi.title"));
				film.setDescription(rs.getString("fi.description"));
				film.setReleaseYear(rs.getString("fi.release_year"));
				
				film.setLanguage(new Language());
				film.getLanguage().setName(rs.getString("lan.name"));
				
				film.setRentalDuration(rs.getInt("fi.rental_duration"));
				film.setRentalRate(rs.getDouble("fi.rental_rate"));
				film.setLength(rs.getInt("fi.length"));
				film.setReplacementCost(rs.getDouble("fi.replacement_cost"));
				film.setRating(rs.getString("fi.rating"));
				film.setSpecialFeatures(rs.getString("fi.special_features"));
				film.setLastUpdate(rs.getString("fi.last_update"));
				
				list.add(film);
				
			}
			System.out.println("list: " + list.toString());
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	public List<Film> filmRating() {
		List<Film> list = new ArrayList<Film>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT rating FROM film GROUP BY rating";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Film film = new Film();
				film.setRating(rs.getString("rating"));
				list.add(film);
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















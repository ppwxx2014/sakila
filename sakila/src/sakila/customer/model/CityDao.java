package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelp;

public class CityDao {
	public List<City> selectCityListByCountry(int cityId) {
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT city_id, city FROM city where country_id = ?";
		
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, cityId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCityId(rs.getInt("city_id"));
				city.setCity(rs.getString("city"));
				list.add(city);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
			
	public List<City> selectCityList(int currentPage) {
		/*
		 * city inner join country 
		 */
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		String sql = "SELECT c.country_id, c.country, c.last_update, ci.city_id, ci.city, ci.last_update"
				+ " FROM city ci INNER JOIN country c ON ci.country_id = c.country_id ORDER BY ci.city_id DESC limit ?,?";
		
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				City city = new City();
				city.setCountry(new Country());
				city.getCountry().setCountryId(rs.getInt("c.country_id"));
				city.getCountry().setCountry(rs.getString("c.country"));
				city.getCountry().setLastUpdate(rs.getString("c.last_update"));
				city.setCityId(rs.getInt("ci.city_id"));
				city.setCity(rs.getString("ci.city"));
				city.setLastUpdate(rs.getString("ci.last_update"));
				list.add(city);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	public int selectCityCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM city";
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
	
	// 도시 추가
	public void insertCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO city(city, last_update, country_id) VALUES (?, now(), ?)";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getCity());
			stmt.setInt(2, city.getCountry().getCountryId());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(null, stmt, conn);
		}
	}
}




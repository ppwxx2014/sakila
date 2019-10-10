package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelper;

public class CountryDao {
	// country의 리스트를 보여주는 메서드
	public List<Country> selectCountryList(int currentPage) {
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		String sql = "SELECT country_id, country, last_update FROM country ORDER BY country_id DESC limit ?,?";
		try {
			conn = DBHelper.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country country = new Country();
				country.setCountryId(rs.getInt("country_id"));
				country.setCountry(rs.getString("country"));
				country.setLastUpdate(rs.getString("last_update"));
				list.add(country);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	// Country테이블에 추가하는 메서드
	public void insertCountry(Country country) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO country(country, last_update) VALUES (?, now())";
		try {
			conn = DBHelper.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(null, stmt, conn);
		}
	}
	
	
	// 전체행을 구하는 메서드
}

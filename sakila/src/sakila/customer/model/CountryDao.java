package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelp;

public class CountryDao {
	// 전체 리스트
	public List<Country> selectCountryListAll() {
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT country_id, country, last_update FROM country ORDER BY country_id";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country country = new Country();
				country.setCountryId(rs.getInt("country_id"));
				country.setCountry(rs.getString("country"));
				list.add(country);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	// country 리스트 (페이징)
	public List<Country> selectCountryList(int currentPage) {
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;
		int beginRow = (currentPage - 1) * ROW_PER_PAGE;
		String sql = "SELECT country_id, country, last_update FROM country ORDER BY country_id DESC limit ?,?";
		try {
			conn = DBHelp.getConncetion();
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
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	// Country입력
	public void insertCountry(Country country) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO country(country, last_update) VALUES (?, now())";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, country.getCountry());
			stmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(null, stmt, conn);
		}
	}
	
	
	// 전체 행의 갯수를 구함
	public int selectCountryCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM country";
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
}

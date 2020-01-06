package sakila.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelp;

public class InventoryDao {
	// 전체 행의 갯수
	public int selectInventoryCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM inventory";
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
	
	// 가장 많이 빌려간 필름
	public List<Inventory> topRentalFilm() {
		List<Inventory> list = new ArrayList<Inventory>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT y.filmtitle, MAX(y.num) AS rentalCountTop \r\n" + 
					"	FROM (\r\n" + 
					"	SELECT fi.title AS filmtitle, COUNT(inv.film_id) AS num FROM inventory inv\r\n" + 
					"	INNER JOIN\r\n" + 
					"	rental ren\r\n" + 
					"	INNER join\r\n" + 
					"	film fi\r\n" + 
					"	ON\r\n" + 
					"	inv.inventory_id = ren.inventory_id\r\n" + 
					"	AND\r\n" + 
					"	fi.film_id = inv.film_id\r\n" + 
					"	GROUP BY inv.film_id\r\n" + 
					"	ORDER BY num desc LIMIT 1 \r\n" + 
					") y";
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Inventory inv = new Inventory();
				inv.setFilm(new Film());
				inv.getFilm().setTitle(rs.getString("y.filmtitle"));
				inv.setRentalCount(rs.getInt("rentalCountTop"));
				
				list.add(inv);
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

package sakila.inventory.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.db.DBHelp;

public class FilmActorDao {
	// 전체 행의 갯수
	public int selectFilmActorCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM film_actor";
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
	
	public List<FilmActor> selectTopActor() {
		List<FilmActor> list = new ArrayList<FilmActor>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT y.actorName AS actorName, MAX(y.num) AS count FROM\r\n" + 
				"	(\r\n" + 
				"		SELECT CONCAT(ac.first_name, ' ', ac.last_name) AS actorName, COUNT(fia.actor_id) AS num \r\n" + 
				"		FROM film_actor fia\r\n" + 
				"		INNER JOIN\r\n" + 
				"		actor ac\r\n" + 
				"		ON\r\n" + 
				"		fia.actor_id = ac.actor_id\r\n" + 
				"		GROUP BY fia.actor_id\r\n" + 
				"		ORDER BY num DESC LIMIT 1\r\n" + 
				"	) y";
		
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				FilmActor fa = new FilmActor();
				
				fa.setFilm(new Film());
				fa.setActor(new Actor());
				
				fa.getFilm().setFilmCount(rs.getInt("count"));
				fa.getActor().setFullname(rs.getString("actorName"));
				
				list.add(fa);
			}
			System.out.println("list: " + list.toString());
		} catch(Exception e) {
			
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		
		return list;
	}
}






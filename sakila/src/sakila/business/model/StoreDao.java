package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.Address;
import sakila.db.DBHelp;

public class StoreDao {
	
	// 리스트 출력
	public List<Store> selectStoreList() {
		
		List<Store> list = new ArrayList<Store>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT s.store_id, ad.address, CONCAT(st.first_name, ' ', st.last_name) staffName, s.last_update FROM store s \r\n" + 
				"INNER JOIN address ad \r\n" + 
				"INNER JOIN staff st\r\n" + 
				"ON s.address_id = ad.address_id\r\n" + 
				"AND s.manager_staff_id = st.staff_id";
		
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Store store = new Store();
				store.setStoreId(rs.getInt("s.store_id"));
				store.setAddress(new Address());
				store.getAddress().setAddress(rs.getString("ad.address"));
				store.setStaff(new Staff());
				store.getStaff().setFirstName(rs.getString("staffName"));
				store.setLastUpdate(rs.getString("s.last_update"));
				
				list.add(store);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
	
	// 전체 행의 갯수
	public int selectStoreCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM store";
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

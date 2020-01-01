package sakila.business.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.Address;
import sakila.db.DBHelp;

public class StaffDao {
	
	// 리스트 출력
	public List<Staff> selectStaffList() {
		
		List<Staff> list = new ArrayList<Staff>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT st.store_id, st.staff_id,  CONCAT(st.first_name, ' ', st.last_name) staffName, ad.address, st.email, st.password, st.last_update\r\n" + 
				"FROM staff st \r\n" + 
				"INNER JOIN address ad\r\n" + 
				"ON st.address_id = ad.address_id";
		
		try {
			conn = DBHelp.getConncetion();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Staff staff = new Staff();
				staff.setStoreId(rs.getInt("st.store_id"));
				staff.setStaffId(rs.getInt("st.staff_id"));
				staff.setName(rs.getString("staffName"));
				staff.setEmail(rs.getString("st.email"));
				staff.setPassword(rs.getString("st.password"));
				staff.setLastUpdate(rs.getString("st.last_update"));
				
				staff.setAddress(new Address());
				staff.getAddress().setAddress(rs.getString("ad.address"));
				
				list.add(staff);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBHelp.close(rs, stmt, conn);
		}
		return list;
	}
		
	// 전체 행의 갯수
	public int selectStaffCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM staff";
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

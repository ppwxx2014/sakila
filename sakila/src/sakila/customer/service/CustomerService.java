package sakila.customer.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.Address;
import sakila.customer.model.AddressDao;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.db.DBHelp;

public class CustomerService {
	private AddressDao addressDao;
	private CustomerDao customerDao;
	
	public List<Customer> selectCustomerList() {
		Connection conn = null;
		List<Customer> list = new ArrayList<Customer>();
		try {	
			conn = DBHelp.getConncetion();
			customerDao = new CustomerDao();
			list = customerDao.selectCustomerList(conn);
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
				DBHelp.close(null, null, conn);
			}
		return list;
		}
	
	
	
	public void insertCustomer(Address address, Customer customer) { // 두개의 요청을 한번에 처리함
		Connection conn = null;
		try {
			conn = DBHelp.getConncetion();
			conn.setAutoCommit(false);
			customerDao = new CustomerDao();
		
		addressDao = new AddressDao();
		int addressId = 0;
		
		addressId = addressDao.insertAddress(conn, address); // 같은 Connection을 사용하기위해 con도 넘겨줌
		if(addressId != 0) {
			System.out.println("addressId 입력됨");
		}
		customer.getAddress().setAddressId(addressId);
		
		customerDao.insertCustomer(conn, customer);
		conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
			
			}
			e.printStackTrace();
		} finally {
			DBHelp.close(null, null, conn);
		}
	}
}







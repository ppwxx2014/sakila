package sakila.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.business.model.Store;
import sakila.customer.model.Address;
import sakila.customer.model.City;
import sakila.customer.model.Customer;
import sakila.customer.service.CustomerService;

@WebServlet("/insertCustomer")
public class InsertCustomer extends HttpServlet {
	
	private CustomerService customerService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: InsertCustomer 실행 :::");
		
		// address request.getParameter("");
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String district = request.getParameter("district");
		String postalCode = request.getParameter("postalCode");
		String phone = request.getParameter("phone");
		System.out.println("request val.. : " + cityId + address + address2 + district + postalCode + phone);
		
		
		
		// address.set...
		Address add = new Address();
		add.setCity(new City());
		add.getCity().setCityId(cityId);
		add.setAddress(address);
		add.setAddress2(address2);
		add.setDistrict(district);
		add.setPostalCode(postalCode);
		add.setPhone(phone);
		
		// customer request.getParameter("");
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		System.out.println("Customer request val.. : " + firstName + lastName + email);
		
		// customer.set...
		Customer customer = new Customer();
		customer.setAddress(new Address());
		customer.setStore(new Store());
		customer.getStore().setStoreId(storeId);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setEmail(email);
		
		
		CustomerService customerService = new CustomerService();
		customerService.insertCustomer(add, customer);
		
		System.out.println("::: InsertCustomer 종료 :::");
	}
}







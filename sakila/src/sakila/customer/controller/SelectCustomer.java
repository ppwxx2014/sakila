package sakila.customer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.Customer;
import sakila.customer.service.CustomerService;

@WebServlet("/selectCustomer")
public class SelectCustomer extends HttpServlet {
	private CustomerService customerService;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectCustomer 실행 :::");
		
		response.setContentType("application/json;charset=utf-8");
		
		customerService = new CustomerService();
		List<Customer> list = new ArrayList<Customer>();
		
		list = customerService.selectCustomerList();
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectCustomer 종료 :::");
	}
}





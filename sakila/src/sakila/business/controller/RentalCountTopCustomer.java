package sakila.business.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.Rental;
import sakila.business.model.RentalDao;

@WebServlet("/business/rentalCountTopCustomer")
public class RentalCountTopCustomer extends HttpServlet {
	private RentalDao rentalDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: RentalCountTopCustomer 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		rentalDao = new RentalDao();
		List<Rental> list = rentalDao.rentalCountTopCustomer();
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: RentalCountTopCustomer 종료 :::");
	}

}

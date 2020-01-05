package sakila.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.CountryDao;


@WebServlet("/customer/selectCountryCount")
public class SelectCountryCount extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectCountryCount 실행 :::");
		response.setContentType("application/json;charset=UTF-8");
		
		countryDao = new CountryDao();
		int count = countryDao.selectCountryCount();
		
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		System.out.println(jsonCount);
		
		response.getWriter().write(jsonCount);
		System.out.println("::: SelectCountryCount 종료 :::");
	}
}

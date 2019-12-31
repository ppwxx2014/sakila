package sakila.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.City;
import sakila.customer.model.CityDao;
import sakila.customer.model.Country;
import sakila.customer.model.CountryDao;

@WebServlet("/customer/insertCity")
public class InsertCity extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		
		// countryId와 city request..
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		String city = request.getParameter("city");
		
		// 객체 생성
		cityDao = new CityDao();
		City c = new City();
		
		c.setCountry(new Country());
		c.setCity(city);
		c.getCountry().setCountryId(countryId);
		cityDao.insertCity(c);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(c);
		
		response.getWriter().write(jsonStr);
		
	}
}







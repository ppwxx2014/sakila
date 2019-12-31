package sakila.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.CityDao;


@WebServlet("/customer/selectCityCount")
public class SelectCityCount extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// json타입으로 응답해주기위해서
		response.setContentType("application/json;charset=UTF-8");
		
		cityDao = new CityDao();
		int count = cityDao.selectCityCount();
		
		Gson gson = new Gson();
		String jsonCount = gson.toJson(count);
		System.out.println(jsonCount);
		
		response.getWriter().write(jsonCount);
	}
}

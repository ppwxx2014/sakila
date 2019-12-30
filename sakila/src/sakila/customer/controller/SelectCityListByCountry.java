package sakila.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.City;
import sakila.customer.model.CityDao;


@WebServlet("/selectCityListByCountry")
public class SelectCityListByCountry extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		
		System.out.println("::: SelectCityListByCountry 실행 :::");
		
		cityDao = new CityDao();
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		System.out.println("선택한 나라 : " + countryId);
		
		List<City> list = cityDao.selectCityListByCountry(countryId);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectCityListByCountry 종료 :::");
	}
}




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


@WebServlet("/address/selectCityList")
public class SelectCityList extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---cityList Servlet---");
		
		response.setContentType("application/json;charset=UTF-8");
		
		// 첫페이지는 1
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		cityDao = new CityDao();
		
		List<City> list = cityDao.selectCityList(currentPage);
		
		// json클래스로 리스트를 보내줌
		Gson gson = new Gson();
		
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
		
		response.getWriter().write(jsonList);
	}
}

package sakila.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.Country;
import sakila.customer.model.CountryDao;

@WebServlet("/customer/selectCountryList")
public class SelectCountryList extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//json타입으로 리턴
		response.setContentType("application/json;charset=UTF-8");
		
		System.out.println("currentPage : " + request.getParameter("currentPage"));

		//첫 html에서 먼저 넘겨받아서 첫페이지 설정 x
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		countryDao = new CountryDao();
		List<Country> list = countryDao.selectCountryList(currentPage);
		// json클래스 선언 --> ajax와 데이터 통신을 위해
		Gson gson = new Gson();
		// list의 내용을 json타입으로 바꿔줌
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
		
		// json타입으로쓴걸 응답해줌
		response.getWriter().write(jsonList);
	}
}








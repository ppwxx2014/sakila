package sakila.customer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.customer.model.Address;
import sakila.customer.model.AddressDao;

@WebServlet("/selctAddressByCity")
public class SelectAddressByCity extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelctAddressByCity 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		addressDao = new AddressDao();
		
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		System.out.println("입력받은 도시번호 : " + cityId);
		
		List<Address> list = addressDao.selectCityListByCountry(cityId);
		
		// json 클래스
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		// 응답
		response.getWriter().write(jsonStr);
		System.out.println("::: SelctAddressByCity 종료 :::");
		
	}
}

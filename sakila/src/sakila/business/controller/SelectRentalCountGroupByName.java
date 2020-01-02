package sakila.business.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.RentalDao;

@WebServlet("/business/selectRentalCountGroupByName")
public class SelectRentalCountGroupByName extends HttpServlet {
	private RentalDao rentalDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectRentalCountGroupByName 실행 :::");
		response.setContentType("application/json;charset=UTF-8");
		
		rentalDao = new RentalDao();
		
		int rows = rentalDao.selectRentalCountGroupByName();
		System.out.println("중복 제거한 rows : " + rows);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(rows);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectRentalCountGroupByName 종료 :::");
	}

}

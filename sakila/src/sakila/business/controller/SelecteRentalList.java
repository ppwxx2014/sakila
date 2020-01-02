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

/**
 * Servlet implementation class SelecteRentalList
 */
@WebServlet("/business/selectRentalList")
public class SelecteRentalList extends HttpServlet {
	private RentalDao rentalDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: selecteRentalList 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		rentalDao = new RentalDao();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println("currentPage: "+currentPage);
		
		List<Rental> list = rentalDao.selectRentalList(currentPage);
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: selecteRentalList 종료 :::");
	}
}






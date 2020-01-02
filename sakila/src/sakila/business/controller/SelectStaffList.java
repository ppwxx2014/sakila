package sakila.business.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.Staff;
import sakila.business.model.StaffDao;

/**
 * Servlet implementation class SelectStaffList
 */
@WebServlet("/business/selectStaffList")
public class SelectStaffList extends HttpServlet {
	private StaffDao staffDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: selectStaffList 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		staffDao = new StaffDao();
		List<Staff> list = staffDao.selectStaffList();
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: selectStaffList 종료 :::");
	}

}

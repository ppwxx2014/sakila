package sakila.inventory.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.inventory.model.FilmDao;

@WebServlet("/inventory/selectFilmCount")
public class SelectFilmCount extends HttpServlet {
	private FilmDao filmDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectFilmCount 실행 :::");
		response.setContentType("application/json;charset=UTF-8");
		
		filmDao = new FilmDao();
		
		int rows = filmDao.selectFilmCount();
		System.out.println("중복 제거한 rows : " + rows);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(rows);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectFilmCount 종료 :::");
	}

}

package sakila.inventory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.inventory.model.Film;
import sakila.inventory.model.FilmDao;

@WebServlet("/inventory/selectFilmList")
public class SelectFilmList extends HttpServlet {
	private FilmDao filmDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectFilmList 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		filmDao = new FilmDao();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println("currentPage: " + currentPage);
		
		String rating = request.getParameter("rating");
		System.out.println("rating: " + rating);
		
		List<Film> list = filmDao.selectFilmList(currentPage, rating);
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectFilmList 종료 :::");
	}

}




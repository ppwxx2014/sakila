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

@WebServlet("/inventory/selectFilmRating")
public class SelectFilmRating extends HttpServlet {
	private FilmDao filmDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectFilmRating 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		filmDao = new FilmDao();
		
		List<Film> list = filmDao.filmRating();
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectFilmRating 종료 :::");
	}

}

package sakila.inventory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.inventory.model.FilmActor;
import sakila.inventory.model.FilmActorDao;

@WebServlet("/inventory/filmTopActor")
public class FilmTopActor extends HttpServlet {
	private FilmActorDao filmActorDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: RentalCountTopFilm 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		filmActorDao = new FilmActorDao();
		
		List<FilmActor> list = filmActorDao.selectTopActor();
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: RentalCountTopFilm 종료 :::");
	}

}

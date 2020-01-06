package sakila.inventory.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.inventory.model.Inventory;
import sakila.inventory.model.InventoryDao;

@WebServlet("/business/rentalCountTopFilm")
public class RentalCountTopFilm extends HttpServlet {
	private InventoryDao inventoryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: RentalCountTopFilm 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		inventoryDao = new InventoryDao();
		
		List<Inventory> list = inventoryDao.topRentalFilm();
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: RentalCountTopFilm 종료 :::");
	}

}

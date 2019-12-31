package sakila.business.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.Store;
import sakila.business.model.StoreDao;

/**
 * Servlet implementation class SelectStoreList
 */
@WebServlet("/business/selectStoreList")
public class SelectStoreList extends HttpServlet {
    private StoreDao storeDao;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: selectStoreList 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		storeDao = new StoreDao();
		List<Store> list = storeDao.selectStoreList();
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: selectStoreList 종료 :::");
	}

}







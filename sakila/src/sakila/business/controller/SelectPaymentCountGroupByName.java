package sakila.business.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.PaymentDao;

@WebServlet("/business/selectPaymentCountGroupByName")
public class SelectPaymentCountGroupByName extends HttpServlet {
	private PaymentDao paymentDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectPaymentCountGroupByName 실행 :::");
		response.setContentType("application/json;charset=UTF-8");
		
		paymentDao = new PaymentDao();
		
		int rows = paymentDao.selectPaymentCountGroupByName();
		System.out.println("중복 제거한 rows : " + rows);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(rows);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectPaymentCountGroupByName 종료 :::");
	}

}

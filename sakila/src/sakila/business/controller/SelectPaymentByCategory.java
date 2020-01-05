package sakila.business.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.business.model.Payment;
import sakila.business.model.PaymentDao;

@WebServlet("/index/selectPaymentByCategory")
public class SelectPaymentByCategory extends HttpServlet {
	private PaymentDao paymentDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectPaymentByCategory 실행 :::");
		response.setContentType("application/json;charset=UTF-8");
		
		paymentDao = new PaymentDao();
		
		List<Payment> list = paymentDao.selectPaymentByCategory();
		
		System.out.println("list : " + list.toString());
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectPaymentByCategory 종료 :::");
	}

}

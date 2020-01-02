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

@WebServlet("/business/selectPaymentList")
public class SelectPaymentList extends HttpServlet {
	private PaymentDao paymentDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("::: SelectPaymentList 실행 :::");
		response.setContentType("/application/json;charset=utf-8");
		
		paymentDao = new PaymentDao();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		System.out.println("currentPage: "+currentPage);
		
		List<Payment> list = paymentDao.selectRentalList(currentPage);
		
		System.out.println(list);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		System.out.println(jsonStr);
		
		response.getWriter().write(jsonStr);
		
		System.out.println("::: SelectPaymentList 종료 :::");
	}

}

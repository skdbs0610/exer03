package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import models.AccountDao;

@WebServlet("/signup_check.do")
public class SignupCheckController extends HttpServlet{

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 이 컨트롤러에서 처리결과를 보내는데 ajax의 결과물인 json을 보내야 한다면
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		String id = req.getParameter("id");
		
		AccountDao adao = new AccountDao();
		Map m = adao.getAccountById(id);
		if(m!=null) {
			out.println(true);
		}else {
			out.println(false);
		}

		
		
	
	}
	
}

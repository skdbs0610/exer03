package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.AccountDao;

@WebServlet("/join.do")
public class JoinController extends HttpServlet{
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("���Դ�?");
		
		req.setAttribute("id","");
		req.setAttribute("pass","");
		req.setAttribute("name","");
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
		rd.forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String pass= req.getParameter("pass");
		String name= req.getParameter("name");
		String gender= req.getParameter("gender");
		
		Map map = new HashMap<>();
		map.put("id",id);
		map.put("pass", pass);
		map.put("name",name);
		map.put("gender",gender);
		
		System.out.println(map.toString());
		
		AccountDao adao = new AccountDao();
		int r = adao.addAccount(map);
		if(r==1) {
			HttpSession session = req.getSession();
			session.setAttribute("auth",true);
			session.setAttribute("id", id);
			resp.sendRedirect(req.getContextPath()+"/index.do");
			
		}else {
			/*�׷��� �ʴٸ� �����޼����� �Բ� �ٽ� �ѹ� ������ �����͸� ���� ���...
			(join�� get���� ����ߴ� jsp�� ����)
			+����� �� �ִ� �����͵��� view�� �ٽ� ���ξ���.*/
			req.setAttribute("err",true);
			req.setAttribute("id",id);
			req.setAttribute("pass",pass);
			req.setAttribute("name",name);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/join.jsp");
			rd.forward(req, resp);
		}
		
	}
}

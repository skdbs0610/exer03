package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/index.do")
public class IndexController extends HttpServlet{
	
	/*
	 * 사용자 요청에 의해서 작동시켜야 될 작업을
	 * 	service(HttpServletRequest arg0, HttpServletResponse arg1) : get이던 post이던 모두 지정 가능
	 *  doGet(HttpServletRequest arg0, HttpServletResponse arg1) :get만처리
	 *  doPost(HttpServletRequest arg0, HttpServletResponse arg1) : post만 처리
	 *  
	 *  이 세개중에 하나를 이용해서 구현해두면 된다.
	 *  	
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("auth")==null) {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/guest.jsp");
			rd.forward(req, resp);
			/*
			 * MVC 구축시 response를 전송하기 위해서 설계하는 JSP파일은 
			 * WebContent 바로 아래보다는 WEB-INF안에다 숨겨 설계해둔다.
			 */
		}else {
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
			rd.forward(req, resp);
		}
	}

}

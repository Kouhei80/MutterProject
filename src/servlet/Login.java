package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginLogic;
import model.LoginModel;
import model.User;


@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
//		d.forward(request, response);
//	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		//ログイン処理
		LoginModel loginModel = new  LoginModel(name, pass);
		LoginLogic loginLogic = new LoginLogic();
		boolean isLogin = loginLogic.execute(loginModel);
		//ログイン成功時の処理
		if(isLogin){
			User user = new User(name, pass);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			//ログイン結果画面にフォワード
			RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
			d.forward(request, response);
		}else{
			//リダイレクト
			response.sendRedirect("/toMutterProject/index.jsp");
		}

	}

}

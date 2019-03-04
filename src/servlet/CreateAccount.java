package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;


@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp");
		d.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		if(name.isEmpty()){
			request.setAttribute("err", "名前が入力されていません");
		}else if(pass.isEmpty()){
			request.setAttribute("err","パスワードが入力されていません");
		}else if(name.isEmpty() && pass.isEmpty()){
			request.setAttribute("err", "名前とパスワードが入力されていません");
		}else{
			UserDAO dao = new UserDAO();
			dao.createUser(new User(name,pass));
			request.setAttribute("msg", "登録しました");
		}
		response.sendRedirect("/toMutterProject/");
	}

}

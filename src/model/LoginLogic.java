package model;

import dao.UserDAO;

public class LoginLogic {
	public boolean execute(LoginModel loginModel){
		UserDAO dao = new UserDAO();
		User user = dao.findByAccount(loginModel);
		return user != null;
	}

}

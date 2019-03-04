package test;

import dao.UserDAO;
import model.LoginModel;
import model.User;

public class UserDAOTest {

	public static void main(String[] args) {
		testFindByUser1();//ユーザーが見つかる場合のテスト
		testFindByUser2();//ユーザーが見つからない場合のテスト
	}
	public static void testFindByUser1(){
		LoginModel loginModel = new LoginModel("tanaka","tanaka1234");
		UserDAO dao = new UserDAO();
		User result = dao.findByAccount(loginModel);
		if(result != null &&
			result.getName().equals("tanaka") &&
			result.getPass().equals("tanaka1234")){
			System.out.println("testFindByUser1:成功しました");
		}else{
			System.out.println("testFindByUser1:失敗しました");
		}
	}
	public static void testFindByUser2(){
		LoginModel loginModel = new LoginModel("tanaka","tanaka12345");
		UserDAO dao = new UserDAO();
		User result = dao.findByAccount(loginModel);
		if(result == null){
			System.out.println("testFindByUser2:成功しました");
		}else{
			System.out.println("testFindByUser2:失敗しました");
		}
	}

}

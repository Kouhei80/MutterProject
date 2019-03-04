package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginModel;
import model.User;

public class UserDAO {
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:file:C:/201808AM/data/toMutterProject";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public User findByAccount(LoginModel loginModel){
		Connection conn = null;
		User user = null;
		try{
			//JDBCドライバを読み込む
			Class.forName(DRIVER_NAME);
			//データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//SELECT文を準備
			String sql = "SELECT NAME, PASS FROM LOGIN_ACCOUNT WHERE NAME = ? AND PASS = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginModel.getName());
			pStmt.setString(2, loginModel.getPass());
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();

			//一致したユーザーが存在した場合
			//そのユーザーを表すUserインスタンスを生成
			if(rs.next()){
				String name = rs.getString("NAME");
				String pass = rs.getString("PASS");

				user = new User(name,pass);
			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			//データベースを切断
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//見つかったユーザーまたはnullを返す
		return user;
	}
	public boolean createUser(User user){
		Connection conn = null;
		try{
			//JDBCドライバを読み込む
			Class.forName(DRIVER_NAME);
			//データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//INSERT文の準備(idは自動連番なので指定しなくていい)
			String sql = "INSERT INTO LOGIN_ACCOUNT(NAME, PASS) VALUES(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, user.getName());
			pStmt.setString(2, user.getPass());

			//INSERT文を実行
			int result = pStmt.executeUpdate();

			if(result != 1){
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return false;
		}finally{
			//データベース切断
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return true;
	}

}

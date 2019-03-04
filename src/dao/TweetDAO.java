package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Tweet;

public class TweetDAO {
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:file:C:/201808AM/data/toMutterProject";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public List<Tweet> findAll(){
		Connection conn = null;
		List<Tweet> tweetList = new ArrayList<Tweet>();
		try{
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			//idを降順に
			String sql = "SELECT * FROM TWEET ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();
			//SELECT文の結果をArrayListに格納
			while(rs.next()){
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String text = rs.getString("TEXT");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String updated=sdf.format(rs.getTimestamp("updated"));
				Tweet tweet = new Tweet(id, userName, text, updated);
				tweetList.add(tweet);

			}
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}finally{
			//データベース切断処理
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		return tweetList;
	}

	public boolean create(Tweet tweet){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			//データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			//INSERT文の準備(idは自動連番なので指定しなくていい)
			String sql = "INSERT INTO TWEET(NAME, TEXT, UPDATED) VALUES(?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//INSERT文の中の「？」に使用する値を設定しSQLを完成
			pStmt.setString(1, tweet.getUserName());
			pStmt.setString(2, tweet.getText());
			pStmt.setString(3, tweet.getUpdated());

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

	public boolean deleteOne(int id){
		Connection conn = null;
		try{
			Class.forName(DRIVER_NAME);
			//データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS);
			//delete文を準備
			//idで削除するつぶやきを決める
			String sql = "DELETE FROM TWEET WHERE id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
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

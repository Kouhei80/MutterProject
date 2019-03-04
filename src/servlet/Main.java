package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TweetDAO;
import model.GetTweetListLogic;
import model.Tweet;
import model.TweetPostLogic;
import model.User;

@WebServlet("/Main")
@MultipartConfig(location="C:/tmp",maxFileSize=1048576)
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Tweetリストを取得して、リクエストスコープに保存
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList", tweetList);

		//リクエストパラメーターを取得
		String action = request.getParameter("action");
		TweetDAO dao = new TweetDAO();
		if(action != null && action.equals("delete")){
			dao.deleteOne(Integer.parseInt(request.getParameter("id")));
			request.setAttribute("msg", "1件削除しました。");
		}
		//ログインしているか確認するため
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User loginUser = (User) session.getAttribute("loginUser");

		if(loginUser == null){	//ログインしていない場合
			//リダイレクト
			response.sendRedirect("/toMutterProject/");
		}else{//ログイン済みの場合
			RequestDispatcher d = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			d.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{


		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		String text =  request.getParameter("text");
		//現在日時の取得
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updated = sdf.format(date);
		//入力値チェック
		if(text != null && text.length() != 0){

			//セッションスコープに保存されたユーザー情報を取得
			HttpSession session = request.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			//つぶやきをTweetListに追加
			Tweet tweet = new Tweet(loginUser.getName(), text, updated);
			TweetPostLogic tweetPostLogic = new TweetPostLogic();
			tweetPostLogic.execute(tweet);

		}else{
		//エラーメッセージをリクエストスコープに保存
			request.setAttribute("errorMsg", "エラー！入力されていません");
		}
		//Tweetリストを取得して、リクエストスコープに保存
		GetTweetListLogic getTweetListLogic = new GetTweetListLogic();
		List<Tweet> tweetList = getTweetListLogic.execute();
		request.setAttribute("tweetList", tweetList);

		//main.jspにフォワード
		RequestDispatcher d = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
		d.forward(request, response);
	}
}

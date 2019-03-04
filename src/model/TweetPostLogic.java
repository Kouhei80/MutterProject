package model;

import dao.TweetDAO;

public class TweetPostLogic {
	public void execute(Tweet tweet){
		TweetDAO dao = new TweetDAO();
		dao.create(tweet);
	}

}

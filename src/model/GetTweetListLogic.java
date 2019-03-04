package model;

import java.util.List;

import dao.TweetDAO;

public class GetTweetListLogic {

	public List<Tweet> execute(){
		TweetDAO dao = new TweetDAO();
		List<Tweet> tweetList = dao.findAll();
		return tweetList;
	}

}

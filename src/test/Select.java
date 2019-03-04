package test;

import java.util.List;

import dao.TweetDAO;
import model.Tweet;

public class Select {

	public static void main(String[] args) {
		TweetDAO dao = new TweetDAO();
		List<Tweet> tweetList = dao.findAll();
		for(Tweet tweet : tweetList){
			System.out.println(tweet.getUserName());
		}
	}

}

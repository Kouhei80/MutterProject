package model;

import java.io.Serializable;

public class Tweet implements Serializable{
	private int id;		  //id
	private String userName; //ユーザー名
	private String text;     //つぶやき内容
	private String updated;	  //日時
	//コンストラクタ
	public Tweet() {
		super();
	}


	public Tweet(String userName, String text, String updated) {
		super();
		this.userName = userName;
		this.text = text;
		this.updated = updated;
	}


	public Tweet(int id, String userName, String text, String updated) {
		super();
		this.id = id;
		this.userName = userName;
		this.text = text;
		this.updated = updated;
	}

	//getter
	public String getUserName() {
		return userName;
	}
	public String getText() {
		return text;
	}

	public int getId() {
		return id;
	}


	public String getUpdated() {
		return updated;
	}

}
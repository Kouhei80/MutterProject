package model;

import java.io.Serializable;

public class User implements Serializable{
	private String name; //ユーザー名
	private String pass; //パスワード
	//コンストラクタ
	public User() {
		super();
	}
	public User(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	//getter
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}


}

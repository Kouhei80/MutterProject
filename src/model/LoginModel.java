package model;

public class LoginModel {
	private String name;
	private String pass;
	public LoginModel(String name, String pass) {
		super();
		this.name = name;
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}

}

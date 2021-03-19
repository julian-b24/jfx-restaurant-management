package model;

public class SystemUser extends Employee{

	private String userName;			//employee username
	private String password;			//employee password
	
	public SystemUser(String nam, String lastN, String ccP, String user, String passw, int id) {
		
		super(nam, lastN, ccP, id);
		userName = user;
		password = passw;
		
	}
	
	//Getters and setters

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

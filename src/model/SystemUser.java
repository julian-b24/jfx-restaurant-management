package model;

public class SystemUser extends Employee{

	private String userName;			//employee username
	private String password;			//employee password
	
	public SystemUser(String nam, String lastN, String emplId, String user, String passw) {
		
		super(nam, lastN, emplId);
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

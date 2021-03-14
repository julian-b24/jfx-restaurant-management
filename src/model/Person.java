package model;

public class Person {
	
	protected String name;					//name of the person
	protected String lastName;				//lastname of the person
	protected String cc;					//cc of the person
	
	public Person(String nam, String lastN, String ccP) {
		
		name = nam;
		lastName = lastN;
		cc = ccP;
	}

	//Getters and setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String id) {
		this.cc = id;
	}

	
	
	
}

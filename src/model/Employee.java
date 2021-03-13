package model;

public class Employee extends Person{

	protected String employeeId;			//employee unique id
	
	public Employee(String nam, String lastN, String emplId) {
		
		super(nam, lastN);
		employeeId = emplId;
	}

	//Getter and setters
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
	
}

package model;

public class Employee extends Person {

	private static final long serialVersionUID = 1L;
	
	protected int employeeId;			//employee unique id
	
	public Employee(String nam, String lastN, String ccP, int id) {
		
		super(nam, lastN, ccP);
		employeeId = id+1;
	}

	//Getter and setters
	
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public int compareById(Employee employee) {
		return employeeId - employee.getEmployeeId();
	}
	
}

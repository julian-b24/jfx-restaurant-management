package model;

public class Client extends Person{
	
	private String adress;				//client adress
	private String phone;				// client contact phone
	private String obsField;			//client observation field
	
	public Client(String nam, String lastN, String adrs, String phn, String obs) {
		
		super(nam, lastN);
		adress = adrs;
		phone = phn;
		obsField = obs;
		
	}
	
	//Getters and setters

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getObsField() {
		return obsField;
	}

	public void setObsField(String obsField) {
		this.obsField = obsField;
	}
	
	
	
	

	
}

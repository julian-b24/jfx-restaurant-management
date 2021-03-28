package model;

import java.io.Serializable;

public class Client extends Person implements Comparable<Client>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String adress;				//client address
	private String phone;				// client contact phone
	private String obsField;			//client observation field
	
	public Client(String nam, String lastN, String ccP, String adrs, String phn, String obs) {
		
		super(nam, lastN, ccP);
		adress = adrs;
		phone = phn;
		obsField = obs;
		
	}
	
	@Override
	public int compareTo(Client otherClient) {
		return (lastName+name).compareTo(otherClient.getLastName()+otherClient.getName());
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

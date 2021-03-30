package model;

import java.io.Serializable;

public abstract class Saleable implements Serializable{
	
	private static final long serialVersionUID = 1L;

	//WARNING CONSTANTS NOT IN THE CLASS DIAGRAM
	public final static boolean DEAFULT_AVAILABLE = true;		//When a saleable is created, it is by deafult available
	
	protected String name;			//Name of the saleable
	private String creatorRef;		//Id of the user that creates the saleable
	private String lastEditorRef;	//Id of the last editor
	private int code;				//Code of the saleable
	private boolean available;		//State of the product, true in case is enable, false in any other way
	private String availableS;		//String with the available boolean. For the tables
	
	public Saleable(String nm, String creatRef, String lastE, int lastCode) {
		name = nm;
		creatorRef = creatRef;
		lastEditorRef = lastE;
		code = lastCode + 1;
		available = DEAFULT_AVAILABLE;
		availableS = "S�";
	}

	//Getters & setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAvailableS() {
		
		if(available) {
			this.availableS = "s�";
		}else {
			this.availableS = "No";
		}
		return availableS;
	}

	public void setAvailableS(String availableS) {
		this.availableS = availableS;
	}

	public String getLastEditorRef() {
		return lastEditorRef;
	}

	public void setLastEditorRef(String lastEditorRef) {
		this.lastEditorRef = lastEditorRef;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public String getCreatorRef() {
		return creatorRef;
	}

	public void setCreatorRef(String creatorRef) {
		this.creatorRef = creatorRef;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	//Editable methods
	/**
	 * enable, sets the field "available" of a saleable in true.
	 * <b> pos: </b> The field available is setted as true <br>
	 */
	public static void enable() {}
	
	/**
	 * disable, sets the field "available" of a saleable in false.
	 * <b> pos: </b> The field available is setted as false <br>
	 */
	public static void disable() {}

}

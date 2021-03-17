package model;

public abstract class Saleable {
	
	//WARNING CONSTANTS NOT IN THE CLASS DIAGRAM
	public final static boolean DEAFULT_REFERENCE = false;		//When a saleable is created, it is by deafult not referenced
	public final static boolean DEAFULT_AVAILABLE = true;		//When a saleable is created, it is by deafult available
	
	private String name;			//Name of the saleable
	private String lastEditorRef;	//Id of the last editor
	private int code;				//Code of the saleable
	private boolean productRef;		//Reference of the saleable in any other product, true if is referenced, false in case of it's not referenced
	private boolean available;		//State of the product, true in case is enable, false in any other way
	
	public Saleable(String nm, String lastE, int lastCode) {
		name = nm;
		lastEditorRef = lastE;
		code = lastCode + 1;
		productRef = DEAFULT_REFERENCE;
		available = DEAFULT_AVAILABLE;
	}

	//Getters & setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastEditorRef() {
		return lastEditorRef;
	}

	public void setLastEditorRef(String lastEditorRef) {
		this.lastEditorRef = lastEditorRef;
	}

	public boolean isProductRef() {
		return productRef;
	}

	public void setProductRef(boolean productRef) {
		this.productRef = productRef;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}

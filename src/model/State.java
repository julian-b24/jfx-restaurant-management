package model;

public enum State {
	
	REQUESTED ("Solicitado"), PROCESS ("En proceso"),
	DELIVERED ("Enviado"), RECIEVED ("Entregado");
	
	private String state;
	
	private State(String stte) {
		state = stte;
	}
	
	public String getState() {
		return state;
	}

}

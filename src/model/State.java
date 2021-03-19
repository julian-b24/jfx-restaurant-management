package model;

public enum State {
	REQUESTED ("Solicitado", 0), PROCESS ("En proceso", 1),
	DELIVERED ("Enviado", 2), RECIEVED ("Entregado", 3);
	
	//Unnecessary implementation, check it according to the advance of the program.
	private String state;
	private int process;
	
	private State(String stte, int procss) {
		state = stte;
		process = procss;
	}
	
	public String getState() {
		return state;
	}
	
	public int getProcess() {
		return process;
	}
}

package Components;

public class Ticket {
	private int number;
	private Process process;
	
	public Ticket(int number, Process process) {
		this.number = number;
		this.process = process;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Process getProcess() {
		return process;
	}

	public void setProcess(Process process) {
		this.process = process;
	}
	
}

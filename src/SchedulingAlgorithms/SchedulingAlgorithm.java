package SchedulingAlgorithms;

import java.util.ArrayList;
import Components.Process;

public abstract class SchedulingAlgorithm {
	
	ArrayList<Process> processes;
	
	public SchedulingAlgorithm() {
		this.processes = new ArrayList<Process>();
	}
	
	public SchedulingAlgorithm(ArrayList<Process> processes) {
		this.processes = processes;
	}

	public abstract Process[] simulate();
	
}

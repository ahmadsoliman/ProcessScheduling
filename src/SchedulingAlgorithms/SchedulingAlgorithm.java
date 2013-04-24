package SchedulingAlgorithms;

import java.util.ArrayList;
import Components.Process;
import Components.User;

public abstract class SchedulingAlgorithm {

	ArrayList<Process> processes;
	ArrayList<User> users;

	public SchedulingAlgorithm() {
		this.processes = new ArrayList<Process>();
		this.users = new ArrayList<User>();
	}

	public SchedulingAlgorithm(ArrayList<Process> processes,
			ArrayList<User> users) {
		this.processes = processes;
		this.users = users;
	}

	public User getUserByName(String name) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserName().equals(name))
				return users.get(i);
		}
		return null;
	}

	public abstract Process[] simulate();

	public ArrayList<Process> getProcesses() {
		return processes;
	}

	public void setProcesses(ArrayList<Process> processes) {
		this.processes = processes;
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

}

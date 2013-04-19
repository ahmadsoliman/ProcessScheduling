package SchedulingAlgorithms;

import java.util.ArrayList;
import java.util.HashMap;

import Components.Process;
import Components.Ticket;
import Components.User;

public class FairShare extends SchedulingAlgorithm {

	public Process[] simulate() {
		if (processes.size() == 0) {
			// System.out.println("No processes to simulate!");
			return null;
		}
		Process.arrangeWithArrivalTimes(processes);

		int totalRuntime = 0;
		User tmpUsr;
		ArrayList<Process> tmpProcess = new ArrayList<Process>(processes.size());
		HashMap<User, ArrayList<Process>> map = new HashMap<User, ArrayList<Process>>();

		for (int i = 0; i < processes.size(); i++) {
			totalRuntime += processes.get(i).getRunTime();
			tmpProcess.add(processes.get(i).clone());
			tmpUsr = getUserByName(tmpProcess.get(i).getUserName());
		}

		int totalPrior = 0;
		for (int i = 0; i < users.size(); i++) {
			totalPrior += users.get(i).getPriority();
		}

		Process[] timeSlots = new Process[totalRuntime];
		int curTime = 0;

		return timeSlots;
	}

}

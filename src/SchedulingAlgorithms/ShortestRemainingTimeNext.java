package SchedulingAlgorithms;

import java.util.ArrayList;

import Components.Process;
import Components.User;

public class ShortestRemainingTimeNext extends SchedulingAlgorithm {

	public ShortestRemainingTimeNext() {
		super();
	}

	public ShortestRemainingTimeNext(ArrayList<Process> processes,
			ArrayList<User> users) {
		super(processes, users);
	}

	public Process[] simulate() {
		if (processes.size() == 0) {
			// System.out.println("No processes to simulate!");
			return null;
		}
		Process.arrangeWithArrivalTimes(processes);

		int totalRuntime = 0;
		ArrayList<Process> tmp = new ArrayList<Process>(processes.size());
		for (int i = 0; i < processes.size(); i++) {
			totalRuntime += processes.get(i).getRunTime();
			tmp.add(processes.get(i).clone());
		}
		
		Process[] timeSlots = new Process[totalRuntime];
		int recentProcess = 0;
		Process assignedProcess = tmp.get(0);
		timeSlots[assignedProcess.getArrivalTime()] = tmp.get(0);
		tmp.get(0).setRunTime(tmp.get(0).getRunTime() - 1);
		for (int i = assignedProcess.getArrivalTime()+1; i < totalRuntime; i++) {
			if (recentProcess < tmp.size()-1
					&& tmp.get(recentProcess + 1).getArrivalTime() <= i) {
				recentProcess++;
				Process shortestRemainingProcess = tmp.get(0);
				for (int j = 1; j < tmp.size(); j++) {
					if (tmp.get(j).getRunTime() < shortestRemainingProcess
							.getRunTime()) {
						shortestRemainingProcess = tmp.get(j);
					}
				}
				assignedProcess = shortestRemainingProcess;
			}
			if (assignedProcess.getRunTime() == 0) {
				recentProcess--;
				tmp.remove(assignedProcess);
				Process shortestRemainingProcess = tmp.get(0);
				for (int j = 1; j < tmp.size(); j++) {
					if (tmp.get(j).getRunTime() < shortestRemainingProcess
							.getRunTime()) {
						shortestRemainingProcess = tmp.get(j);
					}
				}
				assignedProcess = shortestRemainingProcess;
			}
			timeSlots[i] = assignedProcess;
			assignedProcess.setRunTime(assignedProcess.getRunTime() - 1);
		}
		return timeSlots;
	}
}

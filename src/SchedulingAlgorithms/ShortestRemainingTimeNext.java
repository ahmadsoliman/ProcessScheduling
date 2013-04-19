package SchedulingAlgorithms;

import java.util.ArrayList;

import Components.Process;

public class ShortestRemainingTimeNext extends SchedulingAlgorithm {

	public Process[] simulate() {
		if (processes.size() == 0) {
			//System.out.println("No processes to simulate!");
			return null;
		}
		Process.arrangeWithArrivalTimes(processes);
		
		int totalRuntime = 0;
		Process[] timeSlots = new Process[totalRuntime];
		ArrayList<Process> tmp = new ArrayList<Process>(processes.size());
		for (int i = 0; i < processes.size(); i++) {
			totalRuntime += processes.get(i).getRunTime();
			tmp.add(processes.get(i).clone());
		}

		int recentProcess = 0;
		Process assignedProcess = tmp.get(0);
		timeSlots[0] = tmp.get(0);
		tmp.get(0).setRunTime(tmp.get(0).getRunTime() - 1);
		for (int i = 1; i < totalRuntime; i++) {
			if (recentProcess < processes.size()
					&& tmp.get(recentProcess + 1).getArrivalTime() >= i) {
				recentProcess++;
				Process shortestRemainingProcess = tmp.get(0);
				for (int j = 1; j < tmp.size(); j++) {
					if(tmp.get(i).getRunTime() < shortestRemainingProcess.getRunTime()){
						shortestRemainingProcess = tmp.get(i);
					}
				}
				assignedProcess = shortestRemainingProcess;
			}
			if(assignedProcess.getRunTime()==0){
				recentProcess--;
				tmp.remove(assignedProcess);
				Process shortestRemainingProcess = tmp.get(0);
				for (int j = 1; j < tmp.size(); j++) {
					if(tmp.get(i).getRunTime() < shortestRemainingProcess.getRunTime()){
						shortestRemainingProcess = tmp.get(i);
					}
				}
				assignedProcess = shortestRemainingProcess;
			}
			timeSlots[i] = assignedProcess;
			assignedProcess.setRunTime(assignedProcess.getRunTime()-1);
		}
		return timeSlots;
	}
}

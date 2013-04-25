package SchedulingAlgorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Components.Process;
import Components.Ticket;
import Components.User;

public class FairShare extends SchedulingAlgorithm {

	public FairShare() {
		super();
	}

	public FairShare(ArrayList<Process> processes, ArrayList<User> users) {
		super(processes, users);
	}

	public Process[] simulate() {
		if (processes.size() == 0) {
			// System.out.println("No processes to simulate!");
			return null;
		}
		Process.arrangeWithArrivalTimes(processes);

		int totalRuntime = 0;
		User tmpUsr;
		ArrayList<Process> tmpProcesses = new ArrayList<Process>(
				processes.size());
		ArrayList<Process>[] userProcesses = new ArrayList[users.size()];

		for (int i = 0; i < users.size(); i++) {
			userProcesses[i] = new ArrayList<Process>();
			users.get(i).setIndex(i);
		}

		for (int i = 0; i < processes.size(); i++) {
			totalRuntime += processes.get(i).getRunTime();
			tmpProcesses.add(processes.get(i).clone());
			tmpUsr = getUserByName(tmpProcesses.get(i).getUserName());
		}

		Process[] timeSlots = new Process[totalRuntime];

		ArrayList<Process> tup; // tmp user processes

		int i = 0, recentProcess = -1;

		while (i < totalRuntime) {
			// if a new process arrived add it
			if (recentProcess < tmpProcesses.size() - 1
					&& tmpProcesses.get(recentProcess + 1).getArrivalTime() <= i) {
				recentProcess++;
				tmpUsr = getUserByName(tmpProcesses.get(recentProcess)
						.getUserName());
				userProcesses[tmpUsr.getIndex()].add(tmpProcesses
						.get(recentProcess));
			}
			
			//calculate priorities
			int gcd = -1;
			for (int j = 0; j < users.size(); j++) {
				if (userProcesses[users.get(j).getIndex()].size() > 0) {
					if (gcd == -1)
						gcd = users.get(j).getPriority();
					else
						gcd = gcd(gcd, users.get(j).getPriority());
				}
			}

			for (int j = 0; j < users.size(); j++) {
				if (userProcesses[users.get(j).getIndex()].size() > 0) {
					users.get(j)
							.setTmpPriority((users.get(j).getPriority()) / gcd);
				}
			}
			
			for (int j = 0; j < users.size(); j++) {
				tmpUsr = users.get(j);
				for (int j2 = 0; j2 < tmpUsr.getTmpPriority();) {
					//if a new process arrives break to add it 
					if (recentProcess < tmpProcesses.size() - 1
							&& tmpProcesses.get(recentProcess + 1)
									.getArrivalTime() <= i) {
						break;
					}
					tup = userProcesses[tmpUsr.getIndex()];
					int tmpJ2 = j2;
					for (int k = 0; k < tup.size(); k++) {
						Process tmpProcess = tup.get(k);
						if (tmpProcess.getRunTime() == 0)
							continue;
						timeSlots[i++] = tmpProcess;
						tmpProcess.setRunTime(tmpProcess.getRunTime() - 1);
						if (tmpProcess.getRunTime() > 0) {
							timeSlots[i++] = tmpProcess;
							tmpProcess.setRunTime(tmpProcess.getRunTime() - 1);
						}
						j2++;
						if(tmpProcess.getRunTime()==0) {
							//calculate priorities
							gcd = -1;
							for (int r = 0; r < users.size(); r++) {
								if (userProcesses[users.get(r).getIndex()].size() > 0) {
									if (gcd == -1)
										gcd = users.get(r).getPriority();
									else
										gcd = gcd(gcd, users.get(r).getPriority());
								}
							}

							for (int r = 0; r < users.size(); r++) {
								if (userProcesses[users.get(r).getIndex()].size() > 0) {
									users.get(r)
											.setTmpPriority((users.get(r).getPriority()) / gcd);
								}
							}
						}
						if (j2 >= tmpUsr.getTmpPriority())
							break;
					}
					if (tmpJ2 == j2)
						break;
				}
			}
		}

		return timeSlots;
	}

	public int gcd(int a, int b) {
		if (a == 0)
			return b;
		return gcd(b % a, a);
	}

}

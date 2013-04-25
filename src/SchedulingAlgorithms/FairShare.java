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

		int totalRuntime = 0, totalPriority = 0;
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
		int[] lpu = new int[users.size()];// latest process per user

		Queue<Process> queue = new LinkedList<Process>();

		int i = 0, recentProcess = -1;


		while (i < totalRuntime) {
			if (recentProcess < tmpProcesses.size() - 1
					&& tmpProcesses.get(recentProcess + 1).getArrivalTime() <= i) {
				recentProcess++;
				tmpUsr = getUserByName(tmpProcesses.get(recentProcess)
						.getUserName());
				userProcesses[tmpUsr.getIndex()].add(tmpProcesses
						.get(recentProcess));

			}
			
		}
		
		while (i < totalRuntime) {
			if (recentProcess < tmpProcesses.size() - 1
					&& tmpProcesses.get(recentProcess + 1).getArrivalTime() <= i) {
				recentProcess++;
				tmpUsr = getUserByName(tmpProcesses.get(recentProcess)
						.getUserName());
				userProcesses[tmpUsr.getIndex()].add(tmpProcesses
						.get(recentProcess));

			}
			for (int j = 0; j < users.size(); j++) {
				for (int j2 = 0; j2 < users.get(j).getPriority(); j2++) {
					if (userProcesses[users.get(j).getIndex()].size() == 0)
						continue;
					int start = lpu[users.get(j).getIndex()];
					Process tmpProcess = null;
					while (tmpProcess == null || tmpProcess.getRunTime() == 0) {
						tmpProcess = userProcesses[users.get(j).getIndex()]
								.get(lpu[users.get(j).getIndex()]);
						lpu[users.get(j).getIndex()] = (lpu[users.get(j)
								.getIndex()] + 1)
								% userProcesses[users.get(j).getIndex()].size();
						if (lpu[users.get(j).getIndex()] == start) {
							tmpProcess = null;
							break;
						}
					}
					if (tmpProcess == null)
						break;

					timeSlots[i++] = tmpProcess;
					tmpProcess.setRunTime(tmpProcess.getRunTime() - 1);
					// j2++;
					if (tmpProcess.getRunTime() > 0) {
						timeSlots[i++] = tmpProcess;
						tmpProcess.setRunTime(tmpProcess.getRunTime() - 1);
					} else {
						// j2--;
					}
					if (tmpProcess.getRunTime() == 0) {
						// userProcesses[users.get(j).getIndex()].remove(tmpProcess);
						if (userProcesses[users.get(j).getIndex()].size() == 0) {

						}
					}
				}
			}
		}

		return timeSlots;
	}
}

package SchedulingAlgorithms;

import java.util.ArrayList;
import java.util.Random;

import Components.Process;
import Components.Ticket;
import Components.User;

public class Lottery extends SchedulingAlgorithm {

	public Lottery() {
		super();
	}

	public Lottery(ArrayList<Process> processes, ArrayList<User> users) {
		super(processes, users);
	}

	public Process[] simulate() {
		if (processes.size() == 0) {
			// System.out.println("No processes to simulate!");
			return null;
		}
		Process.arrangeWithArrivalTimes(processes);

		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		int totalRuntime = 0, nTickets = 0;
		User tmpUsr;
		ArrayList<Process> tmpProcesses = new ArrayList<Process>(
				processes.size());

		for (int i = 0; i < processes.size(); i++) {
			totalRuntime += processes.get(i).getRunTime();
			tmpProcesses.add(processes.get(i).clone());
		}

		Process[] timeSlots = new Process[totalRuntime];
		int curTime = 0, recentProcess = -1;

		Random rand = new Random();
		do {
			if (recentProcess < tmpProcesses.size() - 1
					&& tmpProcesses.get(recentProcess + 1).getArrivalTime() <= curTime) {
				recentProcess++;
				tmpUsr = getUserByName(tmpProcesses.get(recentProcess)
						.getUserName());
				for (int j = 0; j < tmpUsr.getPriority(); j++) {
					tickets.add(new Ticket(nTickets++, tmpProcesses
							.get(recentProcess)));
				}
			}
			if (tickets.size() == 0)
				curTime++;
			else {
				int r = rand.nextInt(tickets.size());
				Process p = tickets.get(r).getProcess();
				timeSlots[curTime++] = p;
				p.setRunTime(p.getRunTime() - 1);
				if (p.getRunTime() > 0) {
					timeSlots[curTime++] = p;
					p.setRunTime(p.getRunTime() - 1);
				}
				if (p.getRunTime() == 0) {
					for (int i = 0; i < tickets.size(); i++) {
						if (tickets.get(i).getProcess().equals(p))
							tickets.remove(i--);
					}
					System.out.println(tickets.size());
				}
			}
		} while (tickets.size() > 0 || recentProcess < tmpProcesses.size()-1);

		return timeSlots;
	}

}

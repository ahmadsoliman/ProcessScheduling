package SchedulingAlgorithms;

import java.util.ArrayList;
import java.util.Random;

import Components.Process;
import Components.Ticket;
import Components.User;

public class Lottery extends SchedulingAlgorithm {

	public Process[] simulate() {
		if (processes.size() == 0) {
			// System.out.println("No processes to simulate!");
			return null;
		}
		Process.arrangeWithArrivalTimes(processes);
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		int totalRuntime = 0, nTickets = 0;
		User tmpUsr;
		ArrayList<Process> tmpProcess = new ArrayList<Process>(processes.size());
		
		for (int i = 0; i < processes.size(); i++) {
			totalRuntime += processes.get(i).getRunTime();
			tmpProcess.add(processes.get(i).clone());		
			tmpUsr = getUserByName(tmpProcess.get(i).getUserName());
			for (int j = 0; j < tmpUsr.getPriority(); j++) {
				tickets.add(new Ticket(nTickets++, tmpProcess.get(i)));
			}
		}
		
		Process[] timeSlots = new Process[totalRuntime];
		int curTime=0; 
		
		Random rand = new Random();
		while(tickets.size()>0){
			int r = rand.nextInt(tickets.size());
			Process p = tickets.get(r).getProcess();
			timeSlots[curTime++] = p;
			p.setRunTime(p.getRunTime()-1);
			if(p.getRunTime()==0){
				for (int i = 0; i < tickets.size(); i++) {
					if(tickets.get(i).getProcess().equals(p))
						tickets.remove(i--);
				}
			}
		}

		return timeSlots;
	}

}

package Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import SchedulingAlgorithms.FairShare;
import SchedulingAlgorithms.Lottery;
import SchedulingAlgorithms.ShortestRemainingTimeNext;

import Components.User;
import Components.Process;

public class Simulation {

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out
				.println("Please enter the number of the algorithm you want to simulate:");
		System.out.println("  [1] Shortest Remaining Time Next");
		System.out.println("  [2] Lottery");
		System.out.println("  [3] Fair Share");

		int choice = 0;
		while (true) {
			try {
				choice = Integer.parseInt(br.readLine());
				break;
			} catch (Exception e) {
			}
		}

		System.out.println("Please enter the number of users:");
		int nUsers = 0;
		while (true) {
			try {
				nUsers = Integer.parseInt(br.readLine());
				break;
			} catch (Exception e) {
			}
		}

		ArrayList<User> users = new ArrayList<User>();
		System.out.println("Please enter " + nUsers + " user pair"
				+ ((nUsers > 1) ? "s" : "")
				+ ", \"Username Priority\" (quotes for clarity):");
		for (int i = 0; i < nUsers; i++) {
			String[] comps;
			try {
				comps = br.readLine().split(" ");
				users.add(new User(comps[0], Integer.parseInt(comps[1])));
			} catch (Exception e) {
				i--;
				continue;
			}

		}

		System.out.println("Please enter the number of processes:");
		int nProcesses = 0;
		while (true) {
			try {
				nProcesses = Integer.parseInt(br.readLine());
				break;
			} catch (Exception e) {
			}
		}

		ArrayList<Process> processes = new ArrayList<Process>();
		System.out.println("Please enter " + nProcesses + " process"
				+ ((nProcesses > 1) ? "es" : "")
				+ " as, \"ProcessName ArrivalTime Runtime UserName\":");
		for (int i = 0; i < nProcesses; i++) {
			String[] comps;
			try {
				comps = br.readLine().split(" ");
				processes.add(new Process(comps[0], Integer.parseInt(comps[1]),
						Integer.parseInt(comps[2]), comps[3]));
			} catch (Exception e) {
				i--;
				continue;
			}
		}

		switch (choice) {
		case 1:
			simulateShortest(users, processes);
			break;
		case 2:
			simulateLottery(users, processes);
			break;
		case 3:
			simulateFairShare(users, processes);
			break;
		default:
			break;
		}
	}

	public static void simulateFairShare(ArrayList<User> users,
			ArrayList<Process> processes) {
		FairShare sim = new FairShare(processes, users);

		System.out.println("Simulating..\n");
		printSim(sim.simulate());
	}

	public static void simulateLottery(ArrayList<User> users,
			ArrayList<Process> processes) {
		Lottery sim = new Lottery(processes, users);

		System.out.println("Simulating..\n");
		printSim(sim.simulate());
	}

	public static void simulateShortest(ArrayList<User> users,
			ArrayList<Process> processes) {
		ShortestRemainingTimeNext sim = new ShortestRemainingTimeNext(
				processes, users);

		System.out.println("Simulating..\n");
		printSim(sim.simulate());
	}

	public static void printSim(Process[] timeSlots) {
		System.out.println(" Time Slot    User Name    Process Name ");
		System.out.println("----------------------------------------");
		for (int i = 0; i < timeSlots.length; i++) {
			if (timeSlots[i] != null)
				System.out.println("  " + i + "        "
						+ timeSlots[i].getUserName() + "        "
						+ timeSlots[i].getProcessName());
		}
	}
}

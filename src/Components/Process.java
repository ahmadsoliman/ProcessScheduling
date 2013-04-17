package Components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Process {
	private String processName;
	private long arrivalTime;
	private long runTime;
	private String userName;

	public Process(String processName, long arrivalTime, long runTime,
			String userName) {
		super();
		this.processName = processName;
		this.arrivalTime = arrivalTime;
		this.runTime = runTime;
		this.userName = userName;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public long getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(long arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long runTime) {
		this.runTime = runTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean equals(Process p) {
		return this.processName.equals(p.processName)
				&& this.arrivalTime == p.arrivalTime;
	}

	public Process clone() {
		return new Process(processName, arrivalTime, runTime, userName);
	}

	public static void arrangeWithArrivalTimes(ArrayList<Process> processes) {
		Collections.sort(processes, new Comparator<Process>() {

			public int compare(Process o1, Process o2) {
				if (o1.getArrivalTime() < o2.getArrivalTime())
					return -1;
				else if (o1.getArrivalTime() > o2.getArrivalTime())
					return 1;
				return 0;
			}

		});
	}
}

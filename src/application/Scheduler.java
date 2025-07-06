package application;

import java.util.ArrayList;

public class Scheduler {

	public static ArrayList<Process> Processes = new ArrayList<Process>(); // this will be the arraylist that the
																			// Processes will be Randomized in
	public static ArrayList<Process> ReadyQueue; // This ArrayList will act as the ReadyQueue of the CPU


	// First Scheduling Algorithm we will Implement will be the Shortest Remaining
	// Time First
	// This Mehthod will Return An AraayList That represents the Guantss Chart
	// we will need a method that calculates the Remaining Time for each process and
	// selects the shortest remaining one
	public static Process FindLeastRemainingTimeProcess() { // function to find the process that has minimum remaining
															// running time
		int minRemainingTime = Integer.MAX_VALUE; // initilize minBurstTime to a very large number
		Process leastRemainingTimeProcess = null;
		for (Process p : ReadyQueue) {
			if (p.remainingTime < minRemainingTime) { // if process p has less remaining running time than
														// leastRemainingTimeProcess
				leastRemainingTimeProcess = p; // make p the leastRemainingTimeProcess
				minRemainingTime = p.remainingTime;
			}
		}
		return leastRemainingTimeProcess; // return the leastRemainingTimeProcess
	}
	// this next method will check if a process has just arrived and add it to the
	// ready queue
	public static void checkProcessesArrival(int time) { // check if any process has arrived at the given time
		for (Process p : Processes)
			if (p.arrivalTime == time) // if a process arrived at the given time
				ReadyQueue.add(p); // then add it to the readyQueue
	}

	// this next method will check if all the processes are over
	public static boolean allProcessesFinished() { // function to check if all process has finished or not
		for (Process p : Processes)
			if (p.finishTime == -1) // if any process has finish time = -1, then it hasn't finished yet
				return false;
		return true;
	}

	// Next method will be used to add the process to the GANTT CHART
	public static void addToGanttChart(ArrayList<Integer> ganttChart, Process currentlyRunning) { // function to add a
		// process's pid to Gantt
		// Chart
		if (currentlyRunning != null) // if there is a process running at the time the function has called
		{
			ganttChart.add(currentlyRunning.pid); // add this process's pid to Gantt Chart
                        

		} else
			ganttChart.add(-1); // add -1, indicating no process was running at this time
	}
	// ----------------------------------------------------------------------------------------------------
	// this is the main SRTF method

	public static ArrayList<Integer> SRTF() {
		int time = 0; // current time counter that starts at 0 ms
		Process currentlyRunning = null; // the process that is running currently, initially null
		ReadyQueue = new ArrayList<Process>(); // ready queue to add arrived processes that are ready to run

		//// some stuff has to be changed here
		ArrayList<Integer> ganttChart = new ArrayList<Integer>(); // Gantt Chart to show processes run-time

		while (!allProcessesFinished()) { // while there are still unfinished processes
			checkProcessesArrival(time); // check if any process has arriced at current moment

			if (currentlyRunning != null) // if a process is running currently
				currentlyRunning.remainingTime--; // decrement its remaining running time

			Process leastRemainingTimeProcess = FindLeastRemainingTimeProcess(); // find the process that has minimum
																					// remaining running time
			currentlyRunning = leastRemainingTimeProcess; // and then run it, preemiting the previous running process,if
															// any

			if (currentlyRunning != null && currentlyRunning.startTime == -1) // if a process has just started to run,
				currentlyRunning.startTime = time; // then set its start time to current time

			if (currentlyRunning != null && currentlyRunning.remainingTime == 0) { // if remaining running time for a
																					// currently running process is 0,
																					// then this means that the process
																					// has finished
				currentlyRunning.finishTime = time; // set finish time to current time
				currentlyRunning.turnaround = currentlyRunning.finishTime - currentlyRunning.arrivalTime; // compute
																											// turnaround
																											// time
				currentlyRunning.waitingTime = currentlyRunning.turnaround - currentlyRunning.CPUBurst; // compute
																										// waiting
																										// time

				int currentlyRunningIdx = ReadyQueue.indexOf(currentlyRunning); // find the index of the finished
																				// process
				ReadyQueue.remove(currentlyRunningIdx); // remove the finished process from the readyQueue
				currentlyRunning = null;

				/*
				 * starting another process, just at the same time the previous process has
				 * finished
				 */
				leastRemainingTimeProcess = FindLeastRemainingTimeProcess(); // find the process that has minimum
																				// remaining running time
				currentlyRunning = leastRemainingTimeProcess; // and then run it

				if (currentlyRunning != null && currentlyRunning.startTime == -1) // if a process has just started to
																					// run,
					currentlyRunning.startTime = time; // then set its start time to current time

			}

			addToGanttChart(ganttChart, currentlyRunning); // add currently running process at the current moment to
															// Gantt Chart
			time++; // increment time
		}

		return ganttChart;
	}
	// --------------------------------------------------------------------------------------------------------------

	// --------------------------------------------------------------------------------------------------------------
	// Before Implementing SJF , this method is used to find the process with the
	// least Burst Time ;

	public static Process findLeastBurstTimeProcess() { // function to find the process that has minimum burst time
		int minBurstTime = Integer.MAX_VALUE; // initilize minBurstTime to a very large number
		Process leastBurstTimeProcess = null;
		for (Process p : ReadyQueue) {
			if (p.CPUBurst < minBurstTime) { // if process p has less burst time than leastBurstTimeProcess
				leastBurstTimeProcess = p; // make p the leastBurstTimeProcess
				minBurstTime = p.CPUBurst;
			}
		}
		return leastBurstTimeProcess; // return the leastBurstTimeProcess
	}

	// shortest job first Implementation
	public static ArrayList<Integer> SJF() {
		int time = 0; // current time
		Process currentlyRunning = null; // the process that is running currently, initially null
		ReadyQueue = new ArrayList<Process>(); // ready queue to add arrived processes that are ready to run
		ArrayList<Integer> ganttChart = new ArrayList<Integer>(); // Gantt Chart to show processes run-time

		while (!allProcessesFinished()) { // while there are still unfinished processes

			checkProcessesArrival(time); // check if any process has arriced at current moment

			if (currentlyRunning == null) { // if no process is running currently
				Process leastBurstTimeProcess = findLeastBurstTimeProcess(); // find the process with minimum burt time
				int leastBursTimetProcessIdx = ReadyQueue.indexOf(leastBurstTimeProcess); // find its index in the  readyQueue
																							
				if (leastBursTimetProcessIdx != -1) // and if it is in the queue
					currentlyRunning = ReadyQueue.remove(leastBursTimetProcessIdx); // remove it and run it

				if (currentlyRunning != null && currentlyRunning.startTime == -1) // if there is a process has just started to run
																					 
					currentlyRunning.startTime = time; // set its start time to current time
			}

			if (currentlyRunning != null && time == currentlyRunning.CPUBurst + currentlyRunning.startTime) { // if current time = burst time + start time for a currently running process then this means that the process has finished

				currentlyRunning.finishTime = time; // set finish time to current time
				currentlyRunning.turnaround = currentlyRunning.finishTime - currentlyRunning.arrivalTime; // compute turnaround time
				currentlyRunning.waitingTime = currentlyRunning.turnaround - currentlyRunning.CPUBurst; // compute waiting time
                                currentlyRunning.remainingTime=0;
				currentlyRunning = null;
				/*
				 * starting another process, just at the same time the previous process has
				 * finished
				 */
				Process leastBurstTimeProcess = findLeastBurstTimeProcess(); // find the process with minimum burst time
				int leastBursTimetProcessIdx = ReadyQueue.indexOf(leastBurstTimeProcess); // find its index in the
																							// readyQueue
				if (leastBursTimetProcessIdx != -1) // and if it is in the queue
					currentlyRunning = ReadyQueue.remove(leastBursTimetProcessIdx); // remove it and run it

				if (currentlyRunning != null && currentlyRunning.startTime == -1) // if there is a process has just
																					// started to run,
					currentlyRunning.startTime = time; // then set its start time to current time
			}

			addToGanttChart(ganttChart, currentlyRunning); // add currently running process at the current moment to
															// Gantt Chart
			time++; // increment time
		}

		return ganttChart;
	}

}

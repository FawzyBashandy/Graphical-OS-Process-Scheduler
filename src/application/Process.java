package application;

import java.util.Random;

import javafx.beans.property.SimpleIntegerProperty;

public class Process {



	static int ProcessNumber;
	int pid;
	int remainingTime; // For SRTF
	int arrivalTime;
	int CPUBurst;
	int startTime; // if -1 Process didnt start
	int turnaround;
	int finishTime; // if -1 Process hasnt finished yet
	int waitingTime;

	// Simple properties are used to fill the tables in the GUI (Copies of the
	// values)
	SimpleIntegerProperty pid1;
	SimpleIntegerProperty CPUBurst1;
	SimpleIntegerProperty arrivalTime1;
	SimpleIntegerProperty StartTime1;
	SimpleIntegerProperty TA1;
        SimpleIntegerProperty remainingTime1;

	public Integer getPid1() {
		return pid1.get();
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getArrivalTime1() {
		return arrivalTime1.get();
	}
        public int getRemainingTime1() {
            return remainingTime1.get();
        }
        public int getRemainingTime() {
            return remainingTime;
        }
        public SimpleIntegerProperty remainingTime1Property() {
            return remainingTime1;
        }

        public void setRemainingTime1(int remainingTime) {
            this.remainingTime1.set(remainingTime);
        }
        
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Integer getCPUBurst1() {
		return CPUBurst1.get();
	}

	public void setCPUBurst(int cPUBurst) {
		CPUBurst = cPUBurst;
	}

	public Integer getStartTime1() {
		return this.startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public Integer getTA1() {
		return this.turnaround;
	}

	public void setTA1(SimpleIntegerProperty tA1) {
		TA1 = tA1;
	}
	

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public Process(int p , int brust,int arrival) {
                pid = p;
                CPUBurst=brust;
                arrivalTime= arrival;
		pid1 = new SimpleIntegerProperty(pid);
		CPUBurst1 = new SimpleIntegerProperty(this.CPUBurst);
		arrivalTime1 = new SimpleIntegerProperty(this.arrivalTime);
                
		this.startTime = -1;
		this.finishTime = -1;
		this.remainingTime = this.CPUBurst;
                remainingTime1 = new SimpleIntegerProperty(remainingTime);
		TA1 = new SimpleIntegerProperty();
		StartTime1 = new SimpleIntegerProperty();

	}

	public void resetProcess() { // function to reset a process (re-initialize it)
		this.startTime = -1;
		 this.finishTime = -1;
		 this.remainingTime = this.CPUBurst ;
		 this.turnaround = -1 ;
		 this.waitingTime = -1 ;

	}

	public void ToString() {
		System.out.println(ProcessNumber + "\n" + CPUBurst + "\n" + arrivalTime);

	}

}

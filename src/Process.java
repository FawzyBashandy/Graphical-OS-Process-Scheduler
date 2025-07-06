
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte1.other;

public class Process {
    private String processID;
    private int burstTime;

    public Process(String processID, int burstTime) {
        this.processID = processID;
        this.burstTime = burstTime;
    }

    public String getProcessID() {
        return processID;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
}

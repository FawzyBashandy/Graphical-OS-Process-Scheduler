import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

public class SJF_Scheduler {
    private List<Process> SJF_Queue = new ArrayList<>();
    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private int simulationTime = 0;
    private SJFSchedulerGUI gui;


    public static void main (String [] args)
    {
        SJFSchedulerGUI.launch(args);
    }

    SJF_Scheduler(SJFSchedulerGUI aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void addProcess(Process p) {
        SJF_Queue.add(p);
        Collections.sort(SJF_Queue, (p1, p2) -> Integer.compare(p1.getBurstTime(), p2.getBurstTime()));
        Platform.runLater(() -> gui.processTable.getItems().add(p));
    }

    public void startScheduler() {
        if (executorService.isShutdown()) {
            executorService = Executors.newSingleThreadScheduledExecutor();
        }
        executorService.schedule(this::runScheduling, 0, TimeUnit.SECONDS);
    }

    private void runScheduling() {
        if (!SJF_Queue.isEmpty()) {
            Process currentProcess = SJF_Queue.remove(0);
            int burstTime = currentProcess.getBurstTime();
            simulationTime += burstTime; // Simulate process execution
            Platform.runLater(() -> {
                gui.updateGanttChart(currentProcess, simulationTime);
                gui.updateSimulationTime(simulationTime);
                if (!SJF_Queue.isEmpty()) {
                    startScheduler(); // Schedule next process
                }
            });
        }
    }

    public void pauseScheduler() {
        executorService.shutdownNow();
    }
}

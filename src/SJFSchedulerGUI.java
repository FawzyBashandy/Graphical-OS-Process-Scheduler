import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SJFSchedulerGUI extends Application {
    public TableView<Process> processTable = new TableView<>();
    private SJF_Scheduler scheduler;
    private Label simulationTimeLabel = new Label("Time: 0");

    @Override
    public void start(Stage primaryStage) {
        scheduler = new SJF_Scheduler(this);
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(10));
        layout.setTop(setupInputGrid());
        layout.setCenter(processTable);
        layout.setBottom(setupBottomControls());

        Scene scene = new Scene(layout, 800, 600);
        primaryStage.setTitle("SJF Scheduler");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    private HBox setupInputGrid() {
        HBox inputGrid = new HBox(10);
        TextField processIDField = new TextField();
        TextField burstTimeField = new TextField();
        Button addButton = new Button("Add Process");
        addButton.setOnAction(e -> {
            int burstTime = Integer.parseInt(burstTimeField.getText());
            Process newProcess = new Process(processIDField.getText(), burstTime);
            scheduler.addProcess(newProcess);
            processIDField.clear();
            burstTimeField.clear();
        });

        inputGrid.getChildren().addAll(new Label("Process ID:"), processIDField,
                                       new Label("Burst Time:"), burstTimeField, addButton);
        return inputGrid;
    }

    private HBox setupBottomControls() {
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> scheduler.startScheduler());
        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> scheduler.pauseScheduler());
        HBox bottomControls = new HBox(10, startButton, pauseButton, simulationTimeLabel);
        return bottomControls;
    }

    public void updateGanttChart(Process process, int endTime) {
        // Update logic for Gantt chart, visual representation of the process timeline
    }

    public void updateSimulationTime(int currentTime) {
        simulationTimeLabel.setText("Time: " + currentTime);
    }

}

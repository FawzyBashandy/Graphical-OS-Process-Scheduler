package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class StartController implements Initializable {

    @FXML
    private TableView<Process> Table;

    static String simMethod = new String();

    @FXML
    private ComboBox<String> SimulationMenu;

    @FXML
    private TextField field;

    @FXML
    private TableColumn<Process, Integer> IDField;
    @FXML
    private TableColumn<Process, Integer> BurstField;
    @FXML
    private TableColumn<Process, Integer> RemainingBurstField;
    @FXML
    private TableColumn<Process, Integer> ArrivalField;
    @FXML
    private TableColumn<Process, Integer> StartField;
    @FXML
    private TableColumn<Process, Integer> EndField;
    @FXML
    private TableColumn<Process, Integer> TAField;
    @FXML
    private TableColumn<Process, Integer> WaitField;

    @FXML
    private ScrollPane processpane;

    @FXML
    private HBox pane;

    @FXML
    private Button Back;

    @FXML
    private Button Show;

    @FXML
    private Circle c1;
    @FXML
    private Circle c2;
    @FXML
    private Circle c3;

    double TA;

    public static ObservableList<Process> Processes = FXCollections.observableArrayList();
    @FXML
    private ImageView loading;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        SimulationMenu.getItems().addAll("Live Simulation", "Results Only");
        IDField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("pid1"));
        BurstField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("CPUBurst1"));
        RemainingBurstField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("remainingTime1"));
        ArrivalField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("arrivalTime1"));
        StartField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("StartTime1"));
        EndField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("FinishTime"));
        TAField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("TA1"));
        WaitField.setCellValueFactory(new PropertyValueFactory<Process, Integer>("waitingTime"));
        Table.setItems(Processes);
    }

    @FXML
    void back(ActionEvent event) {
        Main.PrimaryStage.setScene(Main.MainScene);
        Main.PrimaryStage.centerOnScreen();
    }

    void setRotation(Circle c, int angel, int duration) {
        RotateTransition rotate = new RotateTransition(Duration.seconds(duration), c);
        rotate.setAutoReverse(true);
        rotate.setByAngle(angel);
        rotate.setCycleCount(2);
        rotate.setRate(3);
        rotate.play();
    }

    @FXML
    String SimMethod(ActionEvent event) {
        for (Process p : Scheduler.Processes) {
            p.remainingTime = p.CPUBurst;
            p.setRemainingTime1(p.CPUBurst);
        }
        simMethod = SimulationMenu.getValue().toString();
        return simMethod;
    }

    @FXML
    void show(ActionEvent event) {

        setRotation(c2, 360, 3);
        setRotation(c2, 180, 3);

        pane.getChildren().clear();
        RotateTransition rt = new RotateTransition(Duration.millis(1000), c1);
        rt.setCycleCount(4);
        rt.setByAngle(125);
        rt.setOnFinished((ActionEvent e) -> {
            for (Process p : Scheduler.Processes) {
                p.remainingTime = p.CPUBurst;
                p.setRemainingTime1(p.CPUBurst);
            }
            if ("Live Simulation".equalsIgnoreCase(simMethod)) {
                displayGanttBlocksOneByOne();
            } else if ("Results Only".equalsIgnoreCase(simMethod)) {
                displayAllGanttBlocksAtOnce();
            }
            else
            {
                Alert aalert = new Alert(Alert.AlertType.INFORMATION);
                aalert.setContentText("You didn't choose a simulation type : A live simulation will be executed !");
                aalert.show();
                displayGanttBlocksOneByOne();
            }
        });
        rt.play();
            	double sum =0 ;    	
    	for (int i =0 ; i < Scheduler.Processes.size();i ++ ) {
    		
    		
    		sum += Scheduler.Processes.get(i).turnaround ; 
    		
    		
    		
    	} 
    	TA = sum/ Scheduler.Processes.size() ; 
    	
    	field.setText(String.valueOf(TA)) ;
    }

    private void displayAllGanttBlocksAtOnce() {
        Random rand = new Random();
        for (Process p : Scheduler.Processes) {
        p.remainingTime = p.CPUBurst;
        p.setRemainingTime1(p.CPUBurst);
         }
        for (int i = 0; i < Controller.ganttChart.size(); i++) {
            StackPane pane1 = new StackPane();
            Rectangle rectangle = new Rectangle(70, 70);
            Label label = new Label();

            if (Controller.ganttChart.get(i) == -1) label.setText("NOP");
            else label.setText(i + "\n" + "P" + Controller.ganttChart.get(i).toString());
            for (Process p : Scheduler.Processes) {
                if (p.pid == Controller.ganttChart.get(i)) {
                    p.setRemainingTime1(0);
                }
            }
            rectangle.setFill(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1));
            label.setTextFill(Color.WHITE);

            pane1.getChildren().addAll(rectangle, label);
            pane.getChildren().add(pane1);
        }
    }

private void displayGanttBlocksOneByOne() {
    Random rand = new Random();
    double delayInSeconds = 0;
    for (Process p : Scheduler.Processes) {
    p.remainingTime = p.CPUBurst;
    p.setRemainingTime1(p.CPUBurst);
    }
    for (int i = 0; i < Controller.ganttChart.size(); i++) {
        PauseTransition pause = new PauseTransition(Duration.seconds(delayInSeconds));
        final int index = i;
        pause.setOnFinished(e -> {
            StackPane pane1 = new StackPane();
            Rectangle rectangle = new Rectangle(70, 70);
            Label label = new Label();

            if (Controller.ganttChart.get(index) == -1) label.setText("NOP");
            else label.setText(index + "\n" + "P" + Controller.ganttChart.get(index));
            for (Process p : Scheduler.Processes) {
                
                if (p.pid == Controller.ganttChart.get(index)) {
                    if(p.remainingTime==p.CPUBurst)
                        p.setRemainingTime1(p.remainingTime--);
                    p.setRemainingTime1(p.remainingTime--);
                }

            }
            rectangle.setFill(new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble(), 1));
            label.setTextFill(Color.WHITE);

            pane1.getChildren().addAll(rectangle, label);
            pane.getChildren().add(pane1);
            
        });
        pause.play();
        delayInSeconds += 1;
    }
}

}

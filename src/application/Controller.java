package application;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Controller implements Initializable {

	public static int ProcessNumber;
	String method = new String();

	@FXML
	private ComboBox<String> AlgMenu;
        
        
	@FXML
	private TextField NumberField;
        
	@FXML
	private TextField processNumberField;
        
	@FXML
	private TextField arrivalTimeField;
        
	@FXML
	private TextField burstTimeField;
	
	private Button GenerateButton;

	@FXML
	private Button ResetButton;

	@FXML
	private Button StartButton;

	@FXML
	private Button ShowButton;
	
	@FXML
	private Button quit;

	 @FXML
	    private TextArea area;
         
         int numberofadded =0;


	public static  ArrayList<Integer> ganttChart = new ArrayList<Integer>() ;
    @FXML
    private Button AddButton;
    @FXML
    private Button AverageButton;

        @FXML
	void ComputeAverage(ActionEvent event) {
            double ta4 ; 
            double ta5 ; 
            ganttChart = Scheduler.SJF(); 
            ta4 = avgwait(Scheduler.Processes) ;
            for (int i = 0; i < Scheduler.Processes.size(); i++) {

                    Scheduler.Processes.get(i).resetProcess();

            }   

            ganttChart = Scheduler.SRTF();
            ta5 = avgwait(Scheduler.Processes) ;
            area.clear();
            area.replaceSelection(
                        "\nAVG WAIT IN SJF - >  " + ta4 
                        +"\nAVG WAIT IN SRTF - >  " + ta5 
                        + "\n----------------------------------------\n");
	}
	@FXML
	void AddProcess(ActionEvent event) {
		
                numberofadded++;
                ProcessNumber = Integer.parseInt(NumberField.getText());
                if(numberofadded > ProcessNumber)
                {
			Alert aalert = new Alert(AlertType.ERROR);
			aalert.setContentText(" You can't add more processes, Please reset and enter bigger number of processes! ");
			aalert.show();
                }
                else{
                    Process process = new Process(Integer.parseInt(processNumberField.getText()),Integer.parseInt(burstTimeField.getText()),Integer.parseInt(arrivalTimeField.getText()));
                    Scheduler.Processes.add(process);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setContentText("Process " + Integer.parseInt(processNumberField.getText()) + " has been added.");
                    alert.show();
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), GenerateButton);
                    ft.setFromValue(1.0);
                    ft.setToValue(0.1);
                    ft.setCycleCount(2);
                    ft.setAutoReverse(true);


                    ft.setOnFinished(e->{ 	
                            Alert aalert = new Alert(AlertType.INFORMATION);
                            aalert.setContentText(" Process has been Addedd ");
                            aalert.show();

      });

                }
	
	}

	@FXML
	void GetNumber(ActionEvent event) {

		ProcessNumber = Integer.parseInt(NumberField.getText());

	}

	
	@FXML
	void quit(ActionEvent event) {

		System.exit(0);

	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		AlgMenu.getItems().addAll("SJF", "SRTF");
		area.setEditable(false);
		

	}

	// TODO Auto-generated method stub
	@FXML
	String method(ActionEvent event) {
		method = AlgMenu.getValue().toString();

		return method;

	}
        
	// TODO Auto-generated method stub
	String SimMethod(ActionEvent event) {
		method = AlgMenu.getValue().toString();

		return method;

	}

	@FXML
	void showTable(ActionEvent event) {

		TableController.Processes.clear();

		for (int i = 0; i < Scheduler.Processes.size(); i++) {
			Process process = Scheduler.Processes.get(i);

			TableController.Processes.add(process);
		}

		Main.PrimaryStage.setScene(Main.showScene);
		Main.PrimaryStage.centerOnScreen();

	}

	@FXML
	 void StartButtonPressed(ActionEvent event) {
		ganttChart.clear();
		for (int i = 0; i < Scheduler.Processes.size(); i++) {

			Scheduler.Processes.get(i).resetProcess();

		}

		switch (method) {
		case "SJF": {
			ganttChart = Scheduler.SJF();
			break;
		}
		case "SRTF": {

			ganttChart = Scheduler.SRTF();
			break;
		}
		}
                
		StartController.Processes.clear();
		

		for (int i = 0; i < Scheduler.Processes.size(); i++) {

			Process process = Scheduler.Processes.get(i);

			StartController.Processes.add(process);
		}

		Main.PrimaryStage.setScene(Main.FinalScene);
		Main.PrimaryStage.centerOnScreen();
        //Stage stage = new Stage() ; 
        //stage.setScene(Main.FinalScene); 
        //stage.show();
	}

	@FXML
	void ResetButtonPressed(ActionEvent event) {
		Scheduler.Processes.clear();
		FadeTransition ft = new FadeTransition(Duration.millis(1000), ResetButton);
		ft.setFromValue(1.0);
		ft.setToValue(0.1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		
		ft.play();
		
		numberofadded = 0;
		

		Scheduler.Processes.clear();

		TableController.Processes.clear();
		NumberField.clear();

	}
	
	double avgwait (ArrayList<Process> Processes ) {
		
		double sum = 0 ; 
		
		for (int i = 0 ; i<Processes.size(); i ++)  sum+= Processes.get(i).waitingTime; 
			
			sum /= Processes.size() ; 
			
			return sum ;
		
		
		
	} 
	

}
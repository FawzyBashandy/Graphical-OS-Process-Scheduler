package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;

public class TableController implements Initializable {
    @FXML
    public TableView<Process> tableView;

    @FXML
    private  TableColumn<Process,Integer> Process_Coulmn ;

    @FXML
    private  TableColumn<Process,Integer> CPU_Coulmn ;

    @FXML
    private TableColumn<Process,Integer> Arrival_Coulmn; 

   
 
    
   static  ObservableList<Process> Processes = FXCollections.observableArrayList() ;

 
    

    @FXML
    void Back(ActionEvent event) {
    	Main.PrimaryStage.setScene(Main.MainScene);
      	Main.PrimaryStage.centerOnScreen();

    }

  
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		Process_Coulmn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("pid1"));
		CPU_Coulmn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("CPUBurst1"));
		Arrival_Coulmn.setCellValueFactory(new PropertyValueFactory<Process,Integer>("arrivalTime1"));
		
		tableView.setItems( Processes );
		
		
		
		
	}







		

		
	}
	
	
	
	
	
	
	



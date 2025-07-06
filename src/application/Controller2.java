package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Controller2 implements Initializable {
	
	


    @FXML
    private Button EnterButton;

    @FXML
    private Button ExitButton;
    
    @FXML
    private ImageView image;
    
    @FXML
    void EnterButtonPressed(ActionEvent event) {
    	  
  		
  		
    	
    	Main.PrimaryStage.setScene(Main.MainScene); 
    	Main.PrimaryStage.centerOnScreen();

    }

    @FXML
    void ExitButtonPressed(ActionEvent event) {
    	System.exit(0);

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		FadeTransition ft = new FadeTransition(Duration.millis(1000), image);
		ft.setFromValue(1.0);
		ft.setToValue(0.3);
		ft.setCycleCount(Animation.INDEFINITE);
		ft.setAutoReverse(true);
		//ft.setDuration(Duration.INDEFINITE);
		ft.play();
		
	}

}

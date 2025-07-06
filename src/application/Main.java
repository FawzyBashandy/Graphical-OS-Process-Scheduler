 package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	public static Scene MainScene ; 
	public static Scene showScene ; 
	public static Scene FinalScene ; 
	public Scene OpeningScene ; 
	public static Stage PrimaryStage ; 
	//public static Stage SecondaryStage= new Stage() ; 
	
	@Override	
	public void start(Stage primaryStage) {
		try {
			PrimaryStage = primaryStage ; 
			
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("MainScene.fxml"));
		    MainScene = new Scene(root,1280.0,800);
			MainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Pane Opening = (Pane)FXMLLoader.load(getClass().getResource("OpeningScene.fxml"));
		    OpeningScene = new Scene(Opening,1280.0,800.0);
		    OpeningScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
		    
			Pane show = (Pane)FXMLLoader.load(getClass().getResource("ShowScene.fxml"));
		    showScene = new Scene(show,1280.0,800.0);
		    showScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    
			AnchorPane table = (AnchorPane)FXMLLoader.load(getClass().getResource("FinalScene.fxml"));
		    FinalScene = new Scene(table,1280.0,800.0);
		    FinalScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
	

			//------------------------------------------------------------------------------------
			primaryStage.setTitle("CPU Scheduler - SJF and SRTF");
			primaryStage.setScene(OpeningScene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			//SecondaryStage.setScene(FinalScene);
			
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}

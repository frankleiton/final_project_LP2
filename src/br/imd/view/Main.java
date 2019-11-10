package br.imd.view;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import org.opencv.core.Core;


public class Main extends Application {
	
	private Stage primeiro;
	private AnchorPane tlPrincipal;
	
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primeiro = primaryStage;
			primeiro.setTitle("Separador de fotos");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("TelaPrincipal.fxml"));
			tlPrincipal = (AnchorPane) loader.load();
			
			Scene scene = new Scene(tlPrincipal, 700, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}

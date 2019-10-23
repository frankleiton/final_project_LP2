package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.util.List;


import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

import org.opencv.core.Core;


public class Main extends Application {
	
	private Stage primeiro;
	private AnchorPane tlPrincipal;
	
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	
	
	private void resizeImgame(String image_url) 
	{
		HOGDescriptor hog = new HOGDescriptor();
		Mat img = new Mat();
		MatOfFloat features = new MatOfFloat();
		
		img = Imgcodecs.imread(image_url, Imgcodecs.IMREAD_GRAYSCALE);
		Imgproc.resize(img, img, new Size(64,128), 0.5, 0.5, Imgproc.INTER_LINEAR);
		hog.compute(img,features);
		List<Float> arrayOfFeatures = features.toList();
		
		for (Float float1 : arrayOfFeatures) {
			System.out.println(float1);
		}
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			primeiro = primaryStage;
			primeiro.setTitle("Projeto final");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../br/imd/view/TelaPrincipal.fxml"));
			tlPrincipal = (AnchorPane) loader.load();
			
			Scene scene = new Scene(tlPrincipal);
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

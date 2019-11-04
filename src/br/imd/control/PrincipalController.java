package br.imd.control;

import java.io.File;
import java.util.List;

import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class PrincipalController {
	
	@FXML
	private Button button_selectImage; 
	
	@FXML
	private Label label_urlImage;
	

	public void ButtonSelectImage(ActionEvent event) {
		FileChooser fc = new FileChooser();
//		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg ", "*.png"));
		File selectedFile = fc.showOpenDialog(null);
		
		if (selectedFile != null) {
			label_urlImage.setText("imagem Selecionada: "+selectedFile.getAbsolutePath());
			resizeImgame(selectedFile.getAbsolutePath());
		}else {
			label_urlImage.setText("Imagem invalida");
		}
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
		
		System.out.println("Tamanho: "+ arrayOfFeatures.size());
		
		for (float float1 : arrayOfFeatures) {
			System.out.println(float1);
		}
	}
}

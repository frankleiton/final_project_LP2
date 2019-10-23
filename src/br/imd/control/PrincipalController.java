package br.imd.control;

import java.io.File;

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
		fc.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.jpg ", "*.png"));
		File selectedFile = fc.showOpenDialog(null);
		
		if (selectedFile != null) {
			label_urlImage.setText("imagem Selecionada: "+selectedFile.getAbsolutePath());
		}else {
			label_urlImage.setText("Imagem invalida");
		}
	}
}

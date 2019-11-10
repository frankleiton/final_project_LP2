package br.imd.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import br.imd.model.Imagem;
import br.imd.model.Knn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class ViewController implements Initializable {

	@FXML
	private ContextMenu coisas;

	@FXML
	private Button buttonSelectImage;
	
	@FXML
	private Button button_scan;

	@FXML
	private ComboBox<String> comboBox_selectDistancia;
	
	@FXML
	private Label label_qtdPictures;

	String distSelected;
	
	List<File> imagesSelected;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		carregarDistancias();
	}

	public void comboSelect(ActionEvent event) {
		distSelected = comboBox_selectDistancia.getValue();
		System.out.println(distSelected);
	}

	private void carregarDistancias() {
		ObservableList<String> options = FXCollections.observableArrayList("Euclidiana", "Manhattan", "Chebychev");

		comboBox_selectDistancia.setItems(options);
	}
	
	public void openFolderNotPerson() throws IOException {
		Runtime.getRuntime().exec("explorer.exe C:\\Pessoa\\");
	}
	
	public void openFolderPerson() throws IOException {
		Runtime.getRuntime().exec("explorer.exe C:\\NotPessoa\\");
	}
	
	public void ButtonSelectImage(ActionEvent event) {
		imagesSelected = new ArrayList<File>();

		FileChooser fc = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
		fc.getExtensionFilters().add(extFilter);
		List<File> selectedFile = fc.showOpenMultipleDialog(null);
		
		

		if (selectedFile != null) {
			
			for (File file : selectedFile) {
				imagesSelected.add(file);
			}
			
			label_qtdPictures.setText(imagesSelected.size() + " Imagens Selecionadas");
			
		}

	}
	
	public void scanImage(ActionEvent event) throws IOException {
		
		if (imagesSelected.size() > 0  && distSelected != null) {
			for (File imgSelected : imagesSelected) {
				System.out.println(imgSelected);
				calcularKnn(imgSelected, distSelected);		
			}
			
			openFolderPerson();
			openFolderNotPerson();
		}
	}
	

	public void calcularKnn(File img, String typeDist) throws IOException {
		Hog h = new Hog();
		Knn knn = new Knn();

		String is_person = null;

		if (typeDist.equals("Euclidiana")) {
			is_person = knn.knn(getDataset(), h.resizeImgame(img.getAbsolutePath()), 3, new DistanciaEucidiana());
		} else if (typeDist.equals("Manhattan")) {
			is_person = knn.knn(getDataset(), h.resizeImgame(img.getAbsolutePath()), 3, new DistanciaManhattan());
		}
		
		

		if (is_person.equals("person")) 
		{
			System.out.println("Pessoa");

			moveFiles(img, "C:\\Pessoa\\");
			
		}else{
			
			System.out.println("não Pessoa");
			
			moveFiles(img, "C:\\NotPessoa\\");
		}
	}
	
	private boolean moveFiles(File img, String path) throws IOException {
		FileChannel sourceChannel = null;
	    FileChannel destinationChannel = null;
	    
	    try {
	        sourceChannel = new FileInputStream(img).getChannel();
	        destinationChannel = new FileOutputStream(path+img.getName()).getChannel();
	        sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
	        
	        return true;
	    }catch(Exception e) {
	    	return false;
	    }finally {
	    	 if (sourceChannel != null && sourceChannel.isOpen())
	             sourceChannel.close();
	         if (destinationChannel != null && destinationChannel.isOpen())
	             destinationChannel.close();
	    }
	    
	   
	    
	}

	private ArrayList<Imagem> getDataset() {

		ArrayList<Imagem> images = new ArrayList<Imagem>();

		try {
			List<String> lines = Files.readAllLines(Paths.get("src/resources/dataset_2019_1.csv"));

			String classType = null;
			for (int i = 1; i < lines.size(); i++) {
				int cont = 0;
				double[] f = new double[1000];

				String[] result = lines.get(i).split(",");

				for (String s : result) {
					if (cont == 1000) {
						classType = s.replaceAll("\"", "");
					} else {
						f[cont] = Double.parseDouble(s);
					}
					cont++;
				}

				images.add(new Imagem(f, classType));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return images;
	}
}

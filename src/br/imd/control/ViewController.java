package br.imd.control;

import java.io.File;
import java.net.URL;
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
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class ViewController implements Initializable {

	@FXML
	private ContextMenu coisas;

	@FXML
	private Button buttonSelectImage;
	
	@FXML
	private Button button_scan;
	

	@FXML
	private Label labelUrlImage;

	@FXML
	private ComboBox<String> comboBox_selectDistancia;

	@FXML
	private ImageView imageView_imgSelected;

	@FXML
	private ProgressBar pb_loadBar;

	String distSelected;
	
	String imgSelected;

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

	public void ButtonSelectImage(ActionEvent event) {

		FileChooser fc = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
		fc.getExtensionFilters().add(extFilter);
		File selectedFile = fc.showOpenDialog(null);

		if (selectedFile != null) {

			labelUrlImage.setText("Imagem Selecionada");
			imgSelected = selectedFile.getAbsolutePath();
			setImageView(imgSelected);

		} else {
			labelUrlImage.setText("Imagem inválida");
		}

	}
	
	public void scanImage(ActionEvent event) {
		if (imgSelected != null && distSelected != null) {
			calcularKnn(imgSelected, distSelected);			
		}
	}
	
	public void setImageView(String filePath) {
		File file = new File(filePath);
		Image image = new Image(file.toURI().toString());
		imageView_imgSelected.setImage(image);
	}

	public void calcularKnn(String imageURL, String typeDist) {
		Hog h = new Hog();
		Knn knn = new Knn();
		String is_person = null;

		if (typeDist.equals("Euclidiana")) {
			is_person = knn.knn(getDataset(), h.resizeImgame(imageURL), 3, new DistanciaEucidiana());
		} else if (typeDist.equals("Manhattan")) {
			is_person = knn.knn(getDataset(), h.resizeImgame(imageURL), 3, new DistanciaManhattan());
		}

		pb_loadBar.setProgress(0.1);

		if (is_person.equals("person")) 
		{
			pb_loadBar.setProgress(1.0);
			pb_loadBar.getStyleClass().add("green-bar");
		}else {
			pb_loadBar.setProgress(1.0);
			pb_loadBar.getStyleClass().add("red-bar");
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

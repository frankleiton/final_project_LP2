package br.imd.control;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import br.imd.model.Distancia;
import br.imd.model.Image;
import br.imd.model.Knn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class ViewController {

	@FXML
	private ContextMenu coisas;

	@FXML
	private ChoiceBox<Distancia> choiceBoxSelectdistance;

	@FXML
	private Button buttonSelectImage;

	@FXML
	private Label labelUrlImage;

	public void ButtonSelectImage(ActionEvent event) {
		Hog h = new Hog();
		Knn knn = new Knn();

		FileChooser fc = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.png");
		fc.getExtensionFilters().add(extFilter);
		File selectedFile = fc.showOpenDialog(null);

		if (selectedFile != null) {
			labelUrlImage.setText("imagem Selecionada: " + selectedFile.getAbsolutePath());

			System.out.println(knn.knn(getDataset(), h.resizeImgame(selectedFile.getAbsolutePath()), 3));

		} else {
			labelUrlImage.setText("Imagem invalida");
		}

	}

	@FXML
	void selectDistance() {
		Distancia manhattan = new DistanciaManhattan();
		Distancia euclidiana = new DistanciaEucidiana();
		choiceBoxSelectdistance.getItems().addAll(manhattan, euclidiana);

	}

	private ArrayList<Image> getDataset() {

		ArrayList<Image> images = new ArrayList<Image>();

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

				images.add(new Image(f, classType));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return images;
	}
}

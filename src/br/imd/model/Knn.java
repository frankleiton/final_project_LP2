package br.imd.model;

import java.util.ArrayList;
import java.util.Collections;

import br.imd.control.ClassPredominante;
import br.imd.control.CompararDistancia;
import br.imd.control.DistanciaEucidiana;
import br.imd.control.DistanciaManhattan;

public class Knn {

	public String knn(ArrayList<Image> images, double[] image, int k) {
		Distancia distancia = new DistanciaManhattan();

		ArrayList<ImageResult> result = distancia.distancia(image, images);

		Collections.sort(result, new CompararDistancia());
		String[] predominantes = new String[k];

		for (int i = 0; i < predominantes.length; i++) {
			predominantes[i] = result.get(i).className;
		}

		return new ClassPredominante().classPredominante(predominantes);

	}

}

package br.imd.model;

import java.util.ArrayList;
import java.util.Collections;

import br.imd.control.ClassPredominante;
import br.imd.control.CompararDistancia;

public class Knn {

	public String knn(ArrayList<Imagem> images, double[] image, int k, Distancia dist) {
		
		Distancia distancia = dist;

		ArrayList<ImageResult> result = distancia.distancia(image, images);

		Collections.sort(result, new CompararDistancia());
		String[] predominantes = new String[k];

		for (int i = 0; i < predominantes.length; i++) {
			predominantes[i] = result.get(i).className;
		}

		return new ClassPredominante().classPredominante(predominantes);

	}

}

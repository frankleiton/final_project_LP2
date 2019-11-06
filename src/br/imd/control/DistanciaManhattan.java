package br.imd.control;

import java.util.ArrayList;

import br.imd.model.Distancia;
import br.imd.model.Imagem;
import br.imd.model.ImageResult;

public class DistanciaManhattan extends Distancia {

	@Override
	public ArrayList<ImageResult> distancia(double[] image, ArrayList<Imagem> images) {

		ArrayList<ImageResult> result = new ArrayList<ImageResult>();

		for (Imagem img : images) {
			double distancia = 0;
			double aux = 0;
			for (int i = 0; i < img.getDados().length; i++) {
				aux = img.getDados()[i] - image[i];
				if (aux >= 0) {
					distancia += aux;
				} else {
					distancia += aux * (-1);
				}
			}
			result.add(new ImageResult(distancia, img.getClassType()));
		}

		return result;

	}

}
package br.imd.control;

import java.util.ArrayList;

import br.imd.model.Distancia;
import br.imd.model.ImageResult;
import br.imd.model.Imagem;

public class DistanciaChebychev extends Distancia {

	@Override
	public ArrayList<ImageResult> distancia(double[] image, ArrayList<Imagem> images) {

		ArrayList<ImageResult> result = new ArrayList<ImageResult>();
		
		for(Imagem img : images) {
			double distancia = 0, aux = 0;
			for (int i = 0; i < img.getDados().length; i++) {
				distancia = img.getDados()[i] - image[i];
				
				if (aux > distancia) {
					distancia = aux;
				}
			}
			
			result.add(new ImageResult(distancia, img.getClassType()));
		}
		
		return result;
	}

}

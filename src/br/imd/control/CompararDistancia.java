package br.imd.control;
import java.util.Comparator;

import br.imd.model.ImageResult;

public class CompararDistancia implements Comparator<ImageResult> {

	@Override
	public int compare(ImageResult o1, ImageResult o2) {
		
		return o1.distancia < o2.distancia ? -1 : o1.distancia == o2.distancia ? 0 : 1;
	}
	
}

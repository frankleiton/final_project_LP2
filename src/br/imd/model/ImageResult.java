package br.imd.model;

public class ImageResult {
	
	public double distancia;
	public String className;
	
	public ImageResult(double distancia, String classname) {
		this.className = classname;
		this.distancia = distancia;
	}

	@Override
	public String toString() {
		return "ImageResult [distancia=" + distancia + ", className=" + className + "]";
	}
	

}

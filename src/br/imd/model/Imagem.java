package br.imd.model;

import java.util.Arrays;

public class Imagem {

	private double[] dados;
	private String classType;

	public Imagem(double[] dados, String classType) {
		this.dados = dados;
		this.classType = classType;
	}

	@Override
	public String toString() {
		return "Image [dados=" + Arrays.toString(dados) + ", classType=" + classType + "]";
	}

	public double[] getDados() {
		return dados;
	}

	public void setDados(double[] dados) {
		this.dados = dados;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

}

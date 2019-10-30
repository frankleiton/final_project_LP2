package br.imd.control;

public class ClassPredominante {

	private String chave1 = "person";
	private String chave2 = "notPerson";

	public String classPredominante(String[] predominantes) {

		int cont_c1 = 0;
		int cont_c2 = 0;

		for (String string : predominantes) {
			if (string.equals(chave1)) {
				cont_c1++;
			} else if (string.equals(chave2)) {
				cont_c2++;
			}
		}

		return cont_c1 > cont_c2 ? chave1 : chave2;

	}

}

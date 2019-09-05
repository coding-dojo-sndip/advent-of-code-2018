package fr.insee.aoc.utils;

import java.util.function.BiFunction;

public enum Affectation {

	// Affectation directe de la valeur
	LITTERAL((valeur, registre) -> valeur),
	// Affectation de la case n°[valeur] du registre
	REGISTER((valeur, registre) -> registre[valeur]);

	private BiFunction<Integer, Integer[], Integer> fonction;

	private Affectation(BiFunction<Integer, Integer[], Integer> fonction) {
		this.fonction = fonction;
	}

	public BiFunction<Integer, Integer[], Integer> getFonction() {
		return fonction;
	}

}

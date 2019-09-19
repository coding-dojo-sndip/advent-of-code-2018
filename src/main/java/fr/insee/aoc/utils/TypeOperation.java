package fr.insee.aoc.utils;

import java.util.Objects;
import java.util.function.BiFunction;

public enum TypeOperation {

	// Addition
	ADD((t, u) -> t + u),
	// Multiplication
	MUL((t, u) -> t * u),
	// Bitwise And
	BAN((t, u) -> t & u),
	// Bitwise Or
	BOR((t, u) -> t | u),
	// Assignment
	SET((t, u) -> t),
	// Greater-than testing
	GT((t, u) -> t > u ? 1 : 0),
	// Equality testing (utilisation de .equals car == ne fonctionne que sur des
	// Integers compris entre -128 et 127)
	EQ((t, u) -> Objects.equals(t, u) ? 1 : 0);

	private BiFunction<Integer, Integer, Integer> fonction;

	private TypeOperation(BiFunction<Integer, Integer, Integer> fonction) {
		this.fonction = fonction;
	}

	public BiFunction<Integer, Integer, Integer> getFonction() {
		return fonction;
	}

}

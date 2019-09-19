package fr.insee.aoc.utils;

import java.util.Arrays;

/**
 * Décomposition des opcodes comme :
 * 
 * un "TypeOperation" qui s'applique sur deux entrées qui sont soit des valeurs
 * littérales, soit des référence au registre.
 *
 */
public enum OpCode {

	// Addition d'une reférence au registre et d'une autre référence au registre
	ADDR(TypeOperation.ADD, Affectation.REGISTER, Affectation.REGISTER),
	// Addition d'une reférence au registre et d'une valeur littérale
	ADDI(TypeOperation.ADD, Affectation.REGISTER, Affectation.LITTERAL),
	// Multiplication d'une reférence au registre et d'une autre référence au
	// registre
	MULR(TypeOperation.MUL, Affectation.REGISTER, Affectation.REGISTER),
	// Multiplication d'une reférence au registre et d'une valeur littérale
	MULI(TypeOperation.MUL, Affectation.REGISTER, Affectation.LITTERAL),
	// Bitwise And d'une reférence au registre et d'une autre référence au registre
	BANR(TypeOperation.BAN, Affectation.REGISTER, Affectation.REGISTER),
	// Bitwise And d'une reférence au registre et d'une valeur littérale
	BANI(TypeOperation.BAN, Affectation.REGISTER, Affectation.LITTERAL),
	// Bitwise Or d'une reférence au registre et d'une autre référence au registre
	BORR(TypeOperation.BOR, Affectation.REGISTER, Affectation.REGISTER),
	// Bitwise Or d'une reférence au registre et d'une valeur littérale
	BORI(TypeOperation.BOR, Affectation.REGISTER, Affectation.LITTERAL),
	// Affectation d'une reférence au registre
	SETR(TypeOperation.SET, Affectation.REGISTER, Affectation.REGISTER),
	// Affectation d'une valeur littérale
	SETI(TypeOperation.SET, Affectation.LITTERAL, Affectation.LITTERAL),
	// Comparaison ">=" entre une valeur littérale et une référence au registre
	GTIR(TypeOperation.GT, Affectation.LITTERAL, Affectation.REGISTER),
	// Comparaison ">=" entre une référence au registre et une valeur littérale
	GTRI(TypeOperation.GT, Affectation.REGISTER, Affectation.LITTERAL),
	// Comparaison ">=" entre une une reférence au registre et une autre référence
	// au registre
	GTRR(TypeOperation.GT, Affectation.REGISTER, Affectation.REGISTER),
	// Comparaison d'égalité entre une valeur littérale et une référence au registre
	EQIR(TypeOperation.EQ, Affectation.LITTERAL, Affectation.REGISTER),
	// Comparaison d'égalité entre une référence au registre et une valeur littérale
	EQRI(TypeOperation.EQ, Affectation.REGISTER, Affectation.LITTERAL),
	// Comparaison d'égalité entre une une reférence au registre et une autre
	// référence au registre
	EQRR(TypeOperation.EQ, Affectation.REGISTER, Affectation.REGISTER);
	TypeOperation typeOperation;
	Affectation affectation1;
	Affectation affectation2;

	private OpCode(TypeOperation typeOperation, Affectation affectation1, Affectation affectation2) {
		this.typeOperation = typeOperation;
		this.affectation1 = affectation1;
		this.affectation2 = affectation2;
	}

	public Integer[] apply(Integer[] registreBefore, Integer value1, Integer value2, Integer target) {
		// Copie du registre en entrée pour ne pas perturber l'objet initial
		Integer[] registreAfter = Arrays.copyOf(registreBefore, 4);

		// Determination des opérandes : valeurs literales ou référence au registre
		int operande1 = affectation1.getFonction().apply(value1, registreBefore);
		int operande2 = affectation2.getFonction().apply(value2, registreBefore);

		// Application de l'opération selon les opérandes précedemment déterminées
		Integer resultat = typeOperation.getFonction().apply(operande1, operande2);

		// On place le résultat dans la case de registre demandée
		registreAfter[target] = resultat;

		return registreAfter;
	}

}

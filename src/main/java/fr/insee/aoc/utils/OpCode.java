package fr.insee.aoc.utils;

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
	ADDI(TypeOperation.ADD, Affectation.REGISTER, Affectation.LITTERAL);

	TypeOperation typeOperation;
	Affectation affectation1;
	Affectation affectation2;

	private OpCode(TypeOperation typeOperation, Affectation affectation1, Affectation affectation2) {
		this.typeOperation = typeOperation;
		this.affectation1 = affectation1;
		this.affectation2 = affectation2;
	}

	public Integer[] apply(Integer[] registreBefore, Integer value1, Integer value2, Integer target) {

		// Determination des opérandes : valeurs literales ou référence au registre
		int operande1 = affectation1.getFonction().apply(value1, registreBefore);
		int operande2 = affectation2.getFonction().apply(value2, registreBefore);

		// Application de l'opération selon les opérandes précedemment déterminées
		Integer resultat = typeOperation.getFonction().apply(operande1, operande2);

		// On place le résultat dans la case de registre demandée
		registreBefore[target] = resultat;

		return registreBefore;

	}

}

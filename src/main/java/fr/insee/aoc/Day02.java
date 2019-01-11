package fr.insee.aoc;

import static fr.insee.aoc.Days.arrayOfLines;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;

public class Day02 implements Day {

	@Override
	public String part1(String input) {
		String[] words = arrayOfLines(input);
		long checksum = numberOfExactlyNTimes(words, 2) * numberOfExactlyNTimes(words, 3);
		return String.valueOf(checksum);
	}

	@Override
	public String part2(String input) {
		String[] words = arrayOfLines(input);
		for (int i = 0; i < words.length - 1; i ++) {
			for (int j = i + 1; j < words.length; j ++) {
				String a = words[i];
				String b = words[j];
				String commonsChars = commonsChars(a, b);
				if(commonsChars.length() == a.length() - 1) return commonsChars;
			}	
		}
		throw new DayException("Erreur d'algorithme");
	}

	private boolean exactlyNTimes(String word, int n) {
		return word.chars()
			.boxed()
			.collect(groupingBy(identity(), counting()))
			.values()
			.stream()
			.anyMatch(v -> v == n);
	}
	
	private long numberOfExactlyNTimes(String[] words, int n) {
		return Arrays.stream(words)
			.filter(word -> exactlyNTimes(word, n))
			.count();
	}
	
	private String commonsChars(String a, String b) {
		StringBuilder builder = new StringBuilder();
		int length = a.length();
		for (int indice = 0; indice < length; indice ++) {
			if (a.charAt(indice) == b.charAt(indice)) {
				builder.append(a.charAt(indice));
			}
		}
		return builder.toString();
	}
}

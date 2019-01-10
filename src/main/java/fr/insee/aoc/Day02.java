package fr.insee.aoc;

import static fr.insee.aoc.DayUtils.readLines;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.List;

public class Day02 implements Day {

	@Override
	public String part1(String input) {
		List<String> words = readLines(input);
		long checksum = numberOfExactlyNTimes(words, 2) * numberOfExactlyNTimes(words, 3);
		return String.valueOf(checksum);
	}

	@Override
	public String part2(String input) {
		List<String> words = readLines(input);
		for (int i = 0; i < words.size() - 1; i ++) {
			for (int j = i + 1; j < words.size(); j ++) {
				String a = words.get(i);
				String b = words.get(j);
				if(differOnlyByOneChar(a, b)) return commonsChars(a, b);
			}	
		}
		throw new DayAlgoException();
	}

	private boolean exactlyNTimes(String word, int n) {
		return word.chars()
			.boxed()
			.collect(groupingBy(identity(), counting()))
			.values()
			.stream()
			.anyMatch(v -> v == n);
	}
	
	private long numberOfExactlyNTimes(List<String> words, int n) {
		return words.stream()
			.filter(word -> exactlyNTimes(word, n))
			.count();
	}
	
	private boolean differOnlyByOneChar(String a, String b) {
		int numberOfDifferences = 0;
		int length = a.length();
		for (int indice = 0; indice < length; indice ++) {
			if (a.charAt(indice) != b.charAt(indice)) {
				numberOfDifferences ++;
				if(numberOfDifferences > 1) {
					return false;
				}
			}
		}
		return numberOfDifferences == 1;
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

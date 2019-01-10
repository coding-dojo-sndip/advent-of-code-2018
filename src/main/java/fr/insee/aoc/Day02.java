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
}

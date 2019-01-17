package fr.insee.aoc;

import static fr.insee.aoc.Days.listOfLines;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day02 implements Day {

	@Override
	public String part1(String input) {
		List<String> strings = listOfLines(input);
		long a = numberOfStringsNTimes(strings, 2);
		long b = numberOfStringsNTimes(strings, 3);
		return String.valueOf(a * b);
	}

	private long numberOfStringsNTimes(List<String> strings, int n) {
		return strings.stream()
			.map(this::occurences)
			.filter(o -> containsSameCharacter(o, n))
			.count();
	}

	private Map<Integer, Long> occurences(String string) {
		return string.chars()
			.boxed()
			.collect(Collectors.groupingBy(identity(), counting()));
	}

	private boolean containsSameCharacter(Map<Integer, Long> occurences, int numberOfOccurences) {
		return occurences.values().contains(Long.valueOf(numberOfOccurences));
	}
}

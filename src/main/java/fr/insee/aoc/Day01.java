package fr.insee.aoc;

import static fr.insee.aoc.DayUtils.notIn;
import static fr.insee.aoc.DayUtils.readLines;
import static fr.insee.aoc.DayUtils.setOf;

import java.util.Set;

public class Day01 implements Day {

	@Override
	public String part1(String input) {
		int frequency = readLines(input).stream()
			.mapToInt(Integer::parseInt)
			.sum();
		return String.valueOf(frequency);
	}

	@Override
	public String part2(String input) {
		Integer[] changes = readLines(input).stream()
			.map(Integer::parseInt)
			.toArray(Integer[]::new);
		Set<Integer> frequencies = setOf();
		int n = 0;
		int frequency = 0;
		while(notIn(frequency, frequencies)) {
			frequencies.add(frequency);
			frequency += changes[n % changes.length];
			n ++;
		}
		return String.valueOf(frequency);
	}
}

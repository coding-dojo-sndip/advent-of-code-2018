package fr.insee.aoc;

import static fr.insee.aoc.utils.DayUtils.*;

import java.util.List;
import java.util.Set;

public class Day01 implements Day {

	public String part1(String input) {
		int sum = streamIntegers(input).sum();
		return String.valueOf(sum);
	}

	public String part2(String input) {
		Set<Integer> frequencies = setOf();
		List<Integer> lines = readIntegers(input);
		int frequency = 0;
		while (notIn(frequency, frequencies)) {
			frequencies.add(frequency);
			frequency += lines.get((frequencies.size() - 1) % lines.size());
		}
		return String.valueOf(frequency);
	}
}

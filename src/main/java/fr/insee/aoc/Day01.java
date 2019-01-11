package fr.insee.aoc;

import static fr.insee.aoc.utils.DayUtils.arrayOfInt;
import static fr.insee.aoc.utils.DayUtils.notIn;
import static fr.insee.aoc.utils.DayUtils.setOf;
import static fr.insee.aoc.utils.DayUtils.streamOfInt;

import java.util.Set;

public class Day01 implements Day {

	public String part1(String input) {
		int frequency = streamOfInt(input).sum();
		return String.valueOf(frequency);
	}

	public String part2(String input) {
		Set<Integer> frequencies = setOf();
		int[] changes = arrayOfInt(input);
		int frequency = 0;
		while (notIn(frequency, frequencies)) {
			frequencies.add(frequency);
			frequency += changes[(frequencies.size() - 1) % changes.length];
		}
		return String.valueOf(frequency);
	}
}

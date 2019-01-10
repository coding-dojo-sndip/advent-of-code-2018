package fr.insee.aoc;

import fr.insee.aoc.utils.DayUtils;

public class Day01 implements Day {

	public String part1(String input) {
		int sum = DayUtils.readLines(input).stream().mapToInt(Integer::valueOf).sum();
		return String.valueOf(sum);
	}
}

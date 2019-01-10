package fr.insee.aoc;

import static fr.insee.aoc.utils.DayUtils.streamLines;

public class Day01 implements Day {

	public String part1(String input) {
		int sum = streamLines(input).mapToInt(Integer::valueOf).sum();
		return String.valueOf(sum);
	}
	
	public String part2(String input) {
		
		return null;
	}
}

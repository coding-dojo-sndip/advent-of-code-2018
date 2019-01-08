package fr.insee.aoc;

public class Day01 implements Day {

	@Override
	public String part1(String input) {
		int frequency = DayUtils.readLines(input).stream()
			.mapToInt(Integer::parseInt)
			.sum();
		return String.valueOf(frequency);
	}
}

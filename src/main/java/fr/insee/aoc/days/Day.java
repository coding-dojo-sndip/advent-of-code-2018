package fr.insee.aoc.days;

import fr.insee.aoc.utils.DayException;

public interface Day {

	default String part1(String input, Object... params) {
		throw new DayException("Not implemented yet!");
	}

	default String part2(String input, Object... params) {
		throw new DayException("Not implemented yet!");
	}
}

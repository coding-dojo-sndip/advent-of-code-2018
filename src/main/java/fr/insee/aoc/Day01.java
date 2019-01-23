package fr.insee.aoc;

import static fr.insee.aoc.Days.arrayOfInt;
import static fr.insee.aoc.Days.notIn;
import static fr.insee.aoc.Days.streamOfInt;

import java.util.HashSet;
import java.util.Set;

public class Day01 implements Day {

	public String part1(String input) {
		int sum = streamOfInt(input).sum();
		return String.valueOf(sum);
	}

	public String part2(String input) {
		Set<Integer> frequencies = new HashSet<>();
		int[] changes = arrayOfInt(input);
		int frequency = 0;
		int n = 0;
		while (notIn(frequency, frequencies)) {
			frequencies.add(frequency);
			frequency += changes[n % changes.length];
			n++;
		}
		return String.valueOf(frequency);
	}
}

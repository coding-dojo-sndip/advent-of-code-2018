package fr.insee.aoc;

import static fr.insee.aoc.Days.readLines;
import static fr.insee.aoc.Days.streamLines;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day01 implements Day {

	public String part1(String input) {
		int sum = streamLines(input).mapToInt(Integer::valueOf).sum();
		return String.valueOf(sum);
	}
	
	public String part2(String input) {
		Set<Integer> frequencies = new HashSet<>();
		List<String> changes = readLines(input);
		int frequency = 0;
		while(true) {
			if(frequencies.contains(frequency)) return String.valueOf(frequency);
			frequencies.add(frequency);
			frequency += Integer.valueOf(changes.get((frequencies.size() - 1) % changes.size()));
		}
	}
}

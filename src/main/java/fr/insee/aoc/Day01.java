package fr.insee.aoc;

import static fr.insee.aoc.Days.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day01 implements Day {

	public String part1(String input) {
		int sum = streamOfLines(input).mapToInt(Integer::valueOf).sum();
		return String.valueOf(sum);
	}
	
	public String part2(String input) {
		Set<Integer> frequencies = new HashSet<>();
		List<String> changes = listOfLines(input);
		int frequency = 0;
		while(true) {
			if(frequencies.contains(frequency)) return String.valueOf(frequency);
			frequencies.add(frequency);
			frequency += Integer.valueOf(changes.get((frequencies.size() - 1) % changes.size()));
		}
	}
}

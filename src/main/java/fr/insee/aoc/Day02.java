package fr.insee.aoc;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static fr.insee.aoc.Days.*;

public class Day02 implements Day {

	@Override
	public String part1(String input) {
		List<String>strings = listOfLines(input);
		long a = strings.stream().map(this::occurences).filter(o -> containsSameCharacter(o, 2)).count();
		long b = strings.stream().map(this::occurences).filter(o -> containsSameCharacter(o, 3)).count();
		return String.valueOf(a*b);
	}
	
	private Map<Integer, Long>occurences(String string){
		return string.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
	
	private boolean containsSameCharacter(Map<Integer, Long> occurences, int numberOfOccurences) {
		return occurences.values().contains(Long.valueOf(numberOfOccurences));
	}
	
}

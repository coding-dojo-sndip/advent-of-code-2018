package fr.insee.aoc;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Day02 implements Day {

	@Override
	public String part1(String input) {
		// TODO Auto-generated method stub
		return Day.super.part1(input);
	}
	
	private Map<Integer, Long>occurences(String string){
		return string.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}
	
}

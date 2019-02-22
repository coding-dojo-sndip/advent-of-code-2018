package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

import fr.insee.aoc.days.Day;
import fr.insee.aoc.days.Day17;

public class Day17Test {
    
	private Day day = new Day17();
	
	@Test
	public void case1_0() {
		Assert.assertEquals("57", day.part1("src/test/resources/17-0.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/17.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("", answer);
	}
}

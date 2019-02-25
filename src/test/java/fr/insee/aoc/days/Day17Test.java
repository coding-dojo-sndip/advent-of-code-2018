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
	public void case2_0() {
		Assert.assertEquals("29", day.part2("src/test/resources/17-0.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/17.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("31471", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/17.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("24169", answer);
	}
}

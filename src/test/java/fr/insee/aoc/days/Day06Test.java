package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day06Test {
    
	private Day day = new Day06();
	
	@Test
	public void case1_0() {
		Assert.assertEquals("17", day.part1("src/test/resources/06-0.txt"));
	}
	
	@Test
	public void case2_0() {
		Assert.assertEquals("16", day.part2("src/test/resources/06-0.txt", 32));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/06.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("3290", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/06.txt", 10_000);
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("45602", answer);
	}
}

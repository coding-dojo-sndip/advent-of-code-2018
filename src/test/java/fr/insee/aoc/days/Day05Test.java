package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day05Test {
    
	private Day day = new Day05();
	
	@Test
	public void case1_0() {
		Assert.assertEquals("10", day.part1("src/test/resources/05-0.txt"));
	}
	
	@Test
	public void case2_0() {
		Assert.assertEquals("4", day.part2("src/test/resources/05-0.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/05.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("11720", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/05.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("", answer);
	}
}

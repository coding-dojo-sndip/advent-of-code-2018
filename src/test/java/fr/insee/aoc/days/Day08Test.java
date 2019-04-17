package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day08Test {
    
	private Day day = new Day08();
	
	@Test
	public void case1_0() {
		Assert.assertEquals("138", day.part1("src/test/resources/08-0.txt"));
	}
	
	@Test
	public void case2_0() {
		Assert.assertEquals("66", day.part2("src/test/resources/08-0.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/08.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("48260", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/08.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("25981", answer);
	}
}

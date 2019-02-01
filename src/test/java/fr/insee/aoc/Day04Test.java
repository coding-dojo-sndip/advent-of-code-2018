package fr.insee.aoc;

import org.junit.Assert;
import org.junit.Test;

public class Day04Test  {
    
	private Day day = new Day04();
	
	@Test
	public void case1_0() {
		Assert.assertEquals("240", day.part1("src/test/resources/04-0.txt"));
	}
	
	@Test
	public void case2_0() {
		Assert.assertEquals("4455", day.part2("src/test/resources/04-0.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/04.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/04.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("", answer);
	}
}

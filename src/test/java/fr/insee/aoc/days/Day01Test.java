package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day01Test  {
    
	private Day day = new Day01();
	
	@Test
	public void case0() {
		Assert.assertEquals("3", day.part1("src/test/resources/01-0.txt"));
	}
	
	@Test
	public void case1() {
		Assert.assertEquals("3", day.part1("src/test/resources/01-1.txt"));
	}
	
	@Test
	public void case2() {
		Assert.assertEquals("0", day.part1("src/test/resources/01-2.txt"));
	}
	
	@Test
	public void case3() {
		Assert.assertEquals("-6", day.part1("src/test/resources/01-3.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/01.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("592", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/01.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("241", answer);
	}
}

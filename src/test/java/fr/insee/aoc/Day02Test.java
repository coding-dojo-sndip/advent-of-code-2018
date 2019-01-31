package fr.insee.aoc;

import org.junit.Assert;
import org.junit.Test;

public class Day02Test  {
    
	private Day day = new Day02();
	
	@Test
	public void case0() {
		Assert.assertEquals("12", day.part1("src/test/resources/02-0.txt"));
	}
	
	@Test
	public void case1() {
		Assert.assertEquals("fgij", day.part2("src/test/resources/02-1.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/02.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("6150", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/02.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("rteotyxzbodglnpkudawhijsc", answer);
	}
}

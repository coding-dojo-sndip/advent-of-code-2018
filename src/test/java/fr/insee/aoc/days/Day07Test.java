package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day07Test {
    
	private Day day = new Day07();
	
	@Test
	public void case1_0() {
		Assert.assertEquals("CABDFE", day.part1("src/test/resources/07-0.txt"));
	}
	
	@Test
	public void case2_0() {
		Assert.assertEquals("15", day.part2("src/test/resources/07-0.txt", 2, 0));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/07.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("PFKQWJSVUXEMNIHGTYDOZACRLB", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/07.txt", 5, 60);
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("864", answer);
	}
}

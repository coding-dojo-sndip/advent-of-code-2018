package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day11Test {

	private Day day = new Day11();

	@Test
	public void case1_0() {
		Assert.assertEquals("33,45", day.part1("src/test/resources/11-0.txt"));
	}
	
	@Test
	public void case1_1() {
		Assert.assertEquals("21,61", day.part1("src/test/resources/11-1.txt"));
	}

	@Test
	public void case2_0() {
		Assert.assertEquals("90,269,16", day.part2("src/test/resources/11-0.txt"));
	}
	
	@Test
	public void case2_1() {
		Assert.assertEquals("232,251,12", day.part2("src/test/resources/11-1.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/11.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("20,32", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/11.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("235,287,13", answer);
	}
}

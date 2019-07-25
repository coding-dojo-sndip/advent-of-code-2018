package fr.insee.aoc.days;

import org.junit.*;

public class Day13Test {

	private Day day = new Day13();

	@Test
	public void case1_0() {
		Assert.assertEquals("7,3", day.part1("src/test/resources/13-0.txt"));
	}
	
	@Test
	public void case2_0() {
		Assert.assertEquals("6,4", day.part1("src/test/resources/13-1.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/13.txt");
		System.out.println(String.format("%s.1:%n%s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("113,136", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/13.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("114,136", answer);
	}
}

package fr.insee.aoc.days;

import org.junit.*;

public class Day12Test {

	private Day day = new Day12();

	@Test
	public void case1_0() {
		Assert.assertEquals("325", day.part1("src/test/resources/12-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/12.txt");
		System.out.println(String.format("%s.1:%n%s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("2767", answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/12.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("2650000001362", answer);
	}
}

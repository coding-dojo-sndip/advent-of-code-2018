package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day09Test {
    
	private Day day = new Day09();
	
	@Test
	public void case1_0() {
		Assert.assertEquals("32", day.part1("", 9, 25));
	}
	
	@Test
	public void case1_1() {
		Assert.assertEquals("8317", day.part1("", 10, 1618));
	}
	
	@Test
	public void case1_2() {
		Assert.assertEquals("146373", day.part1("", 13, 7999));
	}
	
	@Test
	public void case1_3() {
		Assert.assertEquals("2764", day.part1("", 17, 1104));
	}
	
	@Test
	public void case1_4() {
		Assert.assertEquals("54718", day.part1("", 21, 6111));
	}
	
	@Test
	public void case1_5() {
		Assert.assertEquals("37305", day.part1("", 30, 5807));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/09.txt", 410, 72059);
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("429287", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/09.txt", 410, 72059);
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("3624387659", answer);
	}
}

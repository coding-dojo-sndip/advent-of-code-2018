package fr.insee.aoc;

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
	public void case4() {
		Assert.assertEquals("2", day.part2("src/test/resources/01-4.txt"));
	}
	
	@Test
	public void case5() {
		Assert.assertEquals("0", day.part2("src/test/resources/01-5.txt"));
	}
	
	@Test
	public void case6() {
		Assert.assertEquals("10", day.part2("src/test/resources/01-6.txt"));
	}
	
	@Test
	public void case7() {
		Assert.assertEquals("5", day.part2("src/test/resources/01-7.txt"));
	}
	
	@Test
	public void case8() {
		Assert.assertEquals("14", day.part2("src/test/resources/01-8.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/01.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/01.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("", answer);
	}
}

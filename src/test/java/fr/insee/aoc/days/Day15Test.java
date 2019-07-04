package fr.insee.aoc.days;

import org.junit.*;

public class Day15Test {

	private Day day = new Day15();

	@Test
	public void case1_0() {
		Assert.assertEquals("27730", day.part1("src/test/resources/15-0.txt"));
	}
	
	@Test
	public void case1_1() {
		Assert.assertEquals("36334", day.part1("src/test/resources/15-1.txt"));
	}
	
	@Test
	public void case1_2() {
		Assert.assertEquals("39514", day.part1("src/test/resources/15-2.txt"));
	}
	
	@Test
	public void case1_3() {
		Assert.assertEquals("27755", day.part1("src/test/resources/15-3.txt"));
	}
	
	@Test
	public void case1_4() {
		Assert.assertEquals("28944", day.part1("src/test/resources/15-4.txt"));
	}
	
	@Test
	public void case1_5() {
		Assert.assertEquals("18740", day.part1("src/test/resources/15-5.txt"));
	}

	@Test
	public void case2_0() {
		Assert.assertEquals("4988", day.part2("src/test/resources/15-0.txt"));
	}
	
	@Test
	public void case2_2() {
		Assert.assertEquals("31284", day.part2("src/test/resources/15-2.txt"));
	}
	
	@Test
	public void case2_3() {
		Assert.assertEquals("3478", day.part2("src/test/resources/15-3.txt"));
	}
	
	@Test
	public void case2_4() {
		Assert.assertEquals("6474", day.part2("src/test/resources/15-4.txt"));
	}
	
	@Test
	public void case2_5() {
		Assert.assertEquals("1140", day.part2("src/test/resources/15-5.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/15.txt");
		System.out.println(String.format("%s.1:%n%s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("189910", answer);
	}
	
	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/15.txt");
		System.out.println(String.format("%s.1:%n%s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("57820", answer);
	}
}

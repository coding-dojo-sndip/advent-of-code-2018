package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Test;

public class Day16Test {

	private Day day = new Day16();

	@Test
	public void case1_0() {
		Assert.assertEquals("663", day.part1("src/main/resources/16-samples.txt"));
	}

	@Test
	public void case1_1() {
		Assert.assertEquals("1", day.part1("src/test/resources/16-0.txt"));
	}

	@Test
	public void case2_0() {
		Assert.assertEquals("", day.part2("src/main/resources/16-samples.txt", "src/main/resources/16-test.txt"));
	}

}

package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.streamOfLines;
import static java.util.stream.Collectors.joining;

import org.junit.Assert;
import org.junit.Test;

public class Day10Test {

	private Day day = new Day10();

	private static String textFromFile(String answerFile) {
		return streamOfLines(answerFile).collect(joining(String.format("%n")));
	}

	@Test
	public void case1_0() {
		Assert.assertEquals(textFromFile("src/test/resources/10-0.sol"), day.part1("src/test/resources/10-0.txt"));
	}

	@Test
	public void case2_0() {
		Assert.assertEquals("3", day.part2("src/test/resources/10-0.txt"));
	}

	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/10.txt");
		System.out.println(String.format("%s.1:%n%s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals(textFromFile("src/main/resources/10.sol"), answer);
	}

	@Test
	public void part2() {
		String answer = day.part2("src/main/resources/10.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("10476", answer);
	}
}

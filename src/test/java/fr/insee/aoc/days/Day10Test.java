package fr.insee.aoc.days;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;


import static fr.insee.aoc.utils.Days.*;
import static java.util.stream.Collectors.*;

public class Day10Test {
    
	private Day day = new Day10();

	private static String textFromFile(String answerFile) {
	    return streamOfLines(answerFile).collect(joining(String.format("%n")));
    }

	@Test
	public void case1_0() {
		Assert.assertEquals(textFromFile("src/test/resources/10-s.txt"), day.part1("src/test/resources/10-0.txt"));
	}
	
	@Test
	public void part1() {
		String answer = day.part1("src/main/resources/10.txt");
		System.out.println(String.format("%s.1: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals(textFromFile("src/main/resources/10-s.txt"), answer);
	}

	@Test
    @Ignore
	public void part2() {
		String answer = day.part2("src/main/resources/10.txt");
		System.out.println(String.format("%s.2: %s", day.getClass().getSimpleName(), answer));
		Assert.assertEquals("-1", answer);
	}
}

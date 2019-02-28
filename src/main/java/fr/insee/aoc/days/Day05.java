package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readLine;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class Day05 implements Day {

	@Override
	public String part1(String input, Object... params) {
		String polymer = readLine(input);
		return String.valueOf(reaction(polymer));
	}

	private int reaction(String polymer) {
		Deque<Character> queue = new ArrayDeque<>();
		for (int i = 0; i < polymer.length(); i++) {
			char currentChar = polymer.charAt(i);
			if(queue.isEmpty() || !unitReact(currentChar, queue.peek())) {
				queue.push(currentChar);
			} else {				
				queue.poll();
			}
		}
		return queue.size();
	}

	public boolean unitReact(char a, char b) {
		return Math.abs(a - b) == 32;
	}

	@Override
	public String part2(String input, Object... params) {
		String polymer = readLine(input);
		int min = IntStream.rangeClosed('a', 'z')
				.mapToObj(c -> polymer.replaceAll(""+(char)c+"|"+(char)(c-32)+"", ""))
				.mapToInt(p -> reaction(p))
				.min()
				.orElse(-1); // IntOptionnal
		return String.valueOf(min);
	}

}

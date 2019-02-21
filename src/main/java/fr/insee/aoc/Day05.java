package fr.insee.aoc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class Day05 implements Day {

	@Override
	public String part1(String input, Object... params) {
		String polymer = Days.readLine(input);
		return String.valueOf(reaction(polymer));
	}

	private int reaction(String polymer) {
		Deque<Character> res = new ArrayDeque<>();
		for (int i = 0; i < polymer.length(); i++) {
			char currentChar = polymer.charAt(i);
			if(res.isEmpty() || !unitReact(currentChar, res.peek())) {
				res.push(currentChar);
			} else {				
				res.poll();
			}
		}
		return res.size();
	}

	public boolean unitReact(char a, char b) {
		return Math.abs(a - b) == 32;
	}

	@Override
	public String part2(String input, Object... params) {
		String polymer = Days.readLine(input);
		int min = /*IntStream.rangeClosed('a', 'z')
				.mapTo(c -> polymer.replaceAll(""+(char)c+"|"+(char)(c-32)+"", "")) //mapToObj
				.mapToInt(p -> reaction(p))
				.min(); // IntOptionnal
		*/ 0;
		return String.valueOf(min);
	}

}

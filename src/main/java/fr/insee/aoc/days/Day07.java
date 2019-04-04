package fr.insee.aoc.days;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Day07 implements Day {

	private static final Pattern pattern = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin");

	@Override
	public String part1(String input, Object... params) {
		Map<Character, Step> steps = new HashMap<>(26);

		return "";
	}

	static class Step implements Comparable<Step> {
		char id;
		List<Character> prerequisites = new ArrayList<>(26);

		boolean ready(List<Character> stepsDone) {
			return stepsDone.containsAll(prerequisites);
		}

		@Override
		public int compareTo(Step other) {
			return this.id - other.id;
		}
	}

}

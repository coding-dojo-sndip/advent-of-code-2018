package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readChar;
import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.insee.aoc.utils.DayException;

public class Day07 implements Day {

	private static final Pattern pattern = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin.");

	@Override
	public String part1(String input, Object... params) {
		Map<Character, Step> steps = new HashMap<>(26);
		streamOfLines(input).forEach(line -> readLine(steps, line));
		List<Character> stepsDone = new ArrayList<>();
		while(!steps.isEmpty()) {
			Step step = steps.values().stream()
					.filter(s -> s.ready(stepsDone))
					.sorted()
					.findFirst()
					.orElseThrow(DayException::new);
			stepsDone.add(step.id);
			steps.remove(step.id);
		}

		return stepsDone.stream().map(c -> c.toString()).collect(Collectors.joining());
	}

	private void readLine(Map<Character, Step> steps, String line) {
		Matcher matcher = pattern.matcher(line);
		if(matcher.matches()) {
			char prerequisite = readChar(1, matcher);
			char stepId = readChar(2, matcher);
			Step step = steps.computeIfAbsent(stepId, Step::new);
			step.prerequisites.add(prerequisite);
			steps.computeIfAbsent(prerequisite, Step::new);
		}
	}

	static class Step implements Comparable<Step> {
		char id;
		List<Character> prerequisites = new ArrayList<>(26);

		public Step(char id) {
			super();
			this.id = id;
		}

		boolean ready(List<Character> stepsDone) {
			return stepsDone.containsAll(prerequisites);
		}

		@Override
		public int compareTo(Step other) {
			return this.id - other.id;
		}

		@Override
		public boolean equals(Object object) {
			if(object == null) return false;
			if(object instanceof Step) {
				Step other = (Step) object;
				return Objects.equals(this.id, other.id);
			}
			return false;
		}
	}

}

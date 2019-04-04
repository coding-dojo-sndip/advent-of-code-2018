package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readChar;
import static fr.insee.aoc.utils.Days.streamOfLines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.util.stream.Collectors.*;

import fr.insee.aoc.utils.DayException;

public class Day07 implements Day {

	private static final Pattern pattern = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin.");

	@Override
	public String part1(String input, Object... params) {
		Map<Character, Step> steps = new HashMap<>(26);
		streamOfLines(input).forEach(line -> readLine(steps, line));
		List<Character> stepsDone = new ArrayList<>();
		while (!steps.isEmpty()) {
			Step step = nextStep(steps, stepsDone);
			stepsDone.add(step.id);
			steps.remove(step.id);
		}
		return stepsDone.stream().map(String::valueOf).collect(joining());
	}

	@Override
	public String part2(String input, Object... params) {
		int numberOfWorkers = (int) params[0];
		int amountOfTime = (int) params[1];
		// TODO
		return null;
	}
	
	private static Step nextStep(Map<Character, Step> steps, List<Character> stepsDone) {
		return steps.values()
			.stream()
			.filter(s -> s.isReady(stepsDone))
			.sorted()
			.findFirst()
			.orElseThrow(DayException::new);
	}


	private static void readLine(Map<Character, Step> steps, String line) {
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches()) {
			char prerequisiteId = readChar(1, matcher);
			char stepId = readChar(2, matcher);
			Step step = steps.computeIfAbsent(stepId, Step::new);
			step.prerequisites.add(prerequisiteId);
			steps.computeIfAbsent(prerequisiteId, Step::new);
		}
	}

	static class Step implements Comparable<Step> {
		char id;
		int remainingTime;
		List<Character> prerequisites = new ArrayList<>(26);

		public Step(char id) {
			this.id = id;
		}

		public Step(char id, int amountOfTime) {
			this.id = id;
			this.remainingTime = amountOfTime + id + 1 - 'A';
		}

		boolean isReady(List<Character> stepsDone) {
			return stepsDone.containsAll(prerequisites);
		}
		
		public boolean isOver() {
			return remainingTime == 0;
		}

		@Override
		public int compareTo(Step other) {
			return this.id - other.id;
		}
	}

	private static class Worker {
		Step step;

		public boolean isIdle() {
			return step == null;
		}

		public void work() {
			if (step != null) {
				step.remainingTime --;
			}
		}
	}
}

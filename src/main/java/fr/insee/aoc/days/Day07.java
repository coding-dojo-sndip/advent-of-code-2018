package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readChar;
import static fr.insee.aoc.utils.Days.streamOfLines;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;

public class Day07 implements Day {

	private static final Pattern pattern = Pattern.compile("Step ([A-Z]) must be finished before step ([A-Z]) can begin.");
	
	@Override
	public String part1(String input, Object... params) {
		Set<Step> remainingSteps = streamOfLines(input).collect(toSteps());
		List<Character> completedSteps = new ArrayList<>(26);
		while(!remainingSteps.isEmpty()) {
			Step nextStep = nextStep(completedSteps, remainingSteps);
			remainingSteps.remove(nextStep);
			completedSteps.add(nextStep.id);
		}
		return completedSteps.stream().map(String::valueOf).collect(joining());
	}

	private Step nextStep(List<Character> completedSteps, Set<Step> remainingSteps) {
		return remainingSteps.stream()
			.filter(step -> step.canBecompletedSteps(completedSteps))
			.sorted()
			.findFirst()
			.orElse(null);
	}
	
	static void readLine(Map<Character, Step> steps, String line) {
		Matcher matcher = pattern.matcher(line);
		if(matcher.matches()) {
			char prerequisite = readChar(1, matcher);
			char id = readChar(2, matcher);
			steps.computeIfAbsent(prerequisite, Step::withId);
			Step step = steps.computeIfAbsent(id, Step::withId);
			step.prerequisites.add(prerequisite);
		}
	}
	
	static class Step implements Comparable<Step> {

		Character id;
		Set<Character> prerequisites = new HashSet<>();

		private Step(Character id) {
			this.id = id;
		}
		
		static Step withId(Character id) {
			return new Step(id);
		}
		
		boolean canBecompletedSteps(List<Character> completedSteps) {
			return completedSteps.containsAll(prerequisites);
		}
		
		@Override
		public int compareTo(Step other) {
			return this.id.compareTo(other.id);
		}
		
		@Override
		public int hashCode() {
			return id;
		}
		
		@Override
		public boolean equals(Object object) {
			if (this == object) return true;
			if (object == null) return false;
			if(object instanceof Step) {
				Step other = (Step) object;
				return this.id == other.id;
			}
			return false;
		}
	}
	
	static Collector<String, Map<Character, Step>, Set<Step>> toSteps() {
		return new Collector<String, Map<Character, Step>, Set<Step>>() {

			@Override
			public Supplier<Map<Character, Step>> supplier() {
				return () -> new HashMap<>(26);
			}

			@Override
			public BiConsumer<Map<Character, Step>, String> accumulator() {
				return Day07::readLine;
			}

			@Override
			public BinaryOperator<Map<Character, Step>> combiner() {
				return (a, b) -> null;
			}

			@Override
			public Function<Map<Character, Step>, Set<Step>> finisher() {
				return steps -> new HashSet<>(steps.values());
			}

			@Override
			public Set<Characteristics> characteristics() {
				return EnumSet.of(Characteristics.UNORDERED);
			}
		};
	}
}

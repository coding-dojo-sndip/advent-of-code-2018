package fr.insee.aoc.days;

import java.util.*;
import java.util.regex.*;
import java.util.stream.*;

import static fr.insee.aoc.utils.Days.*;

import static java.util.stream.Collectors.*;

import fr.insee.aoc.utils.*;

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
		Set<Worker> workers = createWorkers(numberOfWorkers);
		List<Character> stepsDone = new ArrayList<>();
		Map<Character, Step> steps = new HashMap<>(26);
		streamOfLines(input).forEach(line -> readLine(steps, line, amountOfTime));
		int elapsedTime = 0;
		while (!steps.isEmpty()) {
			LinkedList<Step> availableSteps = availableSteps(steps, stepsDone);
			availableWorkers(workers).forEach(worker -> worker.step = availableSteps.pollFirst());
			workers.forEach(Worker::work);
			List<Character> stepsOver = steps.values()
				.stream()
				.filter(Step::isOver)
				.map(step -> step.id)
				.collect(toList());
			stepsDone.addAll(stepsOver);
			stepsOver.forEach(steps::remove);
			elapsedTime ++;
		}	
		return String.valueOf(elapsedTime);
	}
	
	private static Step nextStep(Map<Character, Step> steps, List<Character> stepsDone) {
		return steps.values()
			.stream()
			.filter(s -> s.isReady(stepsDone))
			.sorted()
			.findFirst()
			.orElseThrow(DayException::new);
	}
	
	private static LinkedList<Step> availableSteps(Map<Character, Step> steps, List<Character> stepsDone) {
		return steps.values()
			.stream()
			.filter(step -> step.isReady(stepsDone))
			.filter(step -> !step.isRunning())
			.sorted()
			.collect(toCollection(LinkedList::new));
	}
	
	private static Set<Worker> availableWorkers(Set<Worker> workers) {
		return workers
			.stream()
			.filter(Worker::isIdle)
			.collect(toSet());
	}

	private static void readLine(Map<Character, Step> steps, String line) {
		readLine(steps, line, 0);
	}
	
	private static void readLine(Map<Character, Step> steps, String line, int amountOfTime) {
		Matcher matcher = pattern.matcher(line);
		if (matcher.matches()) {
			char prerequisiteId = readChar(1, matcher);
			char stepId = readChar(2, matcher);
			Step step = steps.computeIfAbsent(stepId, id -> new Step(id, amountOfTime));
			step.prerequisites.add(prerequisiteId);
			steps.computeIfAbsent(prerequisiteId, id -> new Step(id, amountOfTime));
		}
	}

	private Set<Worker> createWorkers(int numberOfWorkers) {
		return IntStream.range(0, numberOfWorkers)
			.mapToObj(n -> new Worker())
			.collect(toSet());
	}
	
	static class Step implements Comparable<Step> {
		char id;
		int remainingTime;
		int totalTime;
		List<Character> prerequisites = new ArrayList<>(26);

		public Step(char id) {
			this.id = id;
		}

		public Step(char id, int amountOfTime) {
			this.id = id;
			this.totalTime = amountOfTime + id + 1 - 'A';
			this.remainingTime = this.totalTime;
		}

		boolean isReady(List<Character> stepsDone) {
			return stepsDone.containsAll(prerequisites);
		}
		
		boolean isRunning() {
			return remainingTime < totalTime;
		}
		
		public boolean isOver() {
			return remainingTime == 0;
		}

		@Override
		public int compareTo(Step other) {
			return this.id - other.id;
		}

		@Override
		public boolean equals(Object object) {
			if(object instanceof Step) {
				Step other = (Step) object;
				return Objects.equals(this.id, other.id);
			}
			return false;
		}

		@Override
		public int hashCode() {
			return id;
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
				if (step.remainingTime == 0) {
					step = null;
				}
			}
		}
	}
}

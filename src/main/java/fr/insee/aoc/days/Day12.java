package fr.insee.aoc.days;

import java.util.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;
import static fr.insee.aoc.utils.Days.*;

public class Day12 implements Day {
	
	@Override
	public String part1(String input, Object... params) {
		var pots = potsAfterGenerations(input, 20);
		var score = score(pots);
		return String.valueOf(score);
	}
	
	@Override
	public String part2(String input, Object... params) {
		var base = 195;
		var pots = potsAfterGenerations(input, base);
		var baseScore = score(pots);
		var numberOfPlants = countPlants(pots);
		return String.valueOf(baseScore + (50_000_000_000L - base) * numberOfPlants);
	}

	List<Pot> potsAfterGenerations(String input, int numberOfGenerations) {
		var lines = arrayOfLines(input);
		var initialState = initialState(lines);
		var rules = rules(lines);
		var pots = pots(initialState);
		for(var g = 0; g < numberOfGenerations; g ++) {
			addEmptyPots(pots);
			for(int i = 2; i < pots.size() - 2; i ++) {
				pots.get(i).computeNextState(rules, pots);
			}
			pots.forEach(Pot::changeState);
		}
		return pots;
	}
	
	String initialState(String[] lines) {
		return lines[0].replace("initial state: ", "");
	}
	
	List<String> rules(String[] lines) {
		return Arrays.stream(lines)
			.filter(line -> line.endsWith("=> #"))
			.map(line -> line.substring(0, 5))
			.collect(toList());
	}
	
	LinkedList<Pot> pots(String initialState) {
		return IntStream.range(0, initialState.length())
			.mapToObj(i -> new Pot(i, initialState.charAt(i)))
			.collect(toCollection(LinkedList::new));
	}
	
	int score(Collection<Pot> pots) {
		return pots.stream().filter(pot -> !pot.isEmpty()).mapToInt(pot -> pot.index).sum();
	}
	
	long countPlants(Collection<Pot> pots) {
		return pots.stream().filter(pot -> !pot.isEmpty()).count();
	}
	
	void addEmptyPots(Deque<Pot> pots) {
		var numberOfLeadingEmptyPots = pots.stream().takeWhile(Pot::isEmpty).count();
		if(numberOfLeadingEmptyPots < 3) {
			for (var i = 0; i < 3 - numberOfLeadingEmptyPots; i++) {
				addLeadingPot(pots);
			}
		}
		var numberOfTrailingEmptyPots = reverseStream(pots).takeWhile(Pot::isEmpty).count();
		if (numberOfTrailingEmptyPots < 3) {
			for (var i = 0; i < 3 - numberOfTrailingEmptyPots; i++) {
				addTrailingPot(pots);
			}
		}
	}
	
	void addLeadingPot(Deque<Pot> pots) {
		var firstPot = pots.peekFirst();
		var leadingPot = new Pot(firstPot.index - 1, '.');
		pots.addFirst(leadingPot);
	}
	
	void addTrailingPot(Deque<Pot> pots) {
		var lastPot = pots.peekLast();
		var trailingPot = new Pot(lastPot.index + 1, '.');
		pots.addLast(trailingPot);
	}
	
	static class Pot {
		int index;
		char state;
		char nextState;
		
		Pot(int index, char state) {
			this.index = index;
			this.state = state;
			this.nextState = Character.MIN_VALUE;
		}
		
		void computeNextState(Collection<String> rules, List<Pot> pots) {
			var i = pots.indexOf(this);
			var pattern = new StringBuilder(5)
				.append(pots.get(i - 2).state)
				.append(pots.get(i - 1).state)
				.append(this.state)
				.append(pots.get(i + 1).state)
				.append(pots.get(i + 2).state)
				.toString();
			this.nextState = rules.contains(pattern) ? '#' : '.';
			 
		}
		
		void changeState() {
			this.state = this.nextState == Character.MIN_VALUE ? this.state : this.nextState;
		}
		
		boolean isEmpty() {
			return this.state == '.';
		}
	}
}
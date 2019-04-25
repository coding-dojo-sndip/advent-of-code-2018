package fr.insee.aoc.days;

import static java.util.stream.Collectors.toMap;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import static java.util.function.Function.*;
import java.util.stream.IntStream;

public class Day09 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int numberOfPlayers = (int) params[0];
		int marbleMaxValue = (int) params[1];
		long maxScore = play(numberOfPlayers, marbleMaxValue);
		return String.valueOf(maxScore);
	}
	
	@Override
	public String part2(String input, Object... params) {
		return part1(input, params[0], (int) params[1] * 100);
	}

	private long play(int numberOfPlayers, int marbleMaxValue) {
		Map<Integer, Long> scores = IntStream
			.rangeClosed(1, numberOfPlayers)
			.boxed()
			.collect(toMap(identity(), t -> 0L));

		List<Integer> marbles = new LinkedList<>();
		ListIterator<Integer> listIterator = marbles.listIterator();

		int currentPlayer = 0;
		listIterator.add(0);

		for (int marble = 1; marble <= marbleMaxValue; marble++) {
			currentPlayer = (currentPlayer + 1) % numberOfPlayers;

			if (marble % 23 != 0) {
				listIterator = moveNext(marbles, listIterator);
				listIterator.add(marble);
			} else {
				for (int j = 0; j <= 7; j++) {
					listIterator = movePrevious(marbles, listIterator);
				}
				listIterator.next();
				int points = listIterator.previous() + marble;
				scores.computeIfPresent(currentPlayer, (joueur, score) -> score + points);
				listIterator.remove();
				moveNext(marbles, listIterator);
			}
		}
		long maxScore = scores.values()
			.stream()
			.mapToLong(Long::longValue)
			.max()
			.orElse(-1);
		return maxScore;
	}
	
	private static ListIterator<Integer> movePrevious(List<Integer> marbles, ListIterator<Integer> listIterator) {
		if (!listIterator.hasPrevious()) {
			listIterator = marbles.listIterator(marbles.size());
		}
		listIterator.previous();
		return listIterator;
	}

	private static ListIterator<Integer> moveNext(List<Integer> marbles, ListIterator<Integer> listIterator) {
		if (!listIterator.hasNext()) {
			listIterator = marbles.listIterator();
		}
		listIterator.next();
		return listIterator;
	}
}

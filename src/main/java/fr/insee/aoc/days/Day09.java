package fr.insee.aoc.days;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Day09 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int numberOfPlayers = (int) params[0];
		int marbleMaxValue = (int) params[1];
		long maxScore = maxScore(numberOfPlayers, marbleMaxValue);
		return String.valueOf(maxScore);
	}
	
	@Override
	public String part2(String input, Object... params) {
		return part1(input, params[0], (int) params[1] * 100);
	}

	private long maxScore(int numberOfPlayers, int marbleMaxValue) {
		Map<Integer, Long> scores = new HashMap<>(numberOfPlayers);

		List<Integer> marbles = new LinkedList<>();
		ListIterator<Integer> iterator = marbles.listIterator();

		int currentPlayer = 0;
		iterator.add(0);

		for (int marble = 1; marble <= marbleMaxValue; marble++) {
			currentPlayer = (currentPlayer + 1) % numberOfPlayers;
			if (marble % 23 != 0) {
				iterator = moveNext(marbles, iterator);
				iterator.add(marble);
			} else {
				for (int j = 0; j <= 7; j++) {
					iterator = movePrevious(marbles, iterator);
				}
				iterator.next();
				int points = iterator.previous() + marble;
				scores.compute(currentPlayer, (player, score) -> score == null ? points : score + points);
				iterator.remove();
				moveNext(marbles, iterator);
			}
		}
		return scores.values()
			.stream()
			.mapToLong(Long::longValue)
			.max()
			.orElse(-1);
	}
	
	private static ListIterator<Integer> movePrevious(List<Integer> marbles, ListIterator<Integer> iterator) {
		if (!iterator.hasPrevious()) {
			iterator = marbles.listIterator(marbles.size());
		}
		iterator.previous();
		return iterator;
	}

	private static ListIterator<Integer> moveNext(List<Integer> marbles, ListIterator<Integer> iterator) {
		if (!iterator.hasNext()) {
			iterator = marbles.listIterator();
		}
		iterator.next();
		return iterator;
	}
}

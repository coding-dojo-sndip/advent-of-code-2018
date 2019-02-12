package fr.insee.aoc;

import static fr.insee.aoc.Days.indexOfMax;
import static fr.insee.aoc.Days.maxOf;
import static fr.insee.aoc.Days.readDate;
import static fr.insee.aoc.Days.readInt;
import static fr.insee.aoc.Days.streamOfLines;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ToIntFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 implements Day {

	public String part1(String input) {
		Guard guard = selectGuard(guards(records(input)), Guard::totalAsleep);
		return String.valueOf(guard.maxAsleep() * guard.id);
	}

	public String part2(String input) {
		Guard guard = selectGuard(guards(records(input)), Guard::maxAsleepValue);
		return String.valueOf(guard.maxAsleep() * guard.id);
	}

	private Guard selectGuard(List<Guard> guards, ToIntFunction<Guard> strategy) {
		return guards.stream().max(comparingInt(strategy)).get();
	}

	List<Guard> guards(List<Record> records) {
		Map<Integer, Guard> guards = new HashMap<>();
		Guard guard = null;
		Integer fallsAsleepAt = null;
		for (Record record : records) {
			if (record.guardId != null) {
				guard = guards.computeIfAbsent(record.guardId, Guard::new);
				fallsAsleepAt = null;
			} else if (fallsAsleepAt == null) {
				fallsAsleepAt = record.date.getMinute();
			} else {
				for (int i = fallsAsleepAt; i < record.date.getMinute(); i++) {
					guard.minutes[i]++;
				}
				fallsAsleepAt = null;
			}

		}
		return new ArrayList<>(guards.values());
	}

	static class Record implements Comparable<Record> {
		private LocalDateTime date;
		private Integer guardId;
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		private static final Pattern pattern = Pattern
				.compile("\\[(.*)] (?:Guard #(\\d+) begins shift|wakes up|falls asleep)");

		public Record(String line) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				this.date = readDate(1, matcher, formatter);
				if (line.endsWith("begins shift")) {
					this.guardId = readInt(2, matcher);
				}
			}
		}

		@Override
		public int compareTo(Record o) {
			return this.date.compareTo(o.date);
		}
	}

	List<Record> records(String input) {
		return streamOfLines(input).map(Record::new).sorted().collect(toList());
	}

	static class Guard {
		int id;
		int[] minutes = new int[60];

		public Guard(int id) {
			super();
			this.id = id;
		}

		public int totalAsleep() {
			return Arrays.stream(minutes).sum();
		}

		public int maxAsleep() {
			return indexOfMax(minutes);
		}

		public int maxAsleepValue() {
			return maxOf(minutes);
		}
	}
}

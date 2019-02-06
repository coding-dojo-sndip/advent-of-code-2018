package fr.insee.aoc;

import static fr.insee.aoc.Days.groupInt;
import static fr.insee.aoc.Days.streamOfLines;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.ToLongFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 implements Day {

	@Override
	public String part1(String input) {
		return strategyResult(input, Guard::totalTimeAsleep);
	}

	@Override
	public String part2(String input) {
		return strategyResult(input, Guard::minuteMostAsleepCount);
	}

	private String strategyResult(String input, ToLongFunction<Guard> strategy) {
		List<Guard> guards = guards(input);
		Guard guard = selectGuard(guards, strategy);
		int a = guard.id;
		int b = guard.minuteMostAsleep();
		return String.valueOf(a * b);
	}

	private Guard selectGuard(List<Guard> guards, ToLongFunction<Guard> strategy) {
		return guards.stream()
			.max(Comparator.comparingLong(strategy))
			.get();
	}
	
	private static List<Record> records(String input) {
		return streamOfLines(input).map(Record::fromLine).sorted().collect(toList());
	}
	
	private static List<Guard> guards(String input) {
		Map<Integer, Guard> guards = new HashMap<>();
        Iterator<Record> records = records(input).iterator();
		Guard guard = new Guard();
		List<Integer> times = new ArrayList<>();
		while(records.hasNext()) {
		    Record record = records.next();
            if(record.shiftHasBegun() && records.hasNext()) {
				times.add(record.date.getMinute());
			}
			else {
				guard.updateMinutes(times);
				guard = guards.computeIfAbsent(record.guardId, Guard::new);
				times.clear();
			}
		}
		return new ArrayList<>(guards.values());
	}
	
	static class Record implements Comparable<Record> {
		
		private int guardId;
		private LocalDateTime date;

		private static final Comparator<Record> comparator = Comparator.comparing(r -> r.date);
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		private static final Pattern pattern = Pattern.compile("\\[(.+)] (?:Guard #(\\d+) begins shift|wakes up|falls asleep)");
		
		static Record fromLine(String line) {
			Record record = new Record();
			Matcher matcher = pattern.matcher(line);
			if(matcher.matches()) {
				record.date = LocalDateTime.parse(matcher.group(1), formatter);
				if(line.endsWith("begins shift")) record.guardId = groupInt(2, matcher);
			}
			return record;
		}

		boolean shiftHasBegun() {
		    return guardId == 0;
        }

		@Override
		public int compareTo(Record other) {
			return comparator.compare(this, other);
		}
	}
	
	static class Guard {
		private int id;
		private int[] minutes = new int[60];
		
		public Guard() {}
		
		public Guard(int id) {
			this.id = id;
		}
		
		void updateMinutes(List<Integer> times) {
			boolean asleep = false;
			for(int time = 0; time < 60; time ++) {
				if(times.contains(time)) asleep = !asleep;
				if(asleep) minutes[time] ++;  
			}
		}
		
		long totalTimeAsleep() {
			return Arrays.stream(minutes).sum();
		}
		
		int minuteMostAsleep() {
			List<Integer> listOfMinutes = Arrays.stream(minutes).boxed().collect(toList());
			return listOfMinutes.indexOf(Collections.max(listOfMinutes));
		}
		
		int minuteMostAsleepCount() {
			List<Integer> listOfMinutes = Arrays.stream(minutes).boxed().collect(toList());
			return Collections.max(listOfMinutes);
		}
	}
}

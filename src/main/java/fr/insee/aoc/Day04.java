package fr.insee.aoc;

import static fr.insee.aoc.Days.groupInt;
import static fr.insee.aoc.Days.streamOfLines;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.ToLongFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.Day04.Record.Type;

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
		List<Record> records = records(input);
		Map<Integer, Guard> guards = new HashMap<>();
		Guard guard = new Guard();
		List<Integer> times = new ArrayList<>();
		for(Record record : records) {
			if(record.type == Type.BEGINS_SHIFT) {
				if(!times.isEmpty()) guard.shifts.add(Shift.of(times));
				Integer id = record.guardId;
				guard = guards.computeIfAbsent(id, Guard::new);
				times.clear();
			}
			else {
				times.add(record.date.getMinute());
			}
		}
		guard.shifts.add(Shift.of(times));
		Integer id = guard.id;
		guards.computeIfAbsent(id, Guard::new);
		return new ArrayList<>(guards.values());
	}
	
	static class Record implements Comparable<Record> {
		
		private int guardId;
		private LocalDateTime date;
		private Type type;
		
		private static final Comparator<Record> comparator = Comparator.comparing(r -> r.date);
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		private static final Pattern pattern = Pattern.compile("\\[(.+)] (?:Guard #(\\d+) begins shift|wakes up|falls asleep)");
		
		static Record fromLine(String line) {
			Record record = new Record();
			Matcher matcher = pattern.matcher(line);
			if(matcher.matches()) {
				record.date = LocalDateTime.parse(matcher.group(1), formatter);
				if(line.endsWith("begins shift")) {
					record.type = Type.BEGINS_SHIFT;
					record.guardId = groupInt(2, matcher);
				}
				else if(line.endsWith("wakes up")) {
					record.type = Type.WAKES_UP;
				}
				else if(line.endsWith("falls asleep")) {
					record.type = Type.FALLS_ASLEEP;
				}
			}
			return record;
		}
		
		@Override
		public int compareTo(Record other) {
			return comparator.compare(this, other);
		}

		enum Type {
            BEGINS_SHIFT, WAKES_UP, FALLS_ASLEEP
        }
	}
	
	static class Guard {
		private int id;
		private List<Shift> shifts = new ArrayList<>();

		public Guard() {}
		
		public Guard(int id) {
			this.id = id;
		}
		
		long totalTimeAsleep() {
			return shifts.stream()
				.flatMap(s -> s.minutes.stream())
				.filter(m -> m.asleep)
				.count();
		}
		
		int minuteMostAsleep() {
			return shifts.stream()
				.flatMap(s -> s.minutes.stream())
				.filter(m -> m.asleep)
				.collect(groupingBy(m -> m.time, counting()))
				.entrySet().stream()
				.max(Entry.comparingByValue())
				.map(Entry::getKey)
				.orElse(-1);
		}
		
		long minuteMostAsleepCount() {
			return shifts.stream()
				.flatMap(s -> s.minutes.stream())
				.filter(m -> m.asleep)
				.collect(groupingBy(m -> m.time, counting()))
				.entrySet().stream()
				.max(Entry.comparingByValue())
				.map(Entry::getValue)
				.orElse(-1L);
		}
	}
	
	static class Shift {
		private List<Minute> minutes = new ArrayList<>();
		
		static Shift of(List<Integer> times) {
			Shift shift = new Shift();
			boolean asleep = false;
			for(int time = 0; time < 60; time ++) {
				if(times.contains(time)) asleep = !asleep;
				shift.minutes.add(new Minute(time, asleep));
			}
			return shift;
		}
	}
	
	static class Minute {
		private int time;
		private boolean asleep;
		
		Minute(int time, boolean asleep) {
			this.time = time;
			this.asleep = asleep;
		}
	}
}

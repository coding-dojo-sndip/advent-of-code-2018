package fr.insee.aoc;

import static fr.insee.aoc.Days.readDate;
import static fr.insee.aoc.Days.readInt;
import static fr.insee.aoc.Days.streamOfLines;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day04 implements Day {

	static class Record {
		private LocalDateTime date;
		private Integer guardId;
		private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd HH:mm]");
		private static final Pattern pattern = Pattern
				.compile("(\\[.*]) (?:Guard #(\\d+) begins shift|wakes up|falls asleep)");

		public Record(String line) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				this.date = readDate(1, matcher, formatter);
				if (matcher.groupCount() == 2) {
					this.guardId = readInt(2, matcher);
				}
			}

		}
	
	}
	
	
	List<Record> records(String input) {
		return streamOfLines(input)
				.map(Record::new)
				.sorted(Comparator.comparing(r -> r.date))
				.collect(Collectors.toList());
	}
	
	
	static class Guard{
		int id;
		int[] minutes = new int[60] ;
	}
	
	List<Guard> readRecords(List<Record> records) {
		Map<Integer,Guard> guards = new HashMap<>();
		Guard guard = null;
		for(Record record:records) {
			if(record.guardId!=null) {
				guards.getOrDefault(key, defaultValue)
			}
		}
	}

}

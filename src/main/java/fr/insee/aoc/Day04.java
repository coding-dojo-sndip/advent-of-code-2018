package fr.insee.aoc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fr.insee.aoc.Days.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

public class Day04 implements Day {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final Pattern regexBeginsShift = Pattern.compile("\\[(.+)] Guard #(\\d+) begins shift");
    private static final Pattern regexWakesUp = Pattern.compile("\\[(.+)] wakes up");
    private static final Pattern regexFallsAsleep = Pattern.compile("\\[(.+)] falls asleep");

    @Override
    public String part1(String input) {
        records(input).forEach(System.out::println);
        return "-1"
;    }

    private List<Record> records(String input) {
        return streamOfLines(input)
                .map(Record::fromLine)
                .sorted()
                .collect(toList());
    }

    static class Record implements Comparable<Record> {

        enum Type { BEGINS_SHIFT, WAKES_UP, FALLS_ASLEEP}

        private static final Comparator<Record> comparator = Comparator.comparing(r -> r.time);

        private LocalDateTime time;
        private Type type;
        private int guardId;

        static Record fromLine(String line) {
            Record record = new Record();
            Matcher matcherBeginsShift = regexBeginsShift.matcher(line);
            Matcher matcherWakesUp = regexWakesUp.matcher(line);
            Matcher matcherFallsAsleep = regexFallsAsleep.matcher(line);
            if(matcherBeginsShift.matches()) {
                record.type = Type.BEGINS_SHIFT;
                record.time = LocalDateTime.parse(matcherBeginsShift.group(1), formatter);
                record.guardId = groupInt(2, matcherBeginsShift);
            }
            else if(matcherWakesUp.matches()) {
                record.type = Type.WAKES_UP;
                record.time = LocalDateTime.parse(matcherWakesUp.group(1), formatter);
            }
            else if(matcherFallsAsleep.matches()) {
                record.type = Type.FALLS_ASLEEP;
                record.time = LocalDateTime.parse(matcherFallsAsleep.group(1), formatter);
            }
            return record;
        }

        @Override
        public int compareTo(Record other) {
            return comparator.compare(this, other);
        }

        @Override
        public String toString() {
            return formatter.format(time) + " " + type + (guardId != 0 ? " " + guardId : "");
        }
    }
}

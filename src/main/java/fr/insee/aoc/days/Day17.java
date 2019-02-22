package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;
import static fr.insee.aoc.utils.Collectors.*;
import static java.util.stream.Collectors.*;

import java.util.Arrays;

import static java.util.Comparator.*;
import static java.util.function.Function.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

public class Day17 implements Day {

	private static final Pattern pattern = Pattern.compile("(?:x=(\\d+), y=(\\d+)\\.\\.(\\d+))|(?:y=(\\d+), x=(\\d+)\\.\\.(\\d+))");
	
	@Override
	public String part1(String input, Object... params) {
		List<Point> points = streamOfLines(input)
			.map(Segment::fromLine)
			.flatMap(segment -> segment.points().stream())
			.collect(toList());
		Frame frame = Frame.frameWithBorderContaining(points, 1);
		int height = frame.height();
		int width = frame.width();
		char[][] grid = new char[height][width];
		Arrays.stream(grid).forEach(line -> Arrays.fill(line, '.'));
		for(Point point : points) {
			grid[point.getY() - frame.top][point.getX() - frame.left] = '#';
		}
		Arrays.stream(grid).forEach(System.out::println);
		return null;
	}
	

	
	static class Segment {
		Point start, end;

		private Segment(Point start, Point end) {
			this.start = start;
			this.end = end;
		}
		
		private static Segment fromLine(String line) {
			Matcher matcher = pattern.matcher(line);
			if(matcher.matches()) {
				if(line.startsWith("x")) {
					int x = readInt(1, matcher);
					return new Segment(Point.of(x, readInt(2, matcher)), Point.of(x, readInt(3, matcher)));
				}
				int y = readInt(4, matcher);
				return new Segment(Point.of(readInt(5, matcher), y), Point.of(readInt(6, matcher), y));
			}
			return null;
		}
		
		List<Point> points() {
			if(start.getX() == end.getX()) {
				return IntStream.rangeClosed(start.getY(), end.getY()).mapToObj(y -> Point.of(start.getX(), y)).collect(toList());
			}
			return IntStream.rangeClosed(start.getX(), end.getX()).mapToObj(x -> Point.of(x, start.getY())).collect(toList());
		}
	}
}

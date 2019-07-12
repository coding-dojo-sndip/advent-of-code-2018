package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Collectors.*;
import static fr.insee.aoc.utils.Frame.*;
import static java.util.function.Function.*;

import java.util.*;
import java.util.Map.*;
import java.util.regex.*;

import static fr.insee.aoc.utils.Days.*;

import static java.util.stream.Collectors.*;

import fr.insee.aoc.utils.*;

public class Day06 implements Day {

	private static Pattern pattern = Pattern.compile("(\\d+), (\\d+)");

	@Override
	public String part1(String input, Object... params) {
		List<Point> points = streamOfLines(input).map(Day06::lineToPoint).collect(toList());
		Frame frame = smallestFrameContaining(points);
		Map<Point, Integer> areas = points.stream().collect(toMap(identity(), p -> 0));
		for (int i = frame.getLeft(); i <= frame.getRight(); i++) {
			for (int j = frame.getTop(); j <= frame.getBottom(); j++) {
				final Point point = Point.of(i, j);
				List<Point> listOfMin = points.stream()
						.collect(listOfMin(Comparator.<Point>comparingInt(p -> p.manhattan(point))));
				if (listOfMin.size() == 1) {
					Point pointClosest = listOfMin.get(0);
					if (frame.isOnTheEdge(point)) {
						areas.remove(pointClosest);
					}
					areas.computeIfPresent(pointClosest, (k, v) -> v + 1);
				}
			}
		}
		int largestArea = areas.entrySet().stream().mapToInt(Entry::getValue).max().orElseThrow(DayException::new);
		return String.valueOf(largestArea);
	}

	@Override
	public String part2(String input, Object... params) {
		int seuil = (int) params[0];
		int total = 0;
		List<Point> points = streamOfLines(input).map(Day06::lineToPoint).collect(toList());
		Frame frame = smallestFrameContaining(points);
		for (int i = frame.getLeft(); i <= frame.getRight(); i++) {
			for (int j = frame.getTop(); j <= frame.getBottom(); j++) {
				final Point point = Point.of(i, j);
				int distanceTotale = points.stream().mapToInt(p -> p.manhattan(point)).sum();
				if (distanceTotale < seuil) total++;
			}
		}
		return String.valueOf(total);
	}

	
	private static Point lineToPoint(String coords) {
		Matcher matcher = pattern.matcher(coords);
		matcher.matches();
		return Point.of(readInt(1, matcher), readInt(2, matcher));
	}
}

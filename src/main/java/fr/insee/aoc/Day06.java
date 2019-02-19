package fr.insee.aoc;

import static fr.insee.aoc.Days.*;
import static fr.insee.aoc.Days.DaysCollector.*;
import static java.util.stream.Collectors.*;

import java.util.Collection;

import static java.util.Comparator.*;
import static java.util.function.Function.*;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.Days.Point;

public class Day06 implements Day {

	private static final Pattern pattern = Pattern.compile("(\\d+), (\\d+)");
	
	@Override
	public String part1(String input, Object... params) {
		List<Point> points = points(input);
		Map<Point, Integer> counts = points.stream().collect(toMap(identity(), p -> 0));
		Frame frame = Frame.smallestFrameContaining(points);
		for(int i = 0; i < frame.height(); i ++) {
			for(int j = 0; j < frame.width(); j ++) {
				Point location = Point.of(frame.left + j, frame.top + i);
				List<Point> closestPoints = points.stream().collect(listOfMin(comparingInt(point -> point.manhattan(location))));
				if(closestPoints.size() == 1) {
					Point closestPoint = closestPoints.get(0);
					if(frame.isOnTheEdge(closestPoint)) {
						counts.remove(closestPoint);
					}
					else {
						counts.computeIfPresent(closestPoint, (point, count) -> count + 1);
					}
				}
			}
		}
		int largestArea = counts.values().stream().mapToInt(Integer::intValue).max().orElse(-1);
		return String.valueOf(largestArea);
	}
	
	@Override
	public String part2(String input, Object... params) {
		int threshold = (int) params[0];
		int area = 0;
		List<Point> points = points(input);
		Frame frame = Frame.smallestFrameContaining(points);
		for(int i = 0; i < frame.height(); i ++) {
			for(int j = 0; j < frame.width(); j ++) {
				Point location = Point.of(frame.left + j, frame.top + i);
				int totalDistance = points.stream().mapToInt(point -> point.manhattan(location)).sum();
				if(totalDistance < threshold) area ++;
			}
		}
		return String.valueOf(area);
	}

	private List<Point> points(String input) {
		return streamOfLines(input).map(Day06::pointFromLine).collect(toList());
	}
	
	private static Point pointFromLine(String line) {
		Matcher matcher = pattern.matcher(line);
		return matcher.matches() ? Point.of(readInt(1, matcher), readInt(2, matcher)) : null;
	}
	
	static class Frame {
		int top, left, bottom, right;
		
		static Frame smallestFrameContaining(Collection<Point> points) {
			Frame frame = new Frame();
			IntSummaryStatistics statX = points.stream().collect(summarizingInt(Point::getX));
			IntSummaryStatistics statY = points.stream().collect(summarizingInt(Point::getY));
			frame.left = statX.getMin();
			frame.right = statX.getMax();
			frame.top = statY.getMin();
			frame.bottom = statY.getMax();
			return frame;
			
		}
		
		int width() {
			return right - left;
		}
		
		int height() {
			return bottom - top;
		}
		
		boolean isOnTheEdge(Point point) {
			return point.getX() == left || point.getX() == right || point.getY() == top || point.getY() == bottom;
		}
	}
}

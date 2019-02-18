package fr.insee.aoc;

import static fr.insee.aoc.Days.*;
import static fr.insee.aoc.Days.DaysCollector.*;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.Days.Point;

public class Day06 implements Day {

	private static final Pattern pattern = Pattern.compile("(\\d+), (\\d+)");
	
	@Override
	public String part1(String input, Object... params) {
		List<Point> points = points(input);
		Point[][] grid = fillGrid(points);
		return "-1";
	}

	private Point[][] fillGrid(List<Point> points) {
		Frame frame = Frame.smallestFrameContaining(points);
		Point[][] grid = new Point[frame.width()][frame.height()];
		for(int i = 0; i < frame.height(); i ++) {
			for(int j = 0; j < frame.width(); j ++) {
				Point location = Point.of(frame.left + j, frame.top + i);
				List<Point> nearestPoints = points.stream().collect(listOfMin(comparingInt(point -> manhattan(point, location))));
				grid[i][j] = nearestPoints.size() == 1 ? nearestPoints.get(0) : null;
			}
		}
		return grid;
	}
	
	private List<Point> points(String input) {
		return streamOfLines(input).map(Day06::pointFromLine).collect(toList());
	}
	
	private static Point pointFromLine(String line) {
		Matcher matcher = pattern.matcher(line);
		return matcher.matches() ? Point.of(readInt(1, matcher), readInt(2, matcher)) : null;
	}
	
	private static int manhattan(Point a, Point b) {
		return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
	}
	
	static class Frame {
		int top, left, bottom, right;
		
		static Frame smallestFrameContaining(List<Point> points) {
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
	}
}

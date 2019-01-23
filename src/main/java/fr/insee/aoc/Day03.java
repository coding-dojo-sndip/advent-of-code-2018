package fr.insee.aoc;

import static fr.insee.aoc.Days.*;
import static java.util.function.Function.*;
import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {

	
	
	@Override
	public String part1(String input) {
		List<Rectangle> rectangles = rectangles(input);
		for (int i = 0; i < rectangles.size(); i ++) {
			
		}
		return Day.super.part1(input);
	}

	@Override
	public String part2(String input) {
		// TODO Auto-generated method stub
		return Day.super.part2(input);
	}
	
	private List<Rectangle> rectangles(String input) {
		return streamOfLines(input).map(Rectangle::new).collect(toList());
	}

	static class Rectangle {
		
		private static final Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
		
		private int id, left, top, width, height;
		private Set<Point> points;
		
		public Rectangle(String line) {
			Matcher matcher = pattern.matcher(line);
			id = groupInt(1, matcher);
			left = groupInt(2, matcher);
			top = groupInt(3, matcher);
			width = groupInt(4, matcher);
			height = groupInt(5, matcher);
			points = points();
		}
		
		private Set<Point> points() {
			Set<Point> points = new HashSet<>();
			for (int j = 0; j < height; j ++) {
				for (int i = 0; i < width; i ++) {
					points.add(Point.of(left + i, top + j));
				}	
			}
			return points;
		}
		
		public Set<Point> commonPoints(Rectangle other) {
			Set<Point> intersection = new HashSet<>(this.points);
			intersection.retainAll(other.points);
			return intersection;
		}
		
		public boolean overlaps(Rectangle other) {
			return !commonPoints(other).isEmpty();
		}

		public int getId() {
			return id;
		}
	}
}

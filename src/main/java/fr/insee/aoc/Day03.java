package fr.insee.aoc;

import static fr.insee.aoc.Days.groupInt;
import static fr.insee.aoc.Days.streamOfLines;
import static java.lang.Math.min;
import static java.util.Collections.emptySet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.Days.Point;

public class Day03 implements Day {
	
	@Override
	public String part1(String input) {
		Rectangle[] rectangles = rectangles(input);
		Set<Point> intersection = new HashSet<>();
		for (int i = 0; i < rectangles.length; i ++) {
			Rectangle a = rectangles[i];
			for (int j = i + 1; j < rectangles.length; j ++) {
				Rectangle b = rectangles[j];
				intersection.addAll(a.commonPoints(b));
			}
		}
		return String.valueOf(intersection.size());
	}

	@Override
	public String part2(String input) {
		Rectangle[] rectangles = rectangles(input);
        for (Rectangle rectangle : rectangles) {
            if(rectangle.overlapsNone(rectangles)) return String.valueOf(rectangle.id);
        }
		throw new DayException("Erreur d'algorithme.");
	}
	
	private Rectangle[] rectangles(String input) {
		return streamOfLines(input).map(Rectangle::new).toArray(Rectangle[]::new);
	}
	
	static class Rectangle {
		
		private static final Pattern pattern = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");
		
		private int id;
		private Segment height, width;
		
		Rectangle(Segment height, Segment width) {
			super();
			this.height = height;
			this.width = width;
		}

		Rectangle(String line) {
			Matcher matcher = pattern.matcher(line);
			if(matcher.matches()) {
				id = groupInt(1, matcher);
				int left = groupInt(2, matcher);
				int top = groupInt(3, matcher);
				int width = groupInt(4, matcher);
				int height = groupInt(5, matcher);
				this.height = new Segment(top, height);
				this.width = new Segment(left, width);
			}
		}
		
		Optional<Rectangle> intersection(Rectangle other) {
			Optional<Segment> intersectionHeight = this.height.intersection(other.height);
			Optional<Segment> intersectionWidth = this.width.intersection(other.width);
			if(intersectionHeight.isPresent() && intersectionWidth.isPresent()) {
				return Optional.of(new Rectangle(intersectionHeight.get(), intersectionWidth.get()));
			}
			return Optional.empty();
		}
		
		private Set<Point> points() {
			Set<Point> points = new HashSet<>();
			for (int j = 0; j < height.length; j ++) {
				for (int i = 0; i < width.length; i ++) {
					points.add(Point.of(width.start + i, height.start + j));
				}	
			}
			return points;
		}
		
		Set<Point> commonPoints(Rectangle other) {
			return this.intersection(other)
				.map(Rectangle::points)
				.orElse(emptySet());
		}
		
		boolean overlaps(Rectangle other) {
			return this.id != other.id && !commonPoints(other).isEmpty();
		}

        boolean overlapsNone(Rectangle[] rectangles) {
            return Arrays.stream(rectangles).noneMatch(this::overlaps);
        }
	}
	
	static class Segment {
		
		private int start, length, end;
		
		Segment(int start, int length) {
			this.start = start;
			this.length = length;
			this.end = this.start + this.length - 1;
		}
		
		Optional<Segment> intersection(Segment other) {
			if(this.start <= other.start && other.start <= this.end) {
				return Optional.of(new Segment(other.start, min(this.end, other.end) + 1 - other.start));
			}
			else if(other.start <= this.start && this.start <= other.end) {
				return Optional.of(new Segment(this.start, min(this.end, other.end) + 1 - this.start));
			}
			return Optional.empty();
			
		}
	}
}

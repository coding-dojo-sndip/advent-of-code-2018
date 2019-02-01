package fr.insee.aoc;

import static fr.insee.aoc.Days.streamOfLines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03 implements Day {

	private static final Pattern regex = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

	@Override
	public String part1(String input) {
		SquareInch[][] fabric = new SquareInch[1000][1000];
		streamOfLines(input).forEach(r -> rectangle(r, fabric));
		long count = Arrays.stream(fabric)
				.flatMap(Arrays::stream)
				.filter(c -> c != null && c.counter > 1)
				.count();
		return String.valueOf(count);
	}

	@Override
	public String part2(String input) {
		SquareInch[][] fabric = new SquareInch[1000][1000];
		List<Rectangle> rectangles = streamOfLines(input)
				.map(r -> rectangle(r, fabric))
				.collect(Collectors.toList());
		return rectangles.stream()
				.filter(Rectangle::doesntOverlap)
				.findFirst()
				.map(Rectangle::getId)
				.orElseThrow(DayException::new);
	}

	private Rectangle rectangle(String input, SquareInch[][] fabric) {
		Matcher matcher = regex.matcher(input);
		Rectangle rectangle = new Rectangle();
		if (matcher.matches()) {
			int left = Integer.parseInt(matcher.group(2));
			int top = Integer.parseInt(matcher.group(3));
			int width = Integer.parseInt(matcher.group(4));
			int height = Integer.parseInt(matcher.group(5));
			rectangle.id = matcher.group(1);
			for (int i = left; i < left + width; i++) {
				for (int j = top; j < top + height; j++) {
					if(fabric[i][j] == null) fabric[i][j] = new SquareInch();
					fabric[i][j].counter ++;
					rectangle.points.add(fabric[i][j]);
				}
			}
		}
		return rectangle;
	}

	static class Rectangle {
		private String id;
		private List<SquareInch> points = new ArrayList<>();

		public boolean doesntOverlap() {
			return points.stream().allMatch(c -> c.counter == 1);
		}

		public String getId() {
			return id;
		}
	}

	static class SquareInch {
		int counter = 0;
	}
}

package fr.insee.aoc;

import static fr.insee.aoc.Days.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03 implements Day {

	private static final Pattern regex = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");

	@Override
	public String part1(String input) {
		AtomicInteger[][] fabric = new AtomicInteger[1000][1000];
		streamOfLines(input).forEach(r -> rectangle(r, fabric));
		long count = Arrays.stream(fabric)
				.flatMap(Arrays::stream)
				.filter(x -> x != null && x.get() > 1)
				.count();
		return String.valueOf(count);
	}

	@Override
	public String part2(String input) {
		AtomicInteger[][] tissu = new AtomicInteger[1000][1000];
		List<Rectangle> rectangles = streamOfLines(input)
				.map(r -> rectangle(r, tissu))
				.collect(Collectors.toList());
		return rectangles.stream()
				.filter(Rectangle::doesntOverlap)
				.findFirst()
				.map(Rectangle::getId)
				.orElseThrow(DayException::new);
	}

	private Rectangle rectangle(String input, AtomicInteger[][] tissu) {
		Matcher matcher = regex.matcher(input);
		Rectangle rectangle = new Rectangle();
		if (matcher.matches()) {
			int left = readInt(2, matcher);
			int top = readInt(3, matcher);
			int width = readInt(4, matcher);
			int height = readInt(5, matcher);
			rectangle.id = readString(1, matcher);
			for (int i = left; i < left + width; i++) {
				for (int j = top; j < top + height; j++) {
					if (tissu[i][j] == null) {
						tissu[i][j] = new AtomicInteger(1);
					} else {
						tissu[i][j].incrementAndGet();
					}
					rectangle.points.add(tissu[i][j]);
				}
			}
		}
		return rectangle;
	}

	static class Rectangle {
		private String id;
		private List<AtomicInteger> points = new ArrayList<>();

		public boolean doesntOverlap() {
			return points.stream().allMatch(point -> point.get() == 1);
		}

		public String getId() {
			return id;
		}
	}

}

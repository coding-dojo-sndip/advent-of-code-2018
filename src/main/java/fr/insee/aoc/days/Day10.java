package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Frame.*;

import java.util.*;
import java.util.regex.*;

import static fr.insee.aoc.utils.Days.*;

import static java.util.stream.Collectors.*;

import fr.insee.aoc.utils.*;

public class Day10 implements Day {

	@Override
	public String part1(String input, Object... params) {
		List<MobilePoint> points = streamOfLines(input).map(MobilePoint::new).collect(toList());
		while (!areAligned(points)) {
			points.forEach(MobilePoint::move);
		}
		return printMessage(points);
	}
	

	@Override
	public String part2(String input, Object... params) {
		List<MobilePoint> points = streamOfLines(input).map(MobilePoint::new).collect(toList());
		long elapsedTime = 0;
		while (!areAligned(points)) {
			elapsedTime ++;
			points.forEach(MobilePoint::move);
		}
		return String.valueOf(elapsedTime);
	}

	private boolean areAligned(List<MobilePoint> points) {
		return points
			.stream()
			.collect(groupingBy(MobilePoint::getX, toSet()))
			.values()
			.stream()
			.filter(list -> list.size() > 7)
			.anyMatch(this::areAdjacent);
	}

	private boolean areAdjacent(Set<MobilePoint> points) {
		IntSummaryStatistics stat = points.stream().mapToInt(Point::getY).summaryStatistics();
		return stat.getMax() - stat.getMin() == points.size() - 1;
	}

	private String printMessage(Collection<MobilePoint> points) {
		StringBuilder message = new StringBuilder();
		Frame frame = smallestFrameContaining(points);
		for (int y = frame.getTop(); y <= frame.getBottom(); y++) {
			for (int x = frame.getLeft(); x <= frame.getRight(); x++) {
				if (points.contains(Point.of(x, y))) {
					message.append('#');
				} else {
					message.append(' ');
				}
			}
			message.append(System.getProperty("line.separator"));
		}
		return message.toString();
	}

	static class MobilePoint extends Point {
		protected int vx;
		protected int vy;
		private static final Pattern pattern = Pattern.compile("position=< *(-?\\d+), *(-?\\d+)> velocity=< *(-?\\d+), *(-?\\d+)>");

		public MobilePoint(String line) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				this.x = readInt(1, matcher);
				this.y = readInt(2, matcher);
				this.vx = readInt(3, matcher);
				this.vy = readInt(4, matcher);
			}
		}

		public void move() {
			this.x += this.vx;
			this.y += this.vy;
		}

		public int getVx() {
			return vx;
		}

		public int getVy() {
			return vy;
		}
		

		@Override
		public boolean equals(Object object) {
			return super.equals(object);
		}

		@Override
		public int hashCode() {
			return super.hashCode();
		}
	}

}
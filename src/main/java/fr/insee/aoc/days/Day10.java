package fr.insee.aoc.days;

import static java.util.stream.Collectors.*;
import static fr.insee.aoc.utils.Days.*;
import static fr.insee.aoc.utils.Frame.*;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

public class Day10 implements Day {

	@Override
	public String part1(String input, Object... params) {
		List<MobilePoint> points = streamOfLines(input).map(MobilePoint::new).collect(toList());
		boolean aligned = false;
		while (!aligned) { // TODO à simplifier
			points.forEach(MobilePoint::move);
			aligned = areAligned(points);
		}
		String message = printMessage(points);
		return message;
	}

	private boolean areAligned(List<MobilePoint> points) {
		return points
			.stream()
			.collect(groupingBy(MobilePoint::getX)) // TODO Set
			.values()
			.stream()
			.filter(list -> list.size() > 7)
			.anyMatch(list -> areAdjacent(list));
	}

	private boolean areAdjacent(List<MobilePoint> points) {
		IntSummaryStatistics stat = points.stream().mapToInt(Point::getY).summaryStatistics();
		return stat.getMax() - stat.getMin() == 7; // TODO à rendre plus robuste
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
		protected int vx, vy;
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
	}

}
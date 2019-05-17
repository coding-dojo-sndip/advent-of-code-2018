package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readInt;
import static fr.insee.aoc.utils.Days.streamOfLines;
import static fr.insee.aoc.utils.Frame.smallestFrameContaining;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

public class Day10 implements Day {

	@Override
	public String part1(String input, Object... params) {
		List<MobilePoint> points = streamOfLines(input).map(MobilePoint::new).collect(toList());
		while (!areAligned(points)) {
			points.forEach(MobilePoint::move);
		}
		return readMessage(points);
	}

	private boolean areAligned(List<MobilePoint> points) {
		return points
			.stream()
			.collect(groupingBy(MobilePoint::getX, toSet()))
			.values()
			.stream()
			.filter(list -> list.size() > 7)
			.anyMatch(list -> areAdjacent(list));
	}

	private boolean areAdjacent(Set<MobilePoint> points) {
		IntSummaryStatistics stat = points.stream().mapToInt(Point::getY).summaryStatistics();
		return stat.getMax() - stat.getMin() == stat.getCount() - 1;
	}

	private String readMessage(Collection<MobilePoint> points) {
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
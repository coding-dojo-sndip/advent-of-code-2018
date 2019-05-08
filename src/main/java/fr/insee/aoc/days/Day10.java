package fr.insee.aoc.days;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.*;
import static fr.insee.aoc.utils.Days.*;

public class Day10 implements Day {

	@Override
	public String part1(String input, Object... params) {
        Collection<MovingPoint> points = points(input);
        while (!MovingPoint.areAligned(points)) {
            points.forEach(MovingPoint::move);
        }
        String message = readMessage(points);
        System.out.println(message);
        return message;
	}

    private static String readMessage(Collection<MovingPoint> points) {
	    StringBuilder builder = new StringBuilder();
        Collection<Point> positions = points.stream()
            .map(p -> Point.of(p.getX(), p.getY()))
            .collect(toList());
        Frame frame = Frame.smallestFrameContaining(positions);
        for (int y = frame.getTop(); y <= frame.getBottom(); y ++) {
            for (int x = frame.getLeft(); x <= frame.getRight(); x ++) {
                if(positions.contains(Point.of(x, y))) {
                    builder.append('#');
                }
                else {
                    builder.append(' ');
                }
            }
            builder.append(String.format("%n"));
        }
        return builder.toString();
    }

	private static Collection<MovingPoint> points(String input) {
	    return streamOfLines(input).map(MovingPoint::fromLine).collect(toList());
    }

    static class MovingPoint extends Point {

        int vX, vY;

        static final Pattern pattern = Pattern.compile("position=< *(-?\\d+), *(-?\\d+)> velocity=< *(-?\\d+), *(-?\\d+)>");

        private MovingPoint() {}

        static MovingPoint fromLine(String line) {
            MovingPoint point = new MovingPoint();
            Matcher matcher = pattern.matcher(line);
            if(matcher.matches()) {
                point.x = readInt(1, matcher);
                point.y = readInt(2, matcher);
                point.vX = readInt(3, matcher);
                point.vY = readInt(4, matcher);
            }
            return point;
        }

        private static boolean areAligned(Collection<MovingPoint> points) {
            return points.stream()
                .collect(groupingBy(Point::getX))
                .values()
                .stream()
                .filter(MovingPoint::areConnected)
                .count() >= 3;
        }

        private static boolean areConnected(Collection<MovingPoint> verticallyAlignedPoints) {
            if(verticallyAlignedPoints.size() != 8) return false;
            IntSummaryStatistics statistics = verticallyAlignedPoints.stream()
                .mapToInt(Point::getY)
                .summaryStatistics();
            int min = statistics.getMin();
            int max = statistics.getMax();
            return (max - min) == 7;
        }

        void move() {
            x += vX;
            y += vY;
        }
    }
}

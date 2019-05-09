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
		int letterHeight = (int) params[0];
        Collection<MovingPoint> points = points(input);
        while (!MovingPoint.areAligned(points, letterHeight)) {
            points.forEach(MovingPoint::move);
        }
        return readMessage(points);
	}
	
	@Override
	public String part2(String input, Object... params) {
		int letterHeight = (int) params[0];
		Collection<MovingPoint> points = points(input);
		int elapsed = 0;
		while (!MovingPoint.areAligned(points, letterHeight)) {
			elapsed ++;
			points.forEach(MovingPoint::move);
		}
		return String.valueOf(elapsed);
	}

    private static String readMessage(Collection<MovingPoint> points) {
	    StringBuilder builder = new StringBuilder();
        Set<Point> positions = positions(points);
        Frame frame = Frame.smallestFrameContaining(positions);
        for (int y = frame.getTop(); y <= frame.getBottom(); y ++) {
            for (int x = frame.getLeft(); x <= frame.getRight(); x ++) {
            	builder.append(positions.contains(Point.of(x, y)) ? '#' : ' ');
            }
            builder.append(String.format("%n"));
        }
        return builder.toString();
    }

	private static Set<Point> positions(Collection<MovingPoint> points) {
		return points.stream()
            .map(p -> Point.of(p.getX(), p.getY()))
            .collect(toSet());
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

        private static boolean areAligned(Collection<MovingPoint> points, int letterHeight) {
        	Frame frame = Frame.smallestFrameContaining(points);
            return frame.height() <= letterHeight;
        }

        void move() {
            x += vX;
            y += vY;
        }
    }
}

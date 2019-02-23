package fr.insee.aoc.days;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static fr.insee.aoc.utils.Days.readInt;
import static fr.insee.aoc.utils.Days.streamOfLines;
import static java.util.stream.Collectors.toList;

public class Day17 implements Day {

    private static final Pattern pattern = Pattern.compile("(?:x=(\\d+), y=(\\d+)\\.\\.(\\d+))|(?:y=(\\d+), x=(\\d+)\\.\\.(\\d+))");

    @Override
    public String part1(String input, Object... params) {
        List<Point> points = streamOfLines(input)
                .map(Segment::fromLine)
                .flatMap(segment -> segment.points().stream())
                .collect(toList());
        Frame frame = Frame.enclosingWithBorder(points, 1);
        char[][] grid = grid(frame, points);
        Water spring = new Water(null, Point.of(500 - frame.left, 0));
        setValue(spring.position, grid, '+');
        flow(spring, grid);
        Arrays.stream(grid).forEach(System.out::println);
        return null;
    }

    private char[][] grid(Frame frame, Collection<Point> points) {
        int height = frame.height() + 1;
        int width = frame.width() + 1;
        char[][] grid = new char[height][width];
        Arrays.stream(grid).forEach(line -> Arrays.fill(line, '.'));
        for(Point point : points) {
            grid[point.getY() - frame.top][point.getX() - frame.left] = '#';
        }
        return grid;
    }

    private static char getValue(Point point, char[][] grid) {
        return grid[point.y][point.x];
    }

    private static void setValue(Point point, char[][] grid, char value) {
        grid[point.y][point.x] = value;
    }

    private static void flow(Water water, char[][] grid) {
        // Arrays.stream(grid).forEach(System.out::println);
        Point downPoint = water.position.downPoint();
        if(downPoint.y >= grid.length - 1) return;
        boolean canFlowDown = getValue(downPoint, grid) == '.';
        if(canFlowDown) {
            setValue(downPoint, grid, '|');
            flow(new Water(water, downPoint), grid);
        }
        else {
            Point leftPoint = water.position.leftPoint();
            Point rightPoint = water.position.rightPoint();
            boolean canFlowLeft = getValue(leftPoint, grid) == '.';
            boolean canFlowRight = getValue(rightPoint, grid) == '.';
            boolean cantFlow = !canFlowLeft && !canFlowRight;

            if(cantFlow){
                cantFlow(water, grid);
            }
            else {
                if(canFlowLeft) {
                    setValue(leftPoint, grid, '|');
                    flow(new Water(water.source, leftPoint), grid);
                }
                if(canFlowRight){
                    setValue(rightPoint, grid, '|');
                    flow(new Water(water.source, rightPoint), grid);
                }
            }
        }
    }

    private static void cantFlow(Water water, char[][] grid) {
        Point point = null;

        point = Point.of(water.position.x + 1, water.position.y);
        while (getValue(point, grid) == '|') {
            point = Point.of(point.x + 1, point.y);
        }
        boolean rightOk = (getValue(point, grid) == '#');

        point = Point.of(water.position.x - 1, water.position.y);
        while (getValue(point, grid) == '|') {
            point = Point.of(point.x + 1, point.y);
        }
        boolean leftOk = (getValue(point, grid) == '#');

        if(rightOk && leftOk) {
            setValue(water.position, grid, '~');
            point = Point.of(water.position.x + 1, water.position.y);
            while (getValue(point, grid) == '|') {
                setValue(point, grid, '~');
                point = Point.of(point.x + 1, point.y);
            }
            point = Point.of(water.position.x - 1, water.position.y);
            while (getValue(point, grid) == '|') {
                setValue(point, grid, '~');
                point = Point.of(point.x - 1, point.y);
            }
            flow(water.source, grid);
        }
    }

    static class Water {

        Water source;
        Point position;

        Water(Water source, Point position) {
            this.source = source;
            this.position = position;
        }
    }

    static class Segment {
        Point start, end;

        private Segment(Point start, Point end) {
            this.start = start;
            this.end = end;
        }

        private static Segment fromLine(String line) {
            Matcher matcher = pattern.matcher(line);
            if(matcher.matches()) {
                if(line.startsWith("x")) {
                    int x = readInt(1, matcher);
                    return new Segment(Point.of(x, readInt(2, matcher)), Point.of(x, readInt(3, matcher)));
                }
                int y = readInt(4, matcher);
                return new Segment(Point.of(readInt(5, matcher), y), Point.of(readInt(6, matcher), y));
            }
            return null;
        }

        List<Point> points() {
            if(start.x == end.x) {
                return IntStream.rangeClosed(start.y, end.y).mapToObj(y -> Point.of(start.x, y)).collect(toList());
            }
            return IntStream.rangeClosed(start.x, end.x).mapToObj(x -> Point.of(x, start.y)).collect(toList());
        }
    }
}

package fr.insee.aoc.days;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static fr.insee.aoc.utils.Days.readInt;
import static fr.insee.aoc.utils.Days.streamOfCells;
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
        Character[][] grid = grid(frame, points);
        Water spring = new Water(null, Point.of(500 - frame.left, 0));
        Collection<Water> waters = flow(spring, grid);
        setValue(spring.position, grid, '+');
        for(int i = 0; i < 100_000; i ++){
        	Collection<Water> nextWaters = new HashSet<>();
        	waters.forEach(water -> nextWaters.addAll(flow(water, grid)));
        	waters = nextWaters;
        }
        printGrid(grid);
        long count = streamOfCells(grid).filter(c -> c == '|' || c == '~').count();
        return String.valueOf(count);
    }

    private static void printGrid(Character[][] grid) {
        Arrays.stream(grid).forEach(line -> {
            Arrays.stream(line).forEach(System.out::print);
            System.out.println();
        });
    }

    private static Character[][] grid(Frame frame, Collection<Point> points) {
        int height = frame.height() + 1;
        int width = frame.width() + 1;
        Character[][] grid = new Character[height][width];
        Arrays.stream(grid).forEach(line -> Arrays.fill(line, '.'));
        for(Point point : points) {
            grid[point.getY() - frame.top][point.getX() - frame.left] = '#';
        }
        return grid;
    }

    private static char getValue(Point point, Character[][] grid) {
        return grid[point.y][point.x];
    }

    private static void setValue(Point point, Character[][] grid, char value) {
        grid[point.y][point.x] = value;
    }

    private static Collection<Water> flow(Water water, Character[][] grid) {
        // printGrid(grid);
        // System.out.println(water.position);
        Point downPoint = water.position.downPoint();
        boolean canFlowDown = getValue(downPoint, grid) == '.';
        if(canFlowDown) {
            setValue(downPoint, grid, '|');
            if(downPoint.y < grid.length - 2) return Collections.singleton(new Water(water, downPoint));
        }
        else {
            Point leftPoint = water.position.leftPoint();
            Point rightPoint = water.position.rightPoint();
            boolean canFlowLeft = leftPoint.x >= 0 && getValue(leftPoint, grid) == '.';
            boolean canFlowRight = rightPoint.x < grid[0].length && getValue(rightPoint, grid) == '.';
            boolean canNotFlow = !canFlowLeft && !canFlowRight;

            if(canNotFlow){
                return canNotFlow(water, grid).map(Collections::singleton).orElse(Collections.emptySet());
            }
            else {
            	Set<Water> waters = new HashSet<>();
                if(canFlowRight){
                    setValue(rightPoint, grid, '|');
                    waters.add(new Water(water.source, rightPoint));
                }
                if(canFlowLeft) {
                    setValue(leftPoint, grid, '|');
                    waters.add(new Water(water.source, leftPoint));
                }
                return waters;
            }
        }
        return Collections.emptySet();
    }

    private static Optional<Water> canNotFlow(Water water, Character[][] grid) {
        Point point = null;

        point = water.position;
        while (point.x < grid[0].length && getValue(point, grid) == '|') {
            point = Point.of(point.x + 1, point.y);
        }
        boolean clayRight = (point.x < grid[0].length && getValue(point, grid) == '#');

        point = water.position;
        while (point.x >= 0 && getValue(point, grid) == '|') {
            point = Point.of(point.x - 1, point.y);
        }
        boolean clayLeft = (point.x >= 0 && getValue(point, grid) == '#');

        if(clayRight && clayLeft) {
            setValue(water.position, grid, '~');
            point = Point.of(water.position.x + 1, water.position.y);
            while (point.x < grid[0].length && getValue(point, grid) == '|') {
                setValue(point, grid, '~');
                point = Point.of(point.x + 1, point.y);
            }
            point = Point.of(water.position.x - 1, water.position.y);
            while (point.x >= 0 && getValue(point, grid) == '|') {
                setValue(point, grid, '~');
                point = Point.of(point.x - 1, point.y);
            }
            return Optional.of(water.source);
        }
        return Optional.empty();
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

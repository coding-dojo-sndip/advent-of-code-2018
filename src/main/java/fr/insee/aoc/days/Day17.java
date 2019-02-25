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
        Character[][] grid = buildGridAndFlow(input);
        long count = streamOfCells(grid).filter(c -> c == '|' || c == '~').count();
        return String.valueOf(count);
    }
    
    @Override
    public String part2(String input, Object... params) {
    	Character[][] grid = buildGridAndFlow(input);
    	long count = streamOfCells(grid).filter(c -> c == '~').count();
    	return String.valueOf(count);
    }

	private Character[][] buildGridAndFlow(String input) {
		List<Point> points = streamOfLines(input)
            .map(Segment::fromLine)
            .flatMap(segment -> segment.points().stream())
            .collect(toList());
        Frame frame = Frame.enclosingWithBorder(points, 1);
        Character[][] grid = grid(frame, points);
        Water spring = Water.at(Point.of(500 - frame.left, 0));
        flowFromSpring(spring, grid);
		return grid;
	}

	private static void flowFromSpring(Water spring, Character[][] grid) {
		Collection<Water> waters = flow(spring, grid);
        while(!waters.isEmpty()){
        	waters = waters.stream()
    			.flatMap(water -> flow(water, grid).stream())
    			.collect(toList());
        }
	}

    private static Collection<Water> flow(Water water, Character[][] grid) {
    	Point downPoint = water.downPoint();
    	if(getValue(downPoint, grid) == '|') return Collections.emptySet();
        if(water.canFlowDown(grid)) {
            setValue(downPoint, grid, '|');
            if(downPoint.y < grid.length - 2) return Collections.singleton(Water.at(downPoint));
        }
        else {
            boolean canNotFlow = !water.canFlowLeft(grid) && !water.canFlowRight(grid);
            if(canNotFlow){
                return canNotFlow(water, grid);
            }
            else {
            	Set<Water> waters = new HashSet<>();
                if(water.canFlowRight(grid)){
                	Point rightPoint = water.rightPoint();
                    setValue(rightPoint, grid, '|');
                    waters.add(Water.at(rightPoint));
                }
                if(water.canFlowLeft(grid)) {
                	Point leftPoint = water.leftPoint();
                    setValue(leftPoint, grid, '|');
                    waters.add(Water.at(leftPoint));
                }
                return waters;
            }
        }
        return Collections.emptySet();
    }

    private static Collection<Water> canNotFlow(Water water, Character[][] grid) {
        Point point = null;

        point = water;
        while (getValue(point, grid) == '|') {
            point = Point.of(point.x + 1, point.y);
        }
        boolean clayRight = getValue(point, grid) == '#';

        point = water;
        while (getValue(point, grid) == '|') {
            point = Point.of(point.x - 1, point.y);
        }
        boolean clayLeft = getValue(point, grid) == '#';

        if(clayRight && clayLeft) {
        	Set<Water> waters = new HashSet<>();
            setValue(water, grid, '~');
            Point upPoint = water.upPoint();
            if(getValue(upPoint, grid) == '|') waters.add(Water.at(upPoint));
            point = Point.of(water.x + 1, water.y);
            while (getValue(point, grid) == '|') {
                setValue(point, grid, '~');
                upPoint = point.upPoint();
				if(getValue(upPoint, grid) == '|') waters.add(Water.at(upPoint));
                point = Point.of(point.x + 1, point.y);
            }
            point = Point.of(water.x - 1, water.y);
            while (getValue(point, grid) == '|') {
                setValue(point, grid, '~');
                upPoint = point.upPoint();
                if(getValue(upPoint, grid) == '|') waters.add(Water.at(upPoint));
                point = Point.of(point.x - 1, point.y);
            }
            return waters;
        }
        return Collections.emptySet();
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
    	if(point.x < 0 || point.x >= grid[0].length) return ' ';
        return grid[point.y][point.x];
    }

    private static void setValue(Point point, Character[][] grid, char value) {
        grid[point.y][point.x] = value;
    }
    
    static class Water extends Point {

    	public static Water at(Point point) {
    		Water water = new Water();
    		water.x = point.x;
    		water.y = point.y;
    		return water;
    	}
    	
		boolean canFlowDown(Character[][] grid) {
        	return getValue(downPoint(), grid) == '.';
        }
        
        boolean canFlowLeft(Character[][] grid) {
        	return getValue(leftPoint(), grid) == '.';
        }
        
        boolean canFlowRight(Character[][] grid) {
        	return getValue(rightPoint(), grid) == '.';
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
    
    /*
    private static void printGrid(Character[][] grid) {
        Arrays.stream(grid).forEach(line -> {
            Arrays.stream(line).forEach(System.out::print);
            System.out.println();
        });
    }
     */
}

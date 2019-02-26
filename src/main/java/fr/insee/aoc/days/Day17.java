package fr.insee.aoc.days;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static fr.insee.aoc.utils.Days.*;
import static java.util.stream.Collectors.*;
import static java.util.Collections.*;

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
        flow(spring, grid);
		return grid;
	}

    private static void flow(Water water, Character[][] grid) {
    	if(water.shouldFlow(grid)) {
    		if(water.canFlowDown(grid)) {
    			flow(water.flowDown(grid), grid);
    		}
    		else {
    			Collection<Water> sidewayWater = water.flowSideways(grid);
    			if(sidewayWater.isEmpty()) {
    				canNotFlowSideways(water, grid).forEach(w -> flow(w, grid));
    			}
    			else {
    				sidewayWater.forEach(w -> flow(w, grid));
    			}
    		}
    	}
    }

    private static Collection<Water> canNotFlowSideways(Water water, Character[][] grid) {
        return water.stuckByClay(grid) ? water.flowOver(grid) : emptySet();
    }

    private static char getValue(Point point, Character[][] grid) {
    	if(point.x < 0 || point.x >= grid[0].length) return ' ';
        return grid[point.y][point.x];
    }

    private static void setValue(Point point, Character[][] grid, char value) {
        grid[point.y][point.x] = value;
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
    
    static class Water extends Point {

    	private Point leftPoint, rightPoint, downPoint;
    	
    	public static Water at(Point point) {
    		Water water = new Water();
    		water.x = point.x;
    		water.y = point.y;
    		water.leftPoint = point.leftPoint();
    		water.rightPoint = point.rightPoint();
    		water.downPoint = point.downPoint();
    		return water;
    	}
    	
		boolean canFlowDown(Character[][] grid) {
        	return getValue(downPoint, grid) == '.';
        }
        
		boolean shouldFlow(Character[][] grid) {
			return getValue(downPoint, grid) != '|' && downPoint.y <= grid.length - 2;
		}
		
		boolean stuckByClay(Character[][] grid) {
			return stuckByClayOnLeft(grid) && stuckByClayOnRight(grid);
		}
		
        Water flowDown(Character[][] grid) {
			setValue(downPoint, grid, '|');
            return Water.at(downPoint);
        }
        
        Collection<Water> flowSideways(Character[][] grid) {
        	Set<Water> waters = new HashSet<>();
            if(getValue(rightPoint, grid) == '.'){
                setValue(rightPoint, grid, '|');
                waters.add(Water.at(rightPoint));
            }
            if(getValue(leftPoint, grid) == '.') {
                setValue(leftPoint, grid, '|');
                waters.add(Water.at(leftPoint));
            }
            return waters;
        }
        
        Collection<Water> flowOver(Character[][] grid) {
        	Set<Water> waters = new HashSet<>();
        	Point point = Water.at(this);
            while (getValue(point, grid) == '|') {
                setValue(point, grid, '~');
				if(getValue(point.upPoint(), grid) == '|') waters.add(Water.at(point.upPoint()));
                point = Point.of(point.x + 1, point.y);
            }
            point = Point.of(this.x - 1, this.y);
            while (getValue(point, grid) == '|') {
                setValue(point, grid, '~');
                if(getValue(point.upPoint(), grid) == '|') waters.add(Water.at(point.upPoint()));
                point = Point.of(point.x - 1, point.y);
            }
            return waters;
        }
        
		private boolean stuckByClayOnRight(Character[][] grid) {
			Point point = Water.at(this);
	        while (getValue(point, grid) == '|') {
	            point = Point.of(point.x + 1, point.y);
	        }
	       return getValue(point, grid) == '#';
		}
		
		private boolean stuckByClayOnLeft(Character[][] grid) {
			Point point = Water.at(this);
			while (getValue(point, grid) == '|') {
				point = Point.of(point.x - 1, point.y);
			}
			return getValue(point, grid) == '#';
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

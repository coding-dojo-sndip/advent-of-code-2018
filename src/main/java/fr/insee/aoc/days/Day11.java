package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;
import static java.util.stream.IntStream.*;

import static java.util.Comparator.*;

import fr.insee.aoc.utils.DayException;
import fr.insee.aoc.utils.Point;

public class Day11 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		int[][] grid = grid(gridSerial);
		PowerPoint point = optimalPowerPoint(3, 3, grid);
		return String.format("%d,%d", point.getX(), point.getY());
	}
	
	@Override
	public String part2(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		int[][] grid = grid(gridSerial);
		PowerPoint point = optimalPowerPoint(1, 300, grid);
		return String.format("%d,%d,%d", point.getX(), point.getY(), point.getSize());
	}
	
	private PowerPoint optimalPowerPoint(int minSize, int maxSize, int[][] grid) {
		return rangeClosed(minSize, maxSize)
			.parallel()
			.mapToObj(size -> largestTotalPower(size, grid))
			.max(comparingInt(PowerPoint::getTotalPower))
			.orElseThrow(DayException::new);
	}
	
	private int[][] grid(int gridSerial) {
		int[][] grid = new int[300][300];
		range(0, 300).forEach(x -> {
			range(0, 300).forEach(y -> {
				grid[x][y] = powerLevel(x + 1, y + 1, gridSerial);
			});
		});
		return summedAreaGrid(grid);
	}
	
	private int[][] summedAreaGrid(int[][] grid) {
		int width = grid.length;
		int height = grid[0].length;
		int[][] summedAreaGrid = new int [width][height];
		int[][] partialSum = new int [width][height];
		for(int x = 0; x < width; x ++) {
			for(int y = 0; y < height; y ++) {
				partialSum[x][y] = grid[x][y] + (y == 0 ? 0 : partialSum[x][y - 1]);
				summedAreaGrid[x][y] = partialSum[x][y] + (x == 0 ? 0 : summedAreaGrid[x - 1][y]);
			}	
		}
		return summedAreaGrid;
	}
	
	private int powerLevel(int x, int y, int gridSerial) {
		int rackId = x + 10;
		return (((rackId * y + gridSerial) * rackId / 100) % 10) - 5;
	}
	
	private int totalPower(int x, int y, int size, int[][] grid) {
		int a = (x == 0 || y == 0) ? 0 : grid[x - 1][y - 1];
		int b = x == 0 ? 0 : grid[x - 1][y - 1 + size];
		int c = y == 0 ? 0 : grid[x - 1 + size][y - 1];
		int d = grid[x - 1 + size][y - 1 + size];
		return a + d - c - b;
	}
	
	private PowerPoint largestTotalPower(int size, int[][] grid) {
		PowerPoint point = new PowerPoint();
		for(int x = 0; x < 300 - size; x ++) {
			for(int y = 0; y < 300 - size; y ++) {
				int totalPower = totalPower(x, y, size, grid);
				if(totalPower > point.totalPower) {
					point = PowerPoint.of(x + 1, y + 1, size, totalPower);
				}
			}
		}
		return point;
	}
	
	static class PowerPoint extends Point {
		private int size;
		private int totalPower = Integer.MIN_VALUE;

		private PowerPoint() {
			super();
		}

		static PowerPoint of(int x, int y, int size, int totalPower) {
			PowerPoint point = new PowerPoint();
			point.x = x;
			point.y = y;
			point.size = size;
			point.totalPower = totalPower;
			return point;
		}

		public int getSize() {
			return size;
		}

		public int getTotalPower() {
			return totalPower;
		}
	}
}
package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readLine;
import static java.util.stream.IntStream.range;

import fr.insee.aoc.utils.Point;

public class Day11 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		int[][] grid = grid(gridSerial);
		int largestTotalPower = Integer.MIN_VALUE;
		Point point = null;
		for(int x = 0; x < 297; x ++) {
			for(int y = 0; y < 297; y ++) {
				int totalPower = totalPower(x, y, grid);
				if(totalPower > largestTotalPower) {
					point = Point.of(x + 1, y + 1);
					largestTotalPower = totalPower;
				}
			}
		}
		return String.format("%d,%d", point.getX(), point.getY());
	}

	private int[][] grid(int gridSerial) {
		int[][] grid = new int[300][300];
		range(0, 300).forEach(x -> {
			range(0, 300).forEach(y -> {
				grid[x][y] = powerLevel(x + 1, y + 1, gridSerial);
			});
		});
		return grid;
	}
	
	private int powerLevel(int x, int y, int gridSerial) {
		int rackId = x + 10;
		return (((rackId * y + gridSerial) * rackId / 100) % 10) - 5;
	}
	
	private int totalPower(int x, int y, int[][] grid) {
		int totalPower = 0;
		for(int i = x; i < x + 3; i ++) {
			for(int j = y; j < y + 3; j ++) {
				totalPower += grid[i][j];
			}
		}
		return totalPower;
	}
}
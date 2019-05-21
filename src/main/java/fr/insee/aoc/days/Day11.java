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
				int totalPower = totalPower(x, y, 3, grid);
				if(totalPower > largestTotalPower) {
					point = Point.of(x + 1, y + 1);
					largestTotalPower = totalPower;
				}
			}
		}
		return String.format("%d,%d", point.getX(), point.getY());
	}
	
	@Override
	public String part2(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		int[][] grid = grid(gridSerial);
		int largestTotalPower = Integer.MIN_VALUE;
		PointSize point = null;
		for(int size = 1; size <= 300 - size; size ++) {
			for(int x = 0; x < 300 - size; x ++) {
				for(int y = 0; y < 300 - size; y ++) {
					int totalPower = totalPower(x, y, size, grid);
					if(totalPower > largestTotalPower) {
						point = PointSize.of(x + 1, y + 1, size);
						largestTotalPower = totalPower;
					}
				}
			}
		}
		return String.format("%d,%d,%d", point.getX(), point.getY(), point.getSize());
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
	
	private int totalPower(int x, int y, int size, int[][] grid) {
		int totalPower = 0;
		for(int i = x; i < x + size; i ++) {
			for(int j = y; j < y + size; j ++) {
				totalPower += grid[i][j];
			}
		}
		return totalPower;
	}
	
	static class PointSize extends Point {
		private int size;

		private PointSize() {
			super();
		}

		static PointSize of(int x, int y, int size) {
			PointSize point = new PointSize();
			point.x = x;
			point.y = y;
			point.size = size;
			return point;
		}

		public int getSize() {
			return size;
		}
	}
}
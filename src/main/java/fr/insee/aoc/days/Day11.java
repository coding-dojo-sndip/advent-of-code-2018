package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readLine;

import static java.util.Comparator.*;
import java.util.stream.IntStream;

import fr.insee.aoc.utils.DayException;
import fr.insee.aoc.utils.Point;

public class Day11 implements Day {

	private static final int GRID_SIZE = 300;
	
	@Override
	public String part1(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		Point point = pointWithMaxTotalPower(gridSerial, 3).point;
		return String.format("%d,%d", point.getX(), point.getY());
	}

	@Override
	public String part2(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		PointSizeValue pointSizeValue = IntStream.rangeClosed(1, GRID_SIZE)
			.parallel()
			.mapToObj(size -> pointWithMaxTotalPower(gridSerial, size))
			.max(comparingInt(point -> point.value))
			.orElseThrow(DayException::new);
		return pointSizeValue.toString();
	}
	
	static int powerLevel(int x, int y, int gridSerial) {
		int rackId = x + 10;
		return ((((rackId * y + gridSerial) * rackId) % 1000) / 100) - 5;
	}

	static int[][] createGrid(int gridSerial) {
		int[][] grid = new int[GRID_SIZE + 1][GRID_SIZE + 1];
		for (int x = 1; x <= GRID_SIZE; x++) {
			for (int y = 1; y <= GRID_SIZE; y++) {
				grid[x][y] = powerLevel(x, y, gridSerial);
			}
		}
		return grid;
	}
	
	static PointSizeValue pointWithMaxTotalPower(int gridSerial, int size) {
		int[][] grid = createGrid(gridSerial);
		int maxTotalPower = Integer.MIN_VALUE;
		Point point = null;

		for (int x = 1; x <= GRID_SIZE + 1 - size; x++) {
			for (int y = 1; y <= GRID_SIZE + 1 - size; y++) {
				int totalPower = 0;
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						totalPower = totalPower + grid[x + i][y + j];
					}
				}
				if (totalPower > maxTotalPower) {
					maxTotalPower = totalPower;
					point = Point.of(x, y);
				}
			}
		}
		return new PointSizeValue(point, size, maxTotalPower);
	}

	static class PointSizeValue {

		private Point point;
		private int size;
		private int value;

		public PointSizeValue(Point point, int size, int value) {
			super();
			this.point = point;
			this.size = size;
			this.value = value;
		}

		@Override
		public String toString() {
			return String.format("%d,%d,%d", point.getX(), point.getY(), size);
		}
	}
}
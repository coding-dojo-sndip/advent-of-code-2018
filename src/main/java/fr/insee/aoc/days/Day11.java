package fr.insee.aoc.days;

import static java.util.stream.Collectors.*;

import fr.insee.aoc.utils.Point;

import static fr.insee.aoc.utils.Days.*;
import static fr.insee.aoc.utils.Frame.*;

public class Day11 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		Point point = pointWithMaxTotalPower(gridSerial);
		return String.format("%d,%d", point.getX(), point.getY());
	}

	static int powerLevel(int x, int y, int gridSerial) {
		int rackId = x + 10;
		return ((((rackId * y + gridSerial) * rackId) % 1000) / 100) - 5;
	}

	static int[][] createGrid(int gridSerial) {
		int[][] grid = new int[301][301];
		for (int x = 1; x <= 300; x++) {
			for (int y = 1; y <= 300; y++) {
				grid[x][y] = powerLevel(x, y, gridSerial);
			}
		}
		return grid;
	}

	static Point pointWithMaxTotalPower(int gridSerial) {
		int[][] grid = createGrid(gridSerial);
		int maxTotalPower = Integer.MIN_VALUE;
		Point point = null;
		for (int x = 1; x <= 298; x++) {
			for (int y = 1; y <= 298; y++) {
				int totalPower = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						totalPower = totalPower + grid[x + i][y + j];
					}
				}
				if (totalPower > maxTotalPower) {
					maxTotalPower = totalPower;
					point = Point.of(x, y);
				}
			}
		}
		return point;
	}

}
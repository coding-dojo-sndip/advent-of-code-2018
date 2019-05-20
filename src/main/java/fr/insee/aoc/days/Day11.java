package fr.insee.aoc.days;

import static java.util.stream.Collectors.*;

import static java.util.stream.IntStream.*;

import fr.insee.aoc.utils.Point;

import static fr.insee.aoc.utils.Days.*;
import static fr.insee.aoc.utils.Frame.*;

public class Day11 implements Day {

	@Override
	public String part1(String input, Object... params) {
		int gridSerial = Integer.valueOf(readLine(input));
		int[][] grid = grid(gridSerial);
		return "0";
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
}
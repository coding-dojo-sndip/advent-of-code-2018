package fr.insee.aoc.utils;

import static fr.insee.aoc.utils.Days.streamOfLines;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

import java.util.List;

import org.junit.*;


public class PathFinderTest  {
    
	char[][] grid = streamOfLines("src/test/resources/path-finding.txt")
		.map(line -> line.replaceAll("S|G", "."))
		.map(String::toCharArray)
		.toArray(char[][]::new);
	
	@Test
	public void testPathFinding_simple() {
		var start = Point.of(1, 1);
		var goal = Point.of(5, 5);
		List<Point> path = PathFinder.shortestPath(start, goal, grid);
		assertThat(path).hasSize(9)
			.contains(start, atIndex(0))
			.contains(goal, atIndex(8));
	}
	
	
	@Test
	public void testPathFinding_long() {
		var start = Point.of(5, 15);
		var goal = Point.of(5, 17);
		List<Point> path = PathFinder.shortestPath(start, goal, grid);
		assertThat(path).hasSize(15)
			.contains(start, atIndex(0))
			.contains(goal, atIndex(14));
	}
	
	@Test
	public void testPathFinding_goal_blocked() {
		var start = Point.of(1, 7);
		var goal = Point.of(5, 11);
		List<Point> path = PathFinder.shortestPath(start, goal, grid);
		assertThat(path).isNullOrEmpty();
	}
	
	@Test
	public void testPathFinding_start_blocked() {
		var start = Point.of(5, 21);
		var goal = Point.of(5, 23);
		List<Point> path = PathFinder.shortestPath(start, goal, grid);
		assertThat(path).isNullOrEmpty();
	}
	
	@Test
	public void testPathFinding_start_touches_goal() {
		var start = Point.of(4, 26);
		var goal = Point.of(3, 26);
		List<Point> path = PathFinder.shortestPath(start, goal, grid);
		assertThat(path)
			.hasSize(2)
			.containsExactly(start, goal);
	}
	
	@Test
	public void testPathFinding_start_is_goal() {
		var start = Point.of(3, 35);
		var goal = Point.of(3, 35);
		List<Point> path = PathFinder.shortestPath(start, goal, grid);
		assertThat(path)
			.hasSize(1)
			.containsExactly(start);
	}
	
	
	@Test
	public void testPathFinding_huge() {
		char[][] grid = streamOfLines("src/test/resources/path-finding.txt").map(String::toCharArray).toArray(char[][]::new);
		var start = Point.of(24, 52);
		var goal = Point.of(8, 44);
		List<Point> path = PathFinder.shortestPath(start, goal, grid);
		assertThat(path).isNullOrEmpty();
	}
}

package fr.insee.aoc.utils;

import static java.util.stream.Collectors.toSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Stream;


public class PathFinder {

	public static List<Point> shortestPath(Point start, Point goal, char[][] grid) {
		var closed = new ArrayList<Node>();
		var open = new PriorityQueue<Node>();
		open.add(Node.from(start));
		while(!open.isEmpty()) {
			var currentNode = open.poll();
			if(currentNode.point.equals(goal)) {
				return currentNode.path();
			}
			var successors = currentNode.successors(grid);
			for (Node successor : successors) {
				successor.cost = currentNode.cost + 1;
				successor.heuristic = successor.cost + successor.point.manhattan(goal);
				if(Stream.concat(closed.stream(), open.stream()).noneMatch(node -> node.isBetterThan(successor))) {
					open.add(successor);
				}
			}
			closed.add(currentNode);
		}
		return null;
	}
	
	static class Node implements Comparable<Node> {
		int cost, heuristic;
		Node parent;
		Point point;
		
		static Node from(Point point) {
			var node = new Node();
			node.point = point;
			return node;
		}
		
		Set<Node> successors(char[][] grid) {
			return Set.of(point.onTop(), point.onBottom(), point.onLeft(), point.onRight())
				.stream()
				.filter(point -> grid[point.getY()][point.getX()] == '.')
				.map(Node::from)
				.peek(node -> node.parent = this)
				.collect(toSet());
		}
		
		List<Point> path() {
            var path = parent == null ? new ArrayList<Point>() : parent.path();
			path.add(this.point);
			return path;
		}
		
		boolean isBetterThan(Node other) {
			return this.equals(other) && this.compareTo(other) < 0;
		}
		
		@Override
		public int compareTo(Node other) {
			return this.heuristic - other.heuristic;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(point);
		}

		@Override
		public boolean equals(Object object) {
			if (object instanceof Node) {
				var other = (Node) object;
				return Objects.equals(this.point, other.point);
			}
			return false;
		}

		@Override
		public String toString() {
			return String.format("%s [%d]", point, heuristic + cost);
		}
	}
}

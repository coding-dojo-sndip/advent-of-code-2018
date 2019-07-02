package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

import fr.insee.aoc.utils.Point;

public class Day15 implements Day {

	@Override
	public String part1(String input, Object... params) {
		char[][] cave = cave(input);
		var units = units(cave);
		int round = 1;
		while (true) {
			units.sort(Unit.compareByPosition);
			for (Unit unit : units) {
				if (unit.isAlive()) {
					var enemies = unit.identifyTargets(units);
					if (enemies.isEmpty()) {
						var battleOutcome = round * units.stream().mapToInt(u -> u.hp).sum();
						return String.valueOf(battleOutcome);
					}
					List<Point> squaresInRange = unit.squaresInRange(cave, enemies);
					if(!squaresInRange.isEmpty()) {
						if(!squaresInRange.contains(unit.position)) {
							// Move before attack
							for(Point goal : squaresInRange) {
								List<Point> path = findPath(unit.position, goal, cave);
								System.out.println(unit.position + " => " + goal + " : " + path);
							}
							System.exit(0);
						}
						// Attack
					}
				}
			}
			round++;
		}
	}

	static char[][] cave(String input) {
		return streamOfLines(input).map(String::toCharArray).toArray(char[][]::new);
	}

	static List<Unit> units(char[][] cave) {
		List<Unit> units = new ArrayList<>();
		for (int i = 0; i < cave.length; i++) {
			for (int j = 0; j < cave[0].length; j++) {
				var type = cave[i][j];
				if (type == 'E' || type == 'G')
					units.add(Unit.create(type, i, j));
			}
		}
		return units;
	}

	static List<Point> findPath(Point start, Point goal, char[][] grid) {
		var open = new TreeSet<Node>();
		var closed = new TreeSet<Node>();
		open.add(Node.from(start));
		while(!open.isEmpty()) {
			var node = open.pollFirst();
			var successors = node.successors(grid);
			for (Node successor : successors) {
				successor.cost = node.cost + 1;
				successor.heuristic = successor.point.manhattan(goal);
				if(successor.point.equals(goal)) {
					closed.add(successor);
				}
				else {
                    if(Stream.concat(open.stream(), closed.stream()).noneMatch(aNode -> aNode.equals(successor) && aNode.compareTo(successor) < 0)) {
                        open.add(successor);
                    }
				}
			}
			closed.add(node);
		}
		return closed.stream()
			.filter(node -> node.point.equals(goal))
			.findFirst()
			.map(Node::path)
			.orElse(null);
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
		
		@Override
		public int compareTo(Node other) {
			return (this.cost + this.heuristic) - (other.cost + other.heuristic);
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
	}
	
	static class Unit implements Comparable<Unit> {

		Type type;
		int hp;
		Point position;

		private Unit() {
		}

		static Unit create(char type, int i, int j) {
			var unit = new Unit();
			unit.type = type == 'E' ? Type.ELF : Type.GOBELIN;
			unit.hp = 200;
			unit.position = Point.of(j, i);
			return unit;
		}

		static final Comparator<Unit> compareByPosition = Comparator.comparing(unit -> unit.position);

		enum Type {
			ELF, GOBELIN
		}

		boolean isAlive() {
			return this.hp > 0;
		}

		boolean isAnEnemy(Unit other) {
			return this.type != other.type;
		}

		List<Point> openSquaresInRange(char[][] cave) {
			return Set.of(position.onTop(), position.onBottom(), position.onLeft(), position.onRight())
			.stream()
			.filter(point -> cave[point.getY()][point.getX()] == '.')
			.collect(toList());
		}
		
		List<Point> squaresInRange(char[][] cave, List<Unit> enemies) {
			return enemies.stream().flatMap(enemie -> enemie.openSquaresInRange(cave).stream()).collect(toList());
		}

		List<Unit> identifyTargets(List<Unit> units) {
			return units.stream().filter(this::isAnEnemy).collect(toList());
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(position);
		}

		@Override
		public boolean equals(Object object) {
			if (object instanceof Unit) {
				var other = (Unit) object;
				return Objects.equals(this.position, other.position);
			}
			return false;
		}

		@Override
		public int compareTo(Unit other) {
			return compareByPosition.compare(this, other);
		}

		@Override
		public String toString() {
			return String.format("%s (%d)", type, hp);
		}
	}
}

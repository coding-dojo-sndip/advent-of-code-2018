package fr.insee.aoc.days;

import java.util.*;

import static fr.insee.aoc.utils.Days.*;

import static fr.insee.aoc.utils.PathFinder.*;

import static java.util.Comparator.*;

import static java.util.stream.Collectors.*;

import fr.insee.aoc.utils.*;

public class Day15 implements Day {

	@Override
	public String part1(String input, Object... params) {
		char[][] cave = cave(input);
		printGrid(cave);
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
					if (unit.canAttack(cave)) {
						// Attack
					} else {
						unit.move(cave, enemies);
						// Attack
					}
				}
			}
			printGrid(cave);
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

	static class Unit implements Comparable<Unit> {

		char type;
		int hp;
		Point position;

		static final Comparator<Unit> compareByPosition = comparing(unit -> unit.position);
		static final Comparator<Unit> compareByHitPoints = comparingInt(unit -> unit.hp);
		
		static Unit create(char type, int i, int j) {
			var unit = new Unit();
			unit.type = type;
			unit.hp = 200;
			unit.position = Point.of(j, i);
			return unit;
		}

		boolean isAlive() {
			return this.hp > 0;
		}

		boolean isAnEnemy(Unit other) {
			return this.type != other.type;
		}

		List<Point> openSquaresInRange(char[][] cave) {
			return position.neighbors()
				.filter(point -> cave[point.getY()][point.getX()] == '.')
				.collect(toList());
		}

		List<Point> squaresInRange(char[][] cave, List<Unit> enemies) {
			return enemies.stream().flatMap(enemie -> enemie.openSquaresInRange(cave).stream()).collect(toList());
		}

		List<Unit> identifyTargets(List<Unit> units) {
			return units.stream().filter(this::isAnEnemy).collect(toList());
		}

		void moveTo(Point destination, char[][] cave) {
			cave[position.getY()][position.getX()] = '.';
			cave[destination.getY()][destination.getX()] = type;
			this.position = destination;
		}

		boolean canAttack(char[][] cave) {
			return position.neighbors().anyMatch(point -> cave[point.getY()][point.getX()] == (type == 'E' ? 'G' : 'E'));
		}

		void move(char[][] cave, List<Unit> enemies) {
			List<Point> squaresInRange = squaresInRange(cave, enemies);
			if (!squaresInRange.isEmpty()) {
				Optional<Point> target = chooseTarget(cave, squaresInRange);
				if(target.isPresent()) {
					this.openSquaresInRange(cave).stream()
						.map(start -> shortestPath(start, target.get(), cave))
						.filter(Objects::nonNull)
						.min(Comparator.<List<Point>>comparingInt(path -> path.size()).thenComparing(path -> path.get(0)))
						.map(path -> path.get(0))
						.ifPresent(point -> moveTo(point, cave));
				}
			}
		}

		Optional<Point> chooseTarget(char[][] cave, List<Point> squaresInRange) {
			return squaresInRange.stream()
				.map(goal -> shortestPath(position, goal, cave))
				.filter(Objects::nonNull)
				.min(Comparator.<List<Point>>comparingInt(path -> path.size()).thenComparing(path -> path.get(path.size() - 1)))
				.map(path -> path.get(path.size() - 1));
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

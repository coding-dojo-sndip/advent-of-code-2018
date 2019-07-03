package fr.insee.aoc.utils;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.*;

public class Point implements Comparable<Point> {

	protected int x, y;

	private static final Comparator<Point> comparator = Comparator.comparingInt(Point::getY).thenComparingInt(Point::getX);

	protected Point() {}

	protected Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Point of(int x, int y) {
		return new Point(x, y);
	}

	public Point onTop() {
		return Point.of(x, y - 1);
	}
	
	public Point onBottom() {
		return Point.of(x, y + 1);
	}
	
	public Point onLeft() {
		return Point.of(x - 1, y);
	}
	
	public Point onRight() {
		return Point.of(x + 1, y);
	}
	
	public Stream<Point> neighbors() {
		return Stream.of(onTop(), onBottom(), onLeft(), onRight());
	}
	
	public int manhattan(Point other) {
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}
	
	@Override
	public int compareTo(Point other) {
		return comparator.compare(this, other);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.x, this.y);
	}

	@Override
	public boolean equals(Object object) {
		if (this == object)
			return true;
		if (object == null)
			return false;
		if (object instanceof Point) {
			Point other = (Point) object;
			return Objects.equals(this.x, other.x) && Objects.equals(this.y, other.y);
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
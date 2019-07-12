package fr.insee.aoc.utils;

import java.util.Comparator;
import java.util.Objects;

public class Point implements Comparable<Point> {

	protected int x;
	protected int y;

	private static final Comparator<Point> comparator = Comparator.comparingInt(Point::getY).thenComparingInt(Point::getX);

	protected Point() {}

	protected Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Point of(int x, int y) {
		return new Point(x, y);
	}

	@Override
	public int compareTo(Point other) {
		return comparator.compare(this, other);
	}

	public int manhattan(Point other) {
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
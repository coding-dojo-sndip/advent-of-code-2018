package fr.insee.aoc.utils;

import java.util.Comparator;
import java.util.Objects;

public class Point implements Comparable<Point> {
	
	int x, y;

	private static final Comparator<Point> comparator = Comparator.comparingInt(Point::getX).thenComparingInt(Point::getY);
	
	private Point() {}
	
	public static Point of(int x, int y) {
		Point point = new Point();
		point.x = x;
		point.y = y;
		return point;
	}

	@Override
	public int compareTo(Point other) {
		return comparator.compare(this, other);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public boolean equals(Object object) {
		if(object == null) return false;
		if(object instanceof Point) {
			Point other = (Point) object;
			return Objects.equals(this.x, other.x) && Objects.equals(this.y, other.y);
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format("(%d, %d)", x, y);
	}

	public int manhattan(Point other) {
		return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
package fr.insee.aoc.utils;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Collection;
import java.util.IntSummaryStatistics;

public class Frame {

	private int top, bottom, left, right;

	private Frame(int top, int bottom, int left, int right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
	}

	public static Frame frameOf(int top, int bottom, int left, int right) {
		return new Frame(top, bottom, left, right);
	}

	public static Frame inBetween(Point a, Point b) {
		int left = min(a.getX(), b.getX());
		int right = max(a.getX(), b.getX());
		int top = min(a.getY(), b.getY());
		int bottom = max(a.getY(), b.getY());
		return frameOf(top, bottom, left, right);
	}

	public static Frame smallestFrameContaining(Collection<Point> points) {
		IntSummaryStatistics statX = points.stream().mapToInt(Point::getX).summaryStatistics();
		int left = statX.getMin();
		int right = statX.getMax();
		IntSummaryStatistics statY = points.stream().mapToInt(Point::getY).summaryStatistics();
		int top = statY.getMin();
		int bottom = statY.getMax();
		return frameOf(top, bottom, left, right);
	}

	int width() {
		return Math.abs(right - left);
	}

	int height() {
		return Math.abs(bottom - top);
	}

	public boolean isOnTheEdge(Point point) {
		return point.getX() == left || point.getX() == right || point.getY() == top || point.getY() == bottom;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		this.bottom = bottom;
	}

	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right = right;
	}
}
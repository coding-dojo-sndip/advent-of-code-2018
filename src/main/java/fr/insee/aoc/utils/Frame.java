package fr.insee.aoc.utils;

import static java.util.stream.Collectors.*;
import static java.lang.Math.*;

import java.util.Collection;
import java.util.IntSummaryStatistics;

public class Frame {
	
	public int top, left, bottom, right;
	
	public static Frame frameOf(int top, int bottom, int left, int right) {
		Frame frame = new Frame();
		frame.left = left;
		frame.right = right;
		frame.top = top;
		frame.bottom = bottom;
		return frame;
	}
	
	public static Frame inBetween(Point a, Point b) {
		return Frame.frameOf(min(a.y, b.y), max(a.y, b.y), min(a.x, b.x), max(a.x, b.x));
	}
	
	public static Frame smallestFrameContaining(Collection<Point> points) {
		IntSummaryStatistics x = points.stream().collect(summarizingInt(Point::getX));
		IntSummaryStatistics y = points.stream().collect(summarizingInt(Point::getY));
		return Frame.frameOf(y.getMin(), y.getMax(), x.getMin(), x.getMax());
	}
	
	public static Frame frameWithBorderContaining(Collection<Point> points, int borderSize) {
		IntSummaryStatistics x = points.stream().collect(summarizingInt(Point::getX));
		IntSummaryStatistics y = points.stream().collect(summarizingInt(Point::getY));
		return Frame.frameOf(y.getMin() - borderSize, y.getMax() + borderSize, x.getMin() - borderSize, x.getMax() + borderSize);
	}
	
	public int width() {
		return right - left;
	}
	
	public int height() {
		return bottom - top;
	}
	
	public boolean isOnTheEdge(Point point) {
		return point.getX() == left || point.getX() == right || point.getY() == top || point.getY() == bottom;
	}
}
package fr.insee.aoc;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Days {

	public static Stream<String> streamOfLines(String input) {
		try {
			return Files.lines(Paths.get(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new DayException("Erreur dans la lecture du fichier");
	}

	public static IntStream streamOfInt(String input) {
		return streamOfLines(input).mapToInt(Integer::parseInt);
	}

	public static List<String> listOfLines(String input) {
		return streamOfLines(input).collect(toList());
	}

	public static List<Integer> listOfIntegers(String input) {
		return streamOfInt(input).boxed().collect(toList());
	}

	public static String[] arrayOfLines(String input) {
		return streamOfLines(input).toArray(String[]::new);
	}

	public static int[] arrayOfInt(String input) {
		return streamOfInt(input).toArray();
	}

	public static <T> boolean in(T element, Collection<T> collection) {
		return collection == null ? false : collection.contains(element);
	}

	public static <T> boolean notIn(T element, Collection<T> collection) {
		return !in(element, collection);
	}

	public static int groupInt(int group, Matcher matcher) {
		return Integer.valueOf(matcher.group(group)).intValue();
	}
	
	public static int readInt(int group, Matcher matcher) {
		return Integer.parseInt(matcher.group(group));
	}
	public static String readString(int group, Matcher matcher) {
		return matcher.group(group);
	}
	public static LocalDateTime readDate(int group, Matcher matcher, DateTimeFormatter formatter) {
		return LocalDateTime.parse(matcher.group(group), formatter);
	}
	
	public static class Point implements Comparable<Point> {
		
		private int x, y;

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

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}
}

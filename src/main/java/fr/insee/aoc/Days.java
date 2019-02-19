package fr.insee.aoc;

import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Days {

	static Stream<String> streamOfLines(String input) {
		try {
			return Files.lines(Paths.get(input));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		throw new DayException("Erreur dans la lecture du fichier");
	}

	static IntStream streamOfInt(String input) {
		return streamOfLines(input).mapToInt(Integer::parseInt);
	}
	
	static <T> Stream<T> streamOfCells(T[][] array) {
		return Arrays.stream(array).flatMap(Arrays::stream);
	}
	
	static IntStream streamOfCells(int[][] array) {
		return Arrays.stream(array).flatMapToInt(Arrays::stream);
	}
	
	static String readLine(String input) {
		return streamOfLines(input).findFirst().orElse("");
	}

	static List<String> listOfLines(String input) {
		return streamOfLines(input).collect(toList());
	}

	static List<Integer> listOfIntegers(String input) {
		return streamOfInt(input).boxed().collect(toList());
	}

	static String[] arrayOfLines(String input) {
		return streamOfLines(input).toArray(String[]::new);
	}

	static int[] arrayOfInt(String input) {
		return streamOfInt(input).toArray();
	}

	static <T> boolean in(T element, Collection<T> collection) {
		return collection != null && collection.contains(element);
	}

	static <T> boolean notIn(T element, Collection<T> collection) {
		return !in(element, collection);
	}
	
	static int readInt(int group, Matcher matcher) {
		return Integer.parseInt(matcher.group(group));
	}

	static String readString(int group, Matcher matcher) {
		return matcher.group(group);
	}

	static LocalDateTime readDate(int group, Matcher matcher, DateTimeFormatter formatter) {
		return LocalDateTime.parse(matcher.group(group), formatter);
	}
	
	static int indexOfMax(int[] array) {
		int maxAt = 0;
		for (int i = 0; i < array.length; i++) {
		    maxAt = array[i] > array[maxAt] ? i : maxAt;
		}
		return maxAt;
	}
	
	static int maxOf(int[] array) {
		return Arrays.stream(array).max().getAsInt();
	}
	
	static int indexOfMin(int[] array) {
		int minAt = 0;
		for (int i = 0; i < array.length; i++) {
			minAt = array[i] < array[minAt] ? i : minAt;
		}
		return minAt;
	}
	
	static int minOf(int[] array) {
		return Arrays.stream(array).min().getAsInt();
	}
	
	static class Point implements Comparable<Point> {
		
		private int x, y;

		private static final Comparator<Point> comparator = Comparator.comparingInt(Point::getX).thenComparingInt(Point::getY);
		
		private Point() {}
		
		static Point of(int x, int y) {
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
	
	static class Frame {
		int top, left, bottom, right;
		
		static Frame smallestFrameContaining(Collection<Point> points) {
			Frame frame = new Frame();
			IntSummaryStatistics statX = points.stream().collect(summarizingInt(Point::getX));
			IntSummaryStatistics statY = points.stream().collect(summarizingInt(Point::getY));
			frame.left = statX.getMin();
			frame.right = statX.getMax();
			frame.top = statY.getMin();
			frame.bottom = statY.getMax();
			return frame;
			
		}
		
		int width() {
			return right - left;
		}
		
		int height() {
			return bottom - top;
		}
		
		boolean isOnTheEdge(Point point) {
			return point.getX() == left || point.getX() == right || point.getY() == top || point.getY() == bottom;
		}
	}
	
	static class DaysCollector<T> implements Collector<T, List<T>, List<T>> {
		
		private Comparator<? super T> comparator;
		
		private DaysCollector(Comparator<? super T> comparator) {
			super();
			this.comparator = comparator;
		}
		
		public static <T> DaysCollector<T> listOfMax(Comparator<? super T> comparator) {
			return new DaysCollector<T>(comparator);
		}
		
		public static <T> DaysCollector<T> listOfMin(Comparator<? super T> comparator) {
			return new DaysCollector<T>(comparator.reversed());
		}
		
		public static <T extends Comparable<T>> DaysCollector<T> listOfMax() {
			return new DaysCollector<T>(Comparator.naturalOrder());
		}
		
		public static <T extends Comparable<T>> DaysCollector<T> listOfMin() {
			return new DaysCollector<T>(Comparator.reverseOrder());
		}

		@Override
		public Supplier<List<T>> supplier() {
			return ArrayList::new;
		}

		@Override
		public BiConsumer<List<T>, T> accumulator() {
			return this::accept;
		}
		
		@Override
		public BinaryOperator<List<T>> combiner() {
			return this::apply;
		}

		@Override
		public Function<List<T>, List<T>> finisher() {
			return Function.identity();
		}

		@Override
		public Set<Characteristics> characteristics() {
			return EnumSet.of(Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED);
		}
		
		private void accept(List<T> list, T t) {
			int compare = 0;
			if(list.isEmpty() || (compare = comparator.compare(t, list.get(0))) == 0) {
				list.add(t);
			}
			else if(compare > 0) {
				list.clear();
				list.add(t);
			}
		}
		
		private List<T> apply(List<T> a, List<T> b) {
			if(a.isEmpty()) return b;
			if(b.isEmpty()) return a;
			int result = comparator.compare(a.get(0), b.get(0));
			if(result < 0) return b;
			else if(result > 0) return a;
			else {
				a.addAll(b);
				return a;
			}
		}
	}
}

package fr.insee.aoc.utils;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Days {

	public static Stream<String> streamOfLines(String input) {
		try {
			return Files.lines(Paths.get(input));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		throw new DayException("Erreur dans la lecture du fichier");
	}

	public static IntStream streamOfInt(String input) {
		return streamOfLines(input).mapToInt(Integer::parseInt);
	}
	
	public static <T> Stream<T> streamOfCells(T[][] array) {
		return Arrays.stream(array).flatMap(Arrays::stream);
	}
	
	public static IntStream streamOfCells(int[][] array) {
		return Arrays.stream(array).flatMapToInt(Arrays::stream);
	}
	
	public static String readLine(String input) {
		return streamOfLines(input).findFirst().orElse("");
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
		return collection != null && collection.contains(element);
	}

	public static <T> boolean notIn(T element, Collection<T> collection) {
		return !in(element, collection);
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
	
	public static int indexOfMax(int[] array) {
		int maxAt = 0;
		for (int i = 0; i < array.length; i++) {
		    maxAt = array[i] > array[maxAt] ? i : maxAt;
		}
		return maxAt;
	}
	
	public static int maxOf(int[] array) {
		return Arrays.stream(array).max().getAsInt();
	}
	
	public static int indexOfMin(int[] array) {
		int minAt = 0;
		for (int i = 0; i < array.length; i++) {
			minAt = array[i] < array[minAt] ? i : minAt;
		}
		return minAt;
	}
	
	public static int minOf(int[] array) {
		return Arrays.stream(array).min().getAsInt();
	}
}

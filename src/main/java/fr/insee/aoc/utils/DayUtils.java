package fr.insee.aoc.utils;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import fr.insee.aoc.exception.DayException;

public class DayUtils {
	
	
	public static Stream<String> streamOfLines(String input){
		try {
			return Files.lines(Paths.get(input));
		}
		catch (IOException e) {
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

	@SafeVarargs
	public static <T> Set<T> setOf(T... elements) {
		return new HashSet<>(Arrays.asList(elements));
	}
	
	public static <T> boolean in(T element, Collection<T> collection) {
		if(collection == null) return false;
		return collection.contains(element);
	}
	
	public static <T> boolean notIn(T element, Collection<T> collection) {
		return !in(element, collection);
	}
}

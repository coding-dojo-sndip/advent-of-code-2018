package fr.insee.aoc;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public abstract class DayUtils {

	private DayUtils() {}
	
	public static List<String> readLines(String input) {
		try {
			return FileUtils.readLines(Paths.get(input).toFile(), "UTF-8");
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String[] readToArray(String input) {
		List<String> lines = readLines(input);
		return lines.toArray(new String[lines.size()]);
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

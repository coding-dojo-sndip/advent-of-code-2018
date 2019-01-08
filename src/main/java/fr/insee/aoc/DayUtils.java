package fr.insee.aoc;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

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
}

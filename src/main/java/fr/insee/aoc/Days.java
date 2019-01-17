package fr.insee.aoc;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Days {

	public static List<String> readLines(String input) {
		try {
			return Files.readAllLines(Paths.get(input), Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new DayException("Erreur dans la lecture du fichier");
	}

	public static Stream<String> streamLines(String input) {
		try {
			return Files.lines(Paths.get(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new DayException("Erreur dans la lecture du fichier");
	}

}

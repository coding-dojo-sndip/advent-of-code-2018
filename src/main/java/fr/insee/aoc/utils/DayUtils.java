package fr.insee.aoc.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.insee.aoc.exception.DayException;

public class DayUtils {
	
	public static List<String> readLines(String input) {
		try {
			return FileUtils.readLines(new File(input), "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new DayException("Erreur dans la lecture du fichier");
	}

}

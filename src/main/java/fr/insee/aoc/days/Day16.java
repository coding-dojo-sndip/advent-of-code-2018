package fr.insee.aoc.days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.utils.Days;
import fr.insee.aoc.utils.OpCode;

public class Day16 implements Day {

	@Override
	public String part1(String input, Object... params) {
		List<Sample> samples = extractSamples(input);
		for (Sample sample : samples) {
			for (OpCode opCode : OpCode.values()) {
				// Arrays.equals compare membre par membre le contenu du tableau
				if (Arrays.equals(sample.after,
						opCode.apply(sample.before, sample.operand1, sample.operand2, sample.target))) {
					sample.operationsPossibles.add(opCode);
				}
			}
		}
		return String.valueOf(samples.stream().filter(sample -> sample.operationsPossibles.size() > 2).count());
	}

	private List<Sample> extractSamples(String input) {
		List<String> liste = Days.listOfLines(input);
		Pattern regexpLigne1 = Pattern.compile("Before: \\[([0-9]+), ([0-9]+), ([0-9]+), ([0-9]+)\\]");
		Pattern regexpLigne2 = Pattern.compile("([0-9]+) ([0-9]+) ([0-9]+) ([0-9]+)");
		Pattern regexpLigne3 = Pattern.compile("After:  \\[([0-9]+), ([0-9]+), ([0-9]+), ([0-9]+)\\]");
		List<Sample> samples = new ArrayList<>();

		// On s'arrete lorsqu'il reste strictement moins de trois ligne (cas de la
		// dernière ligne vide ou non)
		for (int i = 0; i < liste.size() - 2; i += 4) {
			Sample sample = new Sample();
			Matcher matcher1 = regexpLigne1.matcher(liste.get(i));
			Matcher matcher2 = regexpLigne2.matcher(liste.get(i + 1));
			Matcher matcher3 = regexpLigne3.matcher(liste.get(i + 2));

			if (matcher1.matches()) {
				Integer[] before = new Integer[4];
				before[0] = Integer.parseInt(matcher1.group(1));
				before[1] = Integer.parseInt(matcher1.group(2));
				before[2] = Integer.parseInt(matcher1.group(3));
				before[3] = Integer.parseInt(matcher1.group(4));
				sample.before = before;
			}

			if (matcher3.matches()) {
				Integer[] after = new Integer[4];
				after[0] = Integer.parseInt(matcher3.group(1));
				after[1] = Integer.parseInt(matcher3.group(2));
				after[2] = Integer.parseInt(matcher3.group(3));
				after[3] = Integer.parseInt(matcher3.group(4));
				sample.after = after;
			}

			if (matcher2.matches()) {
				sample.operationCode = Integer.parseInt(matcher2.group(1));
				sample.operand1 = Integer.parseInt(matcher2.group(2));
				sample.operand2 = Integer.parseInt(matcher2.group(3));
				sample.target = Integer.parseInt(matcher2.group(4));
			}

			samples.add(sample);
		}
		return samples;
	}

	@Override
	public String part2(String input, Object... params) {
		return Day.super.part2(input, params);
	}

	private class Sample {
		Integer[] before = new Integer[4];
		Integer[] after = new Integer[4];
		int operationCode;
		int operand1;
		int operand2;
		int target;
		List<OpCode> operationsPossibles = new ArrayList<>();
	}

}

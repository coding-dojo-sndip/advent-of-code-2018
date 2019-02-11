package fr.insee.aoc;

import java.util.stream.IntStream;

import static fr.insee.aoc.Days.*;

public class Day05 implements Day {

    @Override
    public String part1(String input) {
        int length = triggerFullReaction(readLine(input));
        return String.valueOf(length);
    }

    @Override
    public String part2(String input) {
        String polymer = readLine(input);
        int a = 97, z = 122;
        int min = IntStream.rangeClosed(a, z)
            .mapToObj(c -> (char) c + "|" + (char)(c - 32))
            .map(regex -> polymer.replaceAll(regex, ""))
            .mapToInt(Day05::triggerFullReaction)
            .min()
            .orElse(-1);
        return String.valueOf(min);
    }

    private static int triggerFullReaction(String polymer) {
        return polymer.chars()
            .mapToObj(c -> (char) c)
            .collect(StringBuilder::new, Day05::react, StringBuilder::append)
            .length();
    }

    private static void react(StringBuilder builder, Character character) {
        int lastIndex = builder.length() - 1;
        if (lastIndex < 0 || doesntReact(builder.charAt(lastIndex), character)) {
            builder.append(character);
        }
        else {
            builder.deleteCharAt(lastIndex);
        }
    }

    private static boolean doesntReact(char a, char b) {
        return Math.abs(a - b) != 32;
    }
}

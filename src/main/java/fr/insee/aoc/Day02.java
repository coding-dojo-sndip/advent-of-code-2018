package fr.insee.aoc;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static fr.insee.aoc.Days.listOfLines;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;

public class Day02 implements Day {

    @Override
    public String part1(String input) {
        List<String> strings = listOfLines(input);
        long a = numberOfStringsNTimes(strings, 2);
        long b = numberOfStringsNTimes(strings, 3);
        return String.valueOf(a * b);
    }

    private long numberOfStringsNTimes(List<String> strings, int n) {
        return strings.stream()
                .map(this::occurences)
                .filter(o -> containsSameCharacter(o, n))
                .count();
    }

    private Map<Integer, Long> occurences(String string) {
        return string.chars()
                .boxed()
                .collect(Collectors.groupingBy(identity(), counting()));
    }

    private boolean containsSameCharacter(Map<Integer, Long> occurences, int numberOfOccurences) {
        return occurences.values().contains((long) numberOfOccurences);
    }

    private boolean differsByOneChar(String a, String b) {
        int compteur = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                compteur++;
                if (compteur > 1) {
                    return false;
                }
            }
        }
        return compteur == 1;
    }

    private String charsInCommon(String a, String b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                sb.append(a.charAt(i));
            }
        }
        return sb.toString();
    }

    @Override
    public String part2(String input) {
        List<String> strings = listOfLines(input);
        for (int i = 0; i < strings.size(); i++) {
            String a = strings.get(i);
            for (int j = i + 1; j < strings.size(); j++) {
                if (differsByOneChar(a, strings.get(j))) {
                    return charsInCommon(a, strings.get(j));
                }
            }
        }
        throw new DayException();
    }
}

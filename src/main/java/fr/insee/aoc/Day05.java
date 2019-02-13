package fr.insee.aoc;

import static fr.insee.aoc.Days.readLine;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

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
            .collect(ArrayDeque::new, Day05::uniteReaction, (t, u) -> {throw new UnsupportedOperationException();})
            .size();
    }

    private static void uniteReaction(Deque<Character> queue, Character unite) {
    	if(!queue.isEmpty() && reactTogether(unite, queue.peek())) {
            queue.pop();
        }
        else {
            queue.push(unite);
        }
    }

    private static boolean reactTogether(char a, char b) {
        return Math.abs(a - b) == 32;
    }
}

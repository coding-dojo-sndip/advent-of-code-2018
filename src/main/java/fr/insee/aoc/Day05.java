package fr.insee.aoc;

import java.util.ArrayDeque;
import java.util.Deque;
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
        Deque<Character> queue = new ArrayDeque<>(polymer.length());
        for(int i = 0; i < polymer.length(); i ++) {
            char unite = polymer.charAt(i);
            if(!queue.isEmpty() && reactTogether(unite, queue.peek())) {
                queue.pop();
            }
            else {
                queue.push(unite);
            }
        }
        return queue.size();
    }

    private static boolean reactTogether(char a, char b) {
        return Math.abs(a - b) == 32;
    }
}

package fr.insee.aoc;

import java.util.stream.IntStream;

import static fr.insee.aoc.Days.*;

public class Day05 implements Day {

    @Override
    public String part1(String input) {
        Reaction reaction = new Reaction(readLine(input));
        int length = reaction.triggerFullReaction();
        return String.valueOf(length);
    }

    @Override
    public String part2(String input) {
        String polymer = readLine(input);
        int min = IntStream.rangeClosed(97, 122)
            .mapToObj(c -> (char) c + "|" + (char)(c - 32))
            .map(regex -> polymer.replaceAll(regex, ""))
            .map(Reaction::new)
            .mapToInt(Reaction::triggerFullReaction)
            .min()
            .orElse(-1);
        return String.valueOf(min);
    }

    static class Reaction {
        StringBuilder polymer;
        int index;

        Reaction(String polymer) {
            this.polymer = new StringBuilder(polymer);
            this.index = 0;
        }

        boolean stillReacts() {
            return index < polymer.length();
        }

        boolean willReact(int left, int right) {
            if(left < 0 || right >= polymer.length()) return false;
            return canReact(polymer.charAt(left), polymer.charAt(right));
        }

        boolean canReact(char a, char b) {
            return Math.abs(a - b) == 32;
        }

        void react() {
            react(index, index + 1);
        }

        void react(int left, int right) {
            if(willReact(left, right)){
                index --;
                react( left - 1, right + 1);
            }
            else {
                index ++;
                polymer.delete(left + 1, right);
            }
        }

        int triggerFullReaction() {
            while(stillReacts()) {
                react();
            }
            return polymer.length();
        }
    }
}

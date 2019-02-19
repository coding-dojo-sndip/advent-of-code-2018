package fr.insee.aoc;

import static fr.insee.aoc.Days.*;
import static fr.insee.aoc.Days.MaxCollector.*;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.assertj.core.util.Sets;
import org.junit.Test;


public class DaysTest  {
    
	private static final String small_file = "src/test/resources/days.txt", empty_file = "src/test/resources/empty.txt";
	
	@Test
	public void streamOfLines_test_small_file() {
		assertThat(streamOfLines(small_file))
			.isNotEmpty()
			.hasSize(5)
			.hasOnlyElementsOfType(String.class)
			.containsExactly("+3", "+3", "+4", "-2", "-4");
	}
	
	@Test
	public void streamOfLines_test_not_here() {
		assertThatExceptionOfType(DayException.class)
			.isThrownBy(() -> streamOfLines("src/test/resources/not_here.txt"))
	        .withMessage("Erreur dans la lecture du fichier")
	        .withNoCause();
	}
	
	@Test
	public void streamOfLines_test_empty_file() {
		assertThat(streamOfLines(empty_file)).isEmpty();
	}
	
	@Test
	public void streamOfInt_test_small_file() {
		assertThat(streamOfInt(small_file))
			.isNotEmpty()
			.hasSize(5)
			.hasOnlyElementsOfType(Integer.class)
			.containsExactly(3, 3, 4, -2, -4);
	}
	
	@Test
	public void streamOfInt_test_empty_file() {
		assertThat(streamOfInt(empty_file)).isEmpty();
	}
	
	@Test
	public void streamOfCells_test_string_array() {
		String[][] string_array = new String[][] {{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};
		assertThat(streamOfCells(string_array))
			.isNotEmpty()
			.hasSize(8)
			.hasOnlyElementsOfType(String.class)
			.containsExactly("a", "b", "c", "d", "e", "f", "g", "h");
	}
	
	@Test
	public void streamOfCells_test_int_array() {
		int[][] int_array = new int[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
		assertThat(streamOfCells(int_array))
			.isNotEmpty()
			.hasSize(8)
			.hasOnlyElementsOfType(Integer.class)
			.containsExactly(1, 2, 3, 4, 5, 6, 7, 8);
	}
	
	@Test
	public void readLine_test_small_file() {
		assertThat(readLine(small_file))
			.isNotBlank()
			.isEqualTo("+3");
	}
	
	@Test
	public void readLine_test_empty_file() {
		assertThat(readLine(empty_file))
			.isNotNull()
			.isBlank();
	}
	
	@Test
	public void listOfLines_test_small_file() {
		assertThat(listOfLines(small_file))
			.isNotEmpty()
			.hasSize(5)
			.isInstanceOf(List.class)
			.hasOnlyElementsOfType(String.class)
			.containsExactly("+3", "+3", "+4", "-2", "-4");
	}
	
	@Test
	public void listOfLines_test_empty_file() {
		assertThat(listOfLines(empty_file))
			.isNotNull()
			.isInstanceOf(List.class)
			.isEmpty();
	}
	
	@Test
	public void listOfIntegers_test_small_file() {
		assertThat(listOfIntegers(small_file))
			.isNotEmpty()
			.hasSize(5)
			.isInstanceOf(List.class)
			.hasOnlyElementsOfType(Integer.class)
			.containsExactly(3, 3, 4, -2, -4);
	}
	
	@Test
	public void listOfIntegers_test_empty_file() {
		assertThat(listOfIntegers(empty_file))
			.isNotNull()
			.isInstanceOf(List.class)
			.isEmpty();
	}
	
	@Test
	public void arrayOfLines_test_small_file() {
		assertThat(arrayOfLines(small_file))
			.isNotEmpty()
			.hasSize(5)
			.isExactlyInstanceOf(String[].class)
			.hasOnlyElementsOfType(String.class)
			.containsExactly("+3", "+3", "+4", "-2", "-4");
	}
	
	@Test
	public void arrayOfLines_test_empty_file() {
		assertThat(arrayOfLines(empty_file))
			.isNotNull()
			.isExactlyInstanceOf(String[].class)
			.isEmpty();
	}
	
	@Test
	public void arrayOfInt_test_small_file() {
		assertThat(arrayOfInt(small_file))
			.isNotEmpty()
			.hasSize(5)
			.isExactlyInstanceOf(int[].class)
			.containsExactly(3, 3, 4, -2, -4);
	}
	
	@Test
	public void arrayOfInt_test_empty_file() {
		assertThat(arrayOfInt(empty_file))
			.isNotNull()
			.isExactlyInstanceOf(int[].class)
			.isEmpty();
	}
	
	@Test
	public void in_list_is_true() {
		List<String> list = Arrays.asList("a", "b", "c");
		assertThat(in("a", list)).isTrue();
	}	
	
	@Test
	public void in_list_is_false() {
		List<String> list = Arrays.asList("a", "b", "c");
		assertThat(in("A", list)).isFalse();
	}	
	
	@Test
	public void notIn_list_is_true() {
		List<String> list = Arrays.asList("a", "b", "c");
		assertThat(notIn("A", list)).isTrue();
	}	
	
	@Test
	public void notIn_list_is_false() {
		List<String> list = Arrays.asList("a", "b", "c");
		assertThat(notIn("a", list)).isFalse();
	}	
	
	@Test
	public void in_set_is_true() {
		Set<Integer> set = Sets.newTreeSet(1, 2, 3);
		assertThat(in(1, set)).isTrue();
	}	
	
	@Test
	public void in_set_is_false() {
		Set<Integer> set = Sets.newTreeSet(1, 2, 3);
		assertThat(in(4, set)).isFalse();
	}	
	
	@Test
	public void notIn_set_is_true() {
		Set<Integer> set = Sets.newTreeSet(1, 2, 3);
		assertThat(notIn(4, set)).isTrue();
	}	
	
	@Test
	public void notIn_set_is_false() {
		Set<Integer> set = Sets.newTreeSet(1, 2, 3);
		assertThat(notIn(1, set)).isFalse();
	}	
	
	@Test
	public void readInt_test() {
		Pattern pattern = Pattern.compile("\\[(.*)] .* #(\\d+).*");
		Matcher matcher = pattern.matcher("[1518-11-03 00:05] Guard #10 begins shift]");
		matcher.matches();
		assertThat(readInt(2, matcher)).isEqualTo(10);
	}
	
	@Test
	public void readString_test() {
		Pattern pattern = Pattern.compile("\\[(.*)] .* #(\\d+).*");
		Matcher matcher = pattern.matcher("[1518-11-03 00:05] Guard #10 begins shift]");
		matcher.matches();
		assertThat(readString(1, matcher)).isEqualTo("1518-11-03 00:05");
	}
	
	@Test
	public void readDate_test() {
		Pattern pattern = Pattern.compile("\\[(.*)] .* #(\\d+).*");
		Matcher matcher = pattern.matcher("[1518-11-03 00:05] Guard #10 begins shift]");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		matcher.matches();
		assertThat(readDate(1, matcher, formatter)).isEqualToIgnoringSeconds(LocalDateTime.of(1518, 11, 3, 0, 5));
	}
	
	@Test
	public void indexOfMax_test() {
		int[] int_array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		assertThat(indexOfMax(int_array)).isEqualTo(7);
	}
	
	@Test
	public void indexOfMax_test_with_equality() {
		int[] int_array = new int[]{1, 8, 1, 1, 1, 0, 8, 8};
		assertThat(indexOfMax(int_array)).isEqualTo(1);
	}
	
	@Test
	public void indexOfMin_test() {
		int[] int_array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		assertThat(indexOfMin(int_array)).isEqualTo(0);
	}
	
	@Test
	public void indexOfMin_test_with_equality() {
		int[] int_array = new int[]{1, 8, 1, 1, 1, 0, 8, 8};
		assertThat(indexOfMin(int_array)).isEqualTo(5);
	}
	
	@Test
	public void maxOf_test() {
		int[] int_array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		assertThat(maxOf(int_array)).isEqualTo(8);
	}
	
	@Test
	public void maxOf_test_with_equality() {
		int[] int_array = new int[]{1, 8, 1, 1, 1, 0, 8, 8};
		assertThat(maxOf(int_array)).isEqualTo(8);
	}
	
	@Test
	public void minOf_test() {
		int[] int_array = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
		assertThat(minOf(int_array)).isEqualTo(1);
	}
	
	@Test
	public void minOf_test_with_equality() {
		int[] int_array = new int[]{1, 8, 1, 1, 1, 0, 8, 8};
		assertThat(minOf(int_array)).isEqualTo(0);
	}
	
	@Test
	public void maxList_test() {
		int[] int_array = new int[]{1, 8, 1, 1, 1, 0, 8, 8};
		List<Integer> listOfMax = Arrays.stream(int_array).boxed().collect(listOfMax());
		assertThat(listOfMax)
			.hasSize(3)
			.containsOnly(8);
	}
	
	@Test
	public void maxList_test_string_length() {
		String[] word_array = new String[]{"middle", "seed", "jump", "crowd", "chanel", "error", "ruin", "travel"};
		List<String> listOfMax = Arrays.stream(word_array).collect(listOfMax(Comparator.comparingInt(String::length)));
		assertThat(listOfMax)
			.hasSize(3)
			.containsExactly("middle", "chanel", "travel");
	}
	
	@Test
	public void minList_test() {
		int[] int_array = new int[]{1, 8, 1, 1, 1, 2, 8, 8};
		List<Integer> listOfMin = Arrays.stream(int_array).boxed().collect(listOfMin());
		assertThat(listOfMin)
			.hasSize(4)
			.containsOnly(1);
	}
	
	@Test
	public void minList_test_string_length() {
		String[] word_array = new String[]{"middle", "seed", "jump", "crowd", "chanel", "error", "ruin", "travel"};
		List<String> listOfMin = Arrays.stream(word_array).collect(listOfMin(Comparator.comparingInt(String::length)));
		assertThat(listOfMin)
			.hasSize(3)
			.containsExactly("seed", "jump", "ruin");
	}
}

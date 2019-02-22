package fr.insee.aoc.utils;

import static fr.insee.aoc.utils.Collectors.listOfMax;
import static fr.insee.aoc.utils.Collectors.listOfMin;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;


public class CollectorsTest  {
    
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

package fr.insee.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;


public class FrameTest  {
    
	@Test
	public void testFrame_smallestFrameContaining() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		Point c = Point.of(0, 0);
		Frame frame = Frame.smallestFrameContaining(Arrays.asList(a, b, c));
		assertThat(frame)
			.hasFieldOrPropertyWithValue("top", 0)
			.hasFieldOrPropertyWithValue("bottom", 3)
			.hasFieldOrPropertyWithValue("left", -1)
			.hasFieldOrPropertyWithValue("right", 2);
	}
	
	@Test
	public void testFrame_inBetween() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		Frame frame = Frame.inBetween(a, b);
		assertThat(frame)
			.hasFieldOrPropertyWithValue("top", 1)
			.hasFieldOrPropertyWithValue("bottom", 3)
			.hasFieldOrPropertyWithValue("left", -1)
			.hasFieldOrPropertyWithValue("right", 2);
	}
	
	@Test
	public void testFrame_isOnTheEdge() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		Frame frame = Frame.frameOf(0, 3, 0, 2);
		assertThat(frame.isOnTheEdge(a)).isFalse();
		assertThat(frame.isOnTheEdge(b)).isTrue();
	}
}

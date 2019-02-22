package fr.insee.aoc.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;


public class PointTest  {
    
	@Test
	public void testPoint_equals() {
		Point a = Point.of(1, 1);
		Point b = Point.of(1, 1);
		assertThat(a)
			.isEqualTo(b)
			.isEqualByComparingTo(b);
	}
	
	@Test
	public void testPoint_notEquals() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		assertThat(a)
			.isNotEqualTo(b)
			.isNotEqualByComparingTo(b);
	}
	
	@Test
	public void testPoint_compare() {
		Point a = Point.of(-1, 2);
		Point b = Point.of(1, 2);
		Point c = Point.of(1, 3);
		Point d = Point.of(1, 3);
		assertThat(d)
			.isEqualByComparingTo(c)
			.isGreaterThan(b)
			.isGreaterThan(a);
	}
	
	@Test
	public void testPoint_manhattan() {
		Point a = Point.of(-1, 1);
		Point b = Point.of(2, 3);
		assertThat(a.manhattan(b))
			.isEqualTo(b.manhattan(a))
			.isEqualTo(5);
	}
}

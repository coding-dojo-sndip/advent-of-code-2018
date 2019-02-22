package fr.insee.aoc.utils;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class Collectors<T> implements Collector<T, List<T>, List<T>>{
	
	public static <T> Collectors<T> listOfMax(Comparator<? super T> comparator) {
		throw new DayException("Not implemented yet!"); // TODO
	}
	
	public static <T> Collectors<T> listOfMin(Comparator<? super T> comparator) {
		throw new DayException("Not implemented yet!"); // TODO
	}
	
	public static <T extends Comparable<T>> Collectors<T> listOfMax() {
		throw new DayException("Not implemented yet!"); // TODO
	}
	
	public static <T extends Comparable<T>> Collectors<T> listOfMin() {
		throw new DayException("Not implemented yet!"); // TODO
	}

	@Override
	public Supplier<List<T>> supplier() {
		throw new DayException("Not implemented yet!"); // TODO
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		throw new DayException("Not implemented yet!"); // TODO
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		throw new DayException("Not implemented yet!"); // TODO
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		throw new DayException("Not implemented yet!"); // TODO
	}

	@Override
	public Set<Characteristics> characteristics() {
		throw new DayException("Not implemented yet!"); // TODO
	}
}
package fr.insee.aoc.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class Collectors<T> implements Collector<T, List<T>, List<T>> {

	private Comparator<? super T> comparator;

	public Collectors(Comparator<? super T> comparator) {
		this.comparator = comparator;
	}

	public static <T> Collectors<T> listOfMax(Comparator<? super T> comparator) {
		return new Collectors<>(comparator.reversed());
	}

	public static <T> Collectors<T> listOfMin(Comparator<? super T> comparator) {
		return new Collectors<>(comparator);
	}

	public static <T extends Comparable<T>> Collectors<T> listOfMax() {
		return new Collectors<>(Comparator.reverseOrder());
	}

	public static <T extends Comparable<T>> Collectors<T> listOfMin() {
		return new Collectors<>(Comparator.naturalOrder());
	}

	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return (list, t) -> {
			if (list.isEmpty() || comparator.compare(list.get(0), t) == 0) {
				list.add(t);
			} else if (comparator.compare(list.get(0), t) > 0) {
				list.clear();
				list.add(t);
			}
		};
	}

	@Override
	public BinaryOperator<List<T>> combiner() {
		return (a, b) -> {
			if (a.isEmpty()) {
				return b;
			}
			if (b.isEmpty()) {
				return a;
			}
			if (comparator.compare(a.get(0), b.get(0)) == 0) {
				a.addAll(b);
				return a;
			}
			if (comparator.compare(a.get(0), b.get(0)) < 0) {
				return a;
			}
			return b;
		};
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		return Collections::unmodifiableList;
	}

	@Override
	public Set<Characteristics> characteristics() {
		return EnumSet.of(Characteristics.CONCURRENT, Characteristics.UNORDERED);
	}
}
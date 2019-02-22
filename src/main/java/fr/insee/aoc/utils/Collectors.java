package fr.insee.aoc.utils;

import java.util.ArrayList;
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
	
	private Collectors(Comparator<? super T> comparator) {
		super();
		this.comparator = comparator;
	}
	
	public static <T> Collectors<T> listOfMax(Comparator<? super T> comparator) {
		return new Collectors<T>(comparator);
	}
	
	public static <T> Collectors<T> listOfMin(Comparator<? super T> comparator) {
		return new Collectors<T>(comparator.reversed());
	}
	
	public static <T extends Comparable<T>> Collectors<T> listOfMax() {
		return new Collectors<T>(Comparator.naturalOrder());
	}
	
	public static <T extends Comparable<T>> Collectors<T> listOfMin() {
		return new Collectors<T>(Comparator.reverseOrder());
	}

	@Override
	public Supplier<List<T>> supplier() {
		return ArrayList::new;
	}

	@Override
	public BiConsumer<List<T>, T> accumulator() {
		return this::accept;
	}
	
	@Override
	public BinaryOperator<List<T>> combiner() {
		return this::apply;
	}

	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return EnumSet.of(Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH, Characteristics.UNORDERED);
	}
	
	private void accept(List<T> list, T t) {
		int compare = 0;
		if(list.isEmpty() || (compare = comparator.compare(t, list.get(0))) == 0) {
			list.add(t);
		}
		else if(compare > 0) {
			list.clear();
			list.add(t);
		}
	}
	
	private List<T> apply(List<T> a, List<T> b) {
		if(a.isEmpty()) return b;
		if(b.isEmpty()) return a;
		int result = comparator.compare(a.get(0), b.get(0));
		if(result < 0) return b;
		else if(result > 0) return a;
		else {
			a.addAll(b);
			return a;
		}
	}
}
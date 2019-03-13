package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day08 implements Day {
	
	@Override
	public String part1(String input, Object... params) {
		Node tree = readTree(input);
		int metadataSum = tree.metadataSum();
		return String.valueOf(metadataSum);
	}
	
	@Override
	public String part2(String input, Object... params) {
		Node tree = readTree(input);
		int nodeValue = tree.nodeValue();
		return String.valueOf(nodeValue);
	}
	
	private static Node readTree(String input) {
		int[] numbers = readNumbers(input);
		AtomicInteger index = new AtomicInteger(2);
		Deque<Node> incompleteNodes = new ArrayDeque<>();
		Node tree = readRoot(numbers);
		incompleteNodes.push(tree);
		while(isNotEmpty(incompleteNodes)){
			readNode(index, incompleteNodes, numbers);
		}
		return tree;
	}

	private static void readNode(AtomicInteger index, Deque<Node> incompleteNodes, int[] numbers) {
		int numberOfChildren = numbers[index.getAndIncrement()];
		int numberOfMetadata = numbers[index.getAndIncrement()];
		Node node = Node.from(numberOfChildren, numberOfMetadata);
		incompleteNodes.push(node);
		fillNode(node, index, incompleteNodes, numbers);
	}

	private static void fillNode(Node node, AtomicInteger index, Deque<Node> incompleteNodes, int[] numbers) {
		if(node.isComplete()) {
			for(int i = 0; i < node.numberOfMetadata; i ++) {
				node.metadata.add(numbers[index.getAndIncrement()]);
			}
			incompleteNodes.pop();
			if(isNotEmpty(incompleteNodes)) {
				Node parent =  incompleteNodes.peek();
				parent.children.add(node);
				fillNode(parent, index, incompleteNodes, numbers);
			}
		}
	}
	
	
	private static Node readRoot(int[] numbers) {
		return Node.from(numbers[0], numbers[1]);
	}
	
	private static int[] readNumbers(String input) {
		return Arrays.stream(readLine(input).split(" ")).mapToInt(Integer::valueOf).toArray();
	}
	
	private static class Node {
		int numberOfChildren, numberOfMetadata;
		List<Integer> metadata;
		List<Node> children;

		Node(int numberOfChildren, int numberOfMetadata) {
			this.numberOfChildren = numberOfChildren;
			this.numberOfMetadata = numberOfMetadata;
			this.metadata = new ArrayList<>(numberOfMetadata);
			this.children = new ArrayList<>(numberOfChildren);
		}
		
		static Node from(int numberOfChildren, int numberOfMetadata) {
			return new Node(numberOfChildren, numberOfMetadata);
		}
		
		boolean isComplete() {
			return children.size() == numberOfChildren;
		}
		
		int metadataSum() {
			return metadata.stream().mapToInt(Integer::valueOf).sum() + children.stream().mapToInt(Node::metadataSum).sum();
		}
		
		int nodeValue() {
			if(numberOfChildren == 0) {
				return metadata.stream().mapToInt(Integer::valueOf).sum();
			}
			return metadata.stream()
				.filter(index -> 0 <= index && index <= numberOfChildren)
				.mapToInt(index -> children.get(index - 1).nodeValue())
				.sum();
		}
	}
}

package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readLine;

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
		Deque<Node> uncompleteNodes = new ArrayDeque<>();
		Node tree = readRoot(numbers);
		uncompleteNodes.push(tree);
		while(!uncompleteNodes.isEmpty()){
			readNode(index, uncompleteNodes, numbers);
		}
		return tree;
	}

	private static void readNode(AtomicInteger index, Deque<Node> uncompleteNodes, int[] numbers) {
		int numberOfChildren = numbers[index.getAndIncrement()];
		int numberOfMetadata = numbers[index.getAndIncrement()];
		Node node = Node.with(numberOfChildren, numberOfMetadata);
		uncompleteNodes.push(node);
		completeNode(node, index, uncompleteNodes, numbers);
	}

	private static void completeNode(Node node, AtomicInteger index, Deque<Node> uncompleteNodes, int[] numbers) {
		if(node.isComplete()) {
			for(int i = 0; i < node.numberOfMetadata; i ++) {
				node.metadata.add(numbers[index.getAndIncrement()]);
			}
			uncompleteNodes.pop();
			if(!uncompleteNodes.isEmpty()) {
				Node parent =  uncompleteNodes.peek();
				parent.children.add(node);
				completeNode(parent, index, uncompleteNodes, numbers);
			}
		}
	}
	
	
	private static Node readRoot(int[] numbers) {
		return Node.with(numbers[0], numbers[1]);
	}
	
	private static int[] readNumbers(String input) {
		return Arrays.stream(readLine(input).split(" ")).mapToInt(Integer::valueOf).toArray();
	}
	
	private static class Node {
		int numberOfChildren, numberOfMetadata;
		List<Integer> metadata;
		List<Node> children;

		public Node(int numberOfChildren, int numberOfMetadata) {
			this.numberOfChildren = numberOfChildren;
			this.numberOfMetadata = numberOfMetadata;
			this.metadata = new ArrayList<>(numberOfMetadata);
			this.children = new ArrayList<>(numberOfChildren);
		}
		
		public static Node with(int numberOfChildren, int numberOfMetadata) {
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

package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Days.readLine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Day08 implements Day {

	@Override
	public String part1(String input, Object... params) {
		Node root = readTree(input);
		int sumMetadata = root.sumMetadata();
		return String.valueOf(sumMetadata);
	}

	@Override
	public String part2(String input, Object... params) {
		Node root = readTree(input);
		int nodeValue = root.nodeValue();
		return String.valueOf(nodeValue);
	}

	private Node readTree(String input) {
		int[] numbers = readNumbers(input);
		Deque<Node> incompleteNodes = new ArrayDeque<>();
		Node root = createNode(numbers, 0);
		incompleteNodes.push(root);
		int i = 2;
		while (!incompleteNodes.isEmpty()) {
			Node currentNode = incompleteNodes.peek();
			if (currentNode.isComplete()) {
				for (int j = i; j < i + currentNode.numberOfMetadata; j++) {
					currentNode.metadata.add(numbers[j]);
				}
				i += currentNode.numberOfMetadata - 1;
				incompleteNodes.pop();
			} else {
				Node node = createNode(numbers, i);
				currentNode.childNodes.add(node);
				incompleteNodes.push(node);
				i++;
			}
			i ++;
		}
		return root;
	}

	private static int[] readNumbers(String input) {
		return Arrays.stream(readLine(input).split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();
	}

	private static Node createNode(int[] numbers, int index) {
		Node node = new Node();
		node.numberOfChildNodes = numbers[index];
		node.numberOfMetadata = numbers[index + 1];
		return node;
	}

	private static class Node {
		int numberOfChildNodes;
		int numberOfMetadata;
		List<Node> childNodes = new ArrayList<>();
		List<Integer> metadata = new ArrayList<>();

		boolean isComplete() {
			return numberOfChildNodes == childNodes.size();
		}

		public int sumMetadata() {
			return this.metadata.stream().mapToInt(Integer::intValue).sum() + this.childNodes.stream().mapToInt(Node::sumMetadata).sum();
		}

		public int nodeValue() {
			if (this.numberOfChildNodes == 0) {
				return this.sumMetadata();
			}
			return metadata.stream()
				.filter(index -> index > 0 && index <= numberOfChildNodes)
				.map(i -> childNodes.get(i - 1))
				.mapToInt(Node::nodeValue)
				.sum();
		}
	}
}

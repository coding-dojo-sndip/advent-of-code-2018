package fr.insee.aoc.days;

import java.util.*;

import org.apache.commons.math3.complex.*;

public class Day13 implements Day {

	static final Map<Character, Complex> directions = Map.of(
		'<', Complex.ONE,
		'>', Complex.ONE.negate(),
		'v', Complex.I,
		'^', Complex.I.negate());

	static class Cart implements Comparable<Cart>{

		Complex direction;
		Complex position;
		Way way;
		boolean moving;
		
		Cart(char direction, int x, int y) {
			this.direction = directions.get(direction);
			this.position = Complex.valueOf(x, y);
			this.way = Way.LEFT;
			this.moving = true;
		}

		void moveForward() {
			position = position.add(direction);
		}
		
		void turnLeft() {
			direction = direction.multiply(Complex.I.negate());
		}

		void turnRight() {
			direction = direction.multiply(Complex.I);
		}
		
		void turn(char[][] track) {
			var re = (int) position.getReal();
			var im = (int) position.getImaginary();
			switch (track[im][re]) {
			case '/':
				if(re == 0) {
					turnRight();
				}
				else {
					turnLeft();
				}
				break;
			case '\\':
				if(re == 0) {
					turnLeft();
				}
				else {
					turnRight();
				}
				break;
			case '+':
				switch (way) {
				case LEFT:
					turnLeft();
					way = Way.STRAIGHT;
					break;
				case STRAIGHT:
					way = Way.RIGHT;
					break;
				case RIGHT:
					turnRight();
					way = Way.LEFT;
					break;
				}
				break;
			default:
				break;
			}
		}
		
		boolean collideWith(Cart other) {
			return this.position.equals(other.position);
		}
		
		boolean collideWith(List<Cart> carts) {
			return carts.stream().filter(cart -> cart != this && cart.moving).anyMatch(this::collideWith);
		}
		
		enum Way {
			LEFT, STRAIGHT, RIGHT
		}

		@Override
		public int compareTo(Cart other) {
			return (int) (this.direction.getImaginary() == other.direction.getImaginary()
				? this.direction.getReal() - other.direction.getReal()
				: this.direction.getImaginary() - other.direction.getImaginary());
		}

		@Override
		public int hashCode() {
			return direction.hashCode();
		}

		@Override
		public boolean equals(Object object) {
			if(object instanceof Cart) {
				Cart other = (Cart) object;
				return this.direction.equals(other.direction);
			}
			return false;
		}
		
		
	}
}
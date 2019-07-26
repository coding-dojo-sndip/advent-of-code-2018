package fr.insee.aoc.days;

import java.util.*;

import org.apache.commons.math3.complex.*;

import static fr.insee.aoc.utils.Days.*;

import static java.util.Comparator.*;

public class Day13 implements Day {

	static final Map<Character, Complex> directions = Map.of(
		'>', Complex.ONE,
		'v', Complex.I,
		'<', Complex.ONE.negate(),
		'^', Complex.I.negate());
	
	@Override
	public String part1(String input, Object... params) {
		var tracks = tracks(input);
		var carts = carts(tracks);
		while(true) {
			carts.sort(naturalOrder());
			for(var cart : carts) {
				cart.move(tracks);
				if(cart.collideWithAny(carts).isPresent()) {
					return String.format("%.0f,%.0f", cart.position.getReal(), cart.position.getImaginary());
				}
			}
		}
	}

	@Override
	public String part2(String input, Object... params) {
		var tracks = tracks(input);
		var carts = carts(tracks);
		while(carts.size() > 1) {
			carts.sort(naturalOrder());
			for(var cart : carts) {
				if(cart.moving) {
					cart.move(tracks);
					cart.collideWithAny(carts).ifPresent(other -> {
						other.moving = false;
						cart.moving = false;
					});
				}
			}
			carts.removeIf(cart -> !cart.moving);
		}
		var lastCart = carts.get(0);
		return String.format("%.0f,%.0f", lastCart.position.getReal(), lastCart.position.getImaginary());
	}

	static char[][] tracks(String input) {
		return tableOfChars(input);
	}
	
	static List<Cart> carts(char[][] tracks) {
		List<Cart> carts = new ArrayList<>();
		for(var i = 0; i < tracks.length; i++) {
			for(var j = 0; j < tracks[i].length; j++) {
				char dir = tracks[i][j];
				if(directions.containsKey(dir)) {
					carts.add(new Cart(dir, j, i));
				}
			}	
		}
		return carts;
	}
	
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

		void move(char[][] tracks) {
			forward();
			turn(tracks);
		}
		
		void forward() {
			position = position.add(direction);
		}
		
		void turnLeft() {
			direction = direction.multiply(Complex.I.negate());
		}

		void turnRight() {
			direction = direction.multiply(Complex.I);
		}
		
		void turn(char[][] tracks) {
			var x = (int) position.getReal();
			var y = (int) position.getImaginary();
			switch (tracks[y][x]) {
			case '/':
				if(direction.conjugate().equals(direction)) {
					turnLeft();
				}
				else {
					turnRight();
				}
				break;
			case '\\':
				if(direction.conjugate().equals(direction)) {
					turnRight();
				}
				else {
					turnLeft();
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
		
		Optional<Cart> collideWithAny(List<Cart> carts) {
			return carts.stream().filter(cart -> cart != this && cart.moving).filter(this::collideWith).findFirst();
		}
		
		enum Way {
			LEFT, STRAIGHT, RIGHT
		}

		@Override
		public int compareTo(Cart other) {
			return (int) (this.position.getImaginary() == other.position.getImaginary()
				? this.position.getReal() - other.position.getReal()
				: this.position.getImaginary() - other.position.getImaginary());
		}

		@Override
		public int hashCode() {
			return direction.hashCode();
		}

		@Override
		public boolean equals(Object object) {
			if(object instanceof Cart) {
				var other = (Cart) object;
				return this.direction.equals(other.direction);
			}
			return false;
		}
	}
}
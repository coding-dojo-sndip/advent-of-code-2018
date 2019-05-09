package fr.insee.aoc.days;

import static java.util.stream.Collectors.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.insee.aoc.utils.Point;

import static fr.insee.aoc.utils.Days.*;

public class Day10 implements Day {

	@Override
	public String part1(String input, Object... params) {
		// TODO Auto-generated method stub
		
		
		List<MobilePoint> points = streamOfLines(input).map(MobilePoint::new).collect(toList());
		
		while (true) {
			points.forEach(MobilePoint::move);
		}
	}
	
	static class MobilePoint extends Point {
		protected int vx, vy;
		private static final Pattern pattern = Pattern.compile("position=< *(-?\\d+), *(-?\\d+)> velocity=< *(-?\\d+), *(-?\\d+)>");
		
		
		public MobilePoint (String line) {
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				this.x = readInt(1, matcher);
				this.y = readInt(2, matcher);
				this.vx = readInt(3, matcher);
				this.vy = readInt(4, matcher);
			}
		}
				
		public void move() {
			this.x += this.vx;
			this.y += this.vy;
		}		
		
		public int getVx() {
			return vx;
		}

		public int getVy() {
			return vy;
		}
	}

}
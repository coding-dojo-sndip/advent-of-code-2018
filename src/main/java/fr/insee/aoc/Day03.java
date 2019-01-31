package fr.insee.aoc;

import static fr.insee.aoc.Days.streamOfLines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03 implements Day {

	private static final Pattern patternRectangle = Pattern.compile("#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)");



	@Override
	public String part1(String input) {
		AtomicInteger[][] tissu = new AtomicInteger[1000][1000];
		streamOfLines(input).forEach(r -> remplirTissu(r, tissu));
		long count = Arrays.stream(tissu).flatMap(Arrays::stream).filter(x -> x!=null && x.get()>1).count();
		return String.valueOf(count);

	}
	@Override
	public String part2(String input) {
		AtomicInteger[][] tissu = new AtomicInteger[1000][1000];
		return streamOfLines(input)
				.map(r -> remplirTissu(r, tissu)).collect(Collectors.toList()).stream()
				.filter(rectangle -> rectangle.verif())
				.findFirst().map(Rectangle::getId).orElseThrow(()->new DayException("Aucune demande ne correspond."));
	}

	private Rectangle  remplirTissu (String input, AtomicInteger[][] tissu) {
		Matcher matcher = patternRectangle.matcher(input);
		Rectangle rectangle = new Rectangle();
		if (matcher.matches()) {
			int left = 	Integer.parseInt(matcher.group(2));
			int top = Integer.parseInt(matcher.group(3));
			int width = Integer.parseInt(matcher.group(4));
			int height = Integer.parseInt(matcher.group(5));
			rectangle.setId(matcher.group(1));
			for (int i=left; i<left+width; i++) {
				for (int j=top; j<top+height; j++) {
					if (tissu[i][j]==null) {
						tissu[i][j] = new AtomicInteger(1);
					}else {
						tissu[i][j].incrementAndGet();
					}
					rectangle.getPoints().add(tissu[i][j]);
				}
			}
		}
		return rectangle;
	}


	/*#1 @ 1,3: 4x4
	#2 @ 3,1: 4x4
	#3 @ 5,5: 2x2*/

		static class Rectangle {
			private String id;
			private List<AtomicInteger> points = new ArrayList<>();
			
			public Rectangle() {
				super();
			}
	
			public Rectangle(String id, List<AtomicInteger> points) {
				super();
				this.id = id;
				this.points = points;
			}
			public String getId() {
				return id;
			}
			public void setId(String string) {
				this.id = string;
			}
			public List<AtomicInteger> getPoints() {
				return points;
			}
			public void setPoints(List<AtomicInteger> points) {
				this.points = points;
			}
			
			public boolean verif() {
				return points.stream().allMatch(point -> point.get() == 1);
			}
	
		}

}

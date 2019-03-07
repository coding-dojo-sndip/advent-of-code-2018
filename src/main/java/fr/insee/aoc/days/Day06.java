package fr.insee.aoc.days;

import static fr.insee.aoc.utils.Frame.*;
import static fr.insee.aoc.utils.Days.*;
import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;
import static java.util.function.Function.identity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.insee.aoc.utils.Frame;
import fr.insee.aoc.utils.Point;

import static fr.insee.aoc.utils.Collectors.*;

public class Day06 implements Day {
	
	private static Pattern pattern = Pattern.compile("(\\d+), (\\d+)");
	
	@Override
	public String part1(String input, Object... params) {
		Stream<String> coordsPoints = streamOfLines(input);
		List<Point> points = coordsPoints.map(coord -> transformCoorsInPoint(coord)).collect(Collectors.toList());
		Frame frame = smallestFrameContaining(points);
		
		Map<Point, Integer> surfaces =
				points.stream().collect(Collectors.toMap(identity(), p -> 0));		
		for(int i = frame.getLeft() ; i <= frame.getRight() ; i++) {
			for(int j = frame.getTop() ; j <= frame.getBottom() ; j++) {
				final Point point = Point.of(i, j);
				Map<Point, Integer> mapPointDistance =
						points.stream().collect(Collectors.toMap(identity(), p -> p.manhattan(point)));
				List<Entry<Point, Integer>> listOfMin = 
						mapPointDistance.entrySet().stream().collect(listOfMin(Comparator.comparingInt(e-> e.getValue())));
				if(listOfMin.size() == 1 && !frame.isOnTheEdge(point)) {
					Point pointClosest = listOfMin.get(0).getKey();
					surfaces.computeIfPresent(pointClosest, (k, v) -> v+1);
				} else if (frame.isOnTheEdge(point)) {
					surfaces.remove(point);
				}
			}
		}
		
		return String.valueOf(surfaces.entrySet().stream().mapToInt(e -> e.getValue()).max().orElse(-1));
	}
	
	private static Point transformCoorsInPoint(String coords) {
		Matcher matcher = pattern.matcher(coords);
		matcher.matches();
		return Point.of(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
	}


}

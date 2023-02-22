package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void test() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list.add(5);
		List<Integer>listSub = list.subList(6, 9);
		System.out.println(listSub);
		listSub.add(1, -2);//add -2 to index 1
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);
	}
	
	@Test
	@Disabled
	void displayOccurrenciesCount() {
		String[]strings = {"lmn", "abc", "abc", "lmn", "a", "lmn"};
		Arrays.stream(strings)
		.collect(Collectors.groupingBy(s-> s,Collectors.counting()))
		.entrySet().stream().sorted((e1,e2) -> Long.compare(e2.getValue(),e1.getValue()))
		.forEach(e-> System.out.printf("%s:%d\n", e.getKey(), e.getValue()));
		}
	
	//Generate 1_000_000 random numbers
	//display digits and counts of their occurrences in descending order of the counts	
	@Test
	void displayDigitStatistics() {
		new Random().ints(1, Integer.MAX_VALUE).distinct().limit(1000000)
		.boxed()
		.flatMap(num -> Arrays.stream(Integer.toString(num).split("")))
		.collect(Collectors.groupingBy(s-> s,Collectors.counting()))
		.entrySet().stream().sorted((e1,e2) -> Long.compare(e2.getValue(),e1.getValue()))
		.forEach(e-> System.out.printf("%s:%d\n", e.getKey(), e.getValue()));
	}
}

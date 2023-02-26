package telran.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
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
		List<Integer> listSub = list.subList(6, 9);
		System.out.println(listSub);
		listSub.add(1, -2);// add -2 to index 1
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);
	}

	@Test
	@Disabled
	void displayOccurrenciesCount() {
		String[] strings = { "lmn", "abc", "abc", "lmn", "a", "lmn" };
		Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s:%d\n", e.getKey(), e.getValue()));
	}

	// Generate 1_000_000 random numbers
	// display digits and counts of their occurrences in descending order of the
	// counts
	@Test
	@Disabled
	void displayDigitStatistics() {
		new Random().ints(1_000_000, 1, Integer.MAX_VALUE).boxed()
				.flatMap(num -> Arrays.stream(Integer.toString(num).split("")))
				.collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s:%d\n", e.getKey(), e.getValue()));
	}

	@Test
	void maxNumberWithNegativeImageTest() {
		int array[] = { 10000000, 3, -2, -200, 200, -3, 2 };
		int array1[] = { 10000000, -1000000000, 3, -4 };
		assertEquals(200, maxNumberWithNegativeImage(array));
		assertEquals(-1, maxNumberWithNegativeImage(array1));
	}

	// returns maximal positive number having it negative image or -1 if no such
	// numbers
	// O[N]
	int maxNumberWithNegativeImage(int[] array) {
		int res = -1;
		Set<Integer> set = new HashSet<>();
		for (Integer number : array) {
			if (set.isEmpty() || !set.contains(-number)) {
				set.add(number);
			} else if (set.contains(-number) && Math.abs(number) > res) {
				res = Math.abs(number);
			}
		}
		return res;
	}

	@Test
	void treeIteratingTest() {
		Integer array[] = { 1, 11, 111, 32, 9, 1234, 99, 992 };
		createAndIterateTreeInOrder(array);
	}

	// create tree, add in tree numbers from a given array
	// and iterate in the order of array defined in 69
	private void createAndIterateTreeInOrder(Integer[] array) {
		TreeSet<Integer> tree = new TreeSet<>((e1, e2) -> Integer.compare(sumOfDigits(e1), sumOfDigits(e2)));
		tree.addAll(Arrays.asList(array));
		System.out.println(tree);// for Checking
	}

	private int sumOfDigits(Integer num) {
		return num.toString().chars().map(Character::getNumericValue).sum();
	}
}
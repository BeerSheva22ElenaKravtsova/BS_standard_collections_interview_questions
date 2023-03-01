package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.MultiCountersAppl;

class MultiCountersTest {
	MultiCountersAppl counter;

	@BeforeEach
	void setUp() throws Exception {
		counter = new MultiCountersAppl();
	}

	@Test
	void testAddItem() {
		assertEquals(1, counter.addItem(1));
		assertEquals(2, counter.addItem(1));
		assertEquals(1, counter.addItem(-1));
	}

	@Test
	void testGetValue() {
		counter.addItem(1);
		assertEquals(1, counter.getValue(1));

		counter.addItem(1);
		assertEquals(2, counter.getValue(1));

		counter.addItem(-1);
		assertEquals(1, counter.getValue(-1));

		assertNull(counter.getValue(4));
	}

	@Test
	void testRemove() {
		counter.addItem(1);
		counter.addItem(1);
		assertTrue(counter.remove(1));
		assertNull(counter.getValue(1));
	}

	@Test
	void testGetMaxItems() {
		Integer[] arr = new Integer[] { 1, 100, 111111, -1, -9000, 111111, 1, 1 };
		for (Integer num : arr) {
			counter.addItem(num);
		}
		
		assertEquals(3, counter.getValue(1));

		HashSet<Object> exp = new HashSet<>(Arrays.asList(1));
		assertEquals(exp, counter.getMaxItems());

		counter.addItem(111111);
		exp.add(111111);
		assertEquals(exp, counter.getMaxItems());

		counter.remove(1);
		exp.remove(1);
		assertEquals(exp, counter.getMaxItems());
	}
}

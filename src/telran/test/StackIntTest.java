package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

class StackIntTest {
	StackInt stack;

	@BeforeEach
	void setUp() throws Exception {
		stack = new StackInt();
	}

	@Test
	void addTest() {
		assertTrue(stack.isEmpty());
		stack.push(10);
		assertFalse(stack.isEmpty());
	}

	@Test
	void popTest() {
		assertThrowsExactly(NoSuchElementException.class, () -> stack.pop());
		stack.push(10);
		stack.push(20);
		assertEquals(20, stack.pop());
		assertEquals(10, stack.pop());
		assertTrue(stack.isEmpty());
	}

	@Test
	void getMaxTest() {
		assertThrowsExactly(NoSuchElementException.class, () -> stack.getMax());
		stack.push(10);
		assertEquals(10, stack.getMax());
		stack.push(5);
		assertEquals(10, stack.getMax());
		stack.push(20);
		assertEquals(20, stack.getMax());
		stack.pop();
		assertEquals(10, stack.getMax());
	}

}
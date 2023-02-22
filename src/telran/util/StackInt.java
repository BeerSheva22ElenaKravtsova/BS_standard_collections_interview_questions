package telran.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class StackInt {
	LinkedList<Integer> list = new LinkedList<>();
	LinkedList<Integer> sortedList = new LinkedList<>();

	// add number into top of stack
	public void push(int number) {
		if (list.isEmpty() || number >= sortedList.getLast()) {
			sortedList.addLast(number);
		} else {
			sortedList.addLast((int) sortedList.getLast());
		}
		list.addLast(number);
	}

	// return a number from top of stack or throws NoSuchElementException
	// if the stack is empty
	public int pop() {
		if (!list.isEmpty()) {
			sortedList.removeLast();
			return list.removeLast();
		} else {
			throw new NoSuchElementException("Stack is empty");
		}
	}

	// returns true if the stack is empty otherwise false
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// returns maximal value of the stack or throws Exception (if empty)
	public int getMax() {
		if (!list.isEmpty()) {
			return sortedList.getLast();
		} else {
			throw new NoSuchElementException("Stack is empty");
		}
	}
}

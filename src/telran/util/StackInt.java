package telran.util;

import java.util.LinkedList;

public class StackInt {
	LinkedList<Integer> list = new LinkedList<>();
	LinkedList<Integer> sortedList = new LinkedList<>();

	// add number into top of stack
	public void push(int number) {
		if (list.isEmpty() || number >= sortedList.getLast()) {
			sortedList.addLast(number);
		}
		list.addLast(number);
	}

	// return a number from top of stack or throws NoSuchElementException
	// if the stack is empty
	public int pop() {
		if (list.getLast() == sortedList.getLast()) {
			sortedList.removeLast();
		}
		return list.removeLast();
	}

	// returns true if the stack is empty otherwise false
	public boolean isEmpty() {
		return list.isEmpty();
	}

	// returns maximal value of the stack or throws Exception (if empty)
	public int getMax() {
		return sortedList.getLast();
	}
}

package telran.util;

import java.util.HashMap;

public class MyArrayInt {
	protected int size;
	protected int value;
	HashMap<Integer, Integer> array;

	public MyArrayInt(int size, int value) {
		this.size = size;
		this.value = value;
	}

	// sets a given value at a given index
	// throws Exception IndexOutOfBounds() if index > size
	public void set(int index, int value){
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (array == null) {
			array = new HashMap<>();
		}
		array.put(index, value);
	}

	// returns a value at a given index
	public int get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		int res = value;
		if (array!= null) {
			res = array.getOrDefault(index, value);
		}
		return res;
	}

	public void setAll(int value) {
		this.value = value;
		array = null;
	}
}

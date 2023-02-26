package telran.structure;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class MultiCountersAppl implements MultiCounters {

	private Map<Object, Integer> hashMap = new HashMap<>();
	private TreeMap<Integer, HashSet<Object>> treeMap = new TreeMap<>();
	private Integer counter;

	@Override
	public Integer addItem(Object item) {
		counter = getCounter(getValue(item));
		hashMap.put(item, counter);
		addToTree(item, counter);
		return counter;
	}
	
	private Integer getCounter(Integer counter) {
		return counter == null ? 1 : ++counter;
	}

	private void addToTree(Object item, Integer counter) {
		if (counter - 1 != 0) {
			deleteSet(treeMap.get(counter - 1), item, counter - 1);
		}
		if (treeMap.containsKey(counter)) {
			treeMap.get(counter).add(item);
		} else {
			treeMap.put(counter, new HashSet<>(Arrays.asList(item)));
		}
	}

	private boolean deleteSet(HashSet<Object> set, Object item, Integer counter) {
		boolean res = false;
		if (set.remove(item)) {
			treeMap.put(counter, set);
			res = true;
		}
		return res;
	}

	@Override
	public Integer getValue(Object item) {
		return hashMap.get(item);
	}

	@Override
	public boolean remove(Object item) {
		return getValue(item) == null ? false
				: hashMap.remove(item, counter) && deleteSet(treeMap.get(counter), item, counter);
	}

	@Override
	public Set<Object> getMaxItems() {
		return treeMap.get(treeMap.lastKey());
	}
}
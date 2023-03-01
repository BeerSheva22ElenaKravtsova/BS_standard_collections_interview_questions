package telran.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import telran.structure.Words;

public class WordsAppl implements Words {
	private HashMap<String, Integer> hashMap = new HashMap<>();
	private TreeMap<Integer, LinkedList<String>> treeMap = new TreeMap<>();

	@Override
	public boolean addWord(String word) {
		boolean res = false;
		int length = word.length();
		if (!hashMap.entrySet().stream().anyMatch(entry -> entry.getKey().equalsIgnoreCase(word))) {
			hashMap.put(word, length);
			addToTree(length, word);
			res = true;
		}
		return res;
	}

	private void addToTree(int length, String word) {
		if (treeMap.containsKey(length)) {
			treeMap.get(length).add(word);
		} else {
			treeMap.put(length, new LinkedList<>(Arrays.asList(word)));
		}
	}

	@Override
	public List<String> getWordsByPrefix(String prefix) {
		LinkedList<String> res = new LinkedList<>();
		for (Entry<Integer, LinkedList<String>> entry : treeMap.entrySet()) {
			if (entry.getKey().compareTo(prefix.length()) > 0) {
				res.addAll(entry.getValue().stream().filter(str -> str.toLowerCase().startsWith(prefix.toLowerCase()))
						.toList());
			}
		}
		return res;
	}
}
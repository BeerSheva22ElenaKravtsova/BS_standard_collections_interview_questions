package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.WordsAppl;

class WordsTest {
	WordsAppl words;

	@BeforeEach
	void setUp() throws Exception {
		words = new WordsAppl();
	}

	@Test
	void testAddWord() {
		assertTrue(words.addWord("word"));
		assertFalse(words.addWord("Word"));
		assertTrue(words.addWord("wor"));
		assertTrue(words.addWord("words"));
	}

	@Test
	void testGetWordsByPrefix() {
		assertTrue(words.addWord("word"));
		assertFalse(words.addWord("Word"));
		assertTrue(words.addWord("Wor"));
		assertTrue(words.addWord("words"));
		assertTrue(words.addWord("ward"));
		
		List<String>expected = new LinkedList<>();
		expected.add("Wor");
		expected.add("word");
		expected.add("words");
		
		assertEquals(expected, words.getWordsByPrefix("wo"));
	}

}

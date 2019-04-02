
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
	private String[] myText;
	private Random myRandom;

	public MarkovWordOne() {
		myRandom = new Random();
	}

	/*
	 * public static void main(String[] args) { MarkovWordOne MO = new
	 * MarkovWordOne(); MO.testIndexOf(); }
	 */

	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	public void setTraining(String text) {
		myText = text.split("\\s+");
	}

	public String getRandomText(int numWords) {
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - 1); // random word to start with
		String key = myText[index];
		sb.append(key);
		sb.append(" ");
		for (int k = 0; k < numWords - 1; k++) {
			ArrayList<String> follows = getFollows(key);
			if (follows.size() == 0) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = next;
		}
		System.out.println(key + " key"); 
		System.out.println(follows); 
		

		return sb.toString().trim();
	}

	private ArrayList<String> getFollows(String key) {
		ArrayList<String> follows = new ArrayList<String>();
		for (int i = 0; i < myText.length; i++) {
			if (indexOf(myText, key, i) > 0) {
				follows.add(key);
			}
		}
		return follows;
	}

	private int indexOf(String[] words, String target, int start) {
		if (target.equals(words[start])) {
			System.out.println("yes " + words[start] + " at location " + start);
			return start;
		} else {
			System.out.println("No - 1");
			return -1;
		}
	}

	public void testIndexOf() {
		String[] sample = { "this", "is", "just", "a", "test", "yes", "this", "is", "a", "simple", "test" };
		indexOf(sample, "this", 0);
		indexOf(sample, "this", 3);
		indexOf(sample, "frog", 0);
		indexOf(sample, "frog", 5);
		indexOf(sample, "simple", 2);
		indexOf(sample, "test", 4);

	}

}

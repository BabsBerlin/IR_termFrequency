/*
 * WordCounter.java
 * 
 * 18.09.2019
 * 
 * by Barbara Hartmann
 */
package HamletIR;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {
	
	public void wordCount(String filename) throws FileNotFoundException {
		Map<String, Integer> words = new HashMap<String, Integer>();
		Scanner file = new Scanner(new File(filename));
		while (file.hasNext()) {
			String word = file.next();
			Integer count = words.get(word);
			if (count != null)
				count++;
			else
				count = 1;
			words.put(word, count);

		}

		file.close();
		
		// print hash map of counted terms to console
		System.out.println("word --> count");
		for (Map.Entry<String, Integer> entry : words.entrySet())  
            System.out.println(entry.getKey() + " --> " + entry.getValue()); 

	}

}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class TextAnalyzer {

	public static void main(String[] args) throws IOException {

		BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/dgato/OneDrive/Desktop/The Raven.txt"));
//path to text file
		
		Map<String, Integer> wordCounts = new HashMap<>();

		String line;

		while ((line = bufferedReader.readLine()) != null) {

			String[] words = line.split("[\\s.;,?:!()\"]+"); //line split

			// iterate all words
			for (String word : words) {

				word = word.trim();
				//if statement to increase word count
				if (word.length() > 0) {

					if (wordCounts.containsKey(word)) {
						wordCounts.put(word, wordCounts.get(word) + 1);
					} else {
						wordCounts.put(word, 1);
					}
				}
			}
		}

		
		Map<String, Integer> sortedWordCounts = wordCounts.entrySet().stream()
				.sorted(Collections.reverseOrder(Entry.comparingByValue()))
				.collect(Collectors.toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		// sort by frequency
		
		System.out.printf("%-20s%15s\n", "Word From Poem", "Frequency");

		System.out.printf("%-20s%15s\n", "--------------", "---------");

		for (Map.Entry<String, Integer> entry : sortedWordCounts.entrySet()) {

			System.out.printf("%-20s%10s\n", entry.getKey(), entry.getValue());
		}
		//output results 
		bufferedReader.close();
	}

}



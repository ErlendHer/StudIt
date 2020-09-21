package studit.core.chatbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeyboardLinker {
	
	private Map<String, String[]> keywords;
	private List<String> recognizedWords;
	
	public KeyboardLinker(Map<String, String[]> keywords) {
		this.keywords = keywords;
		extractKeywords();
	}
	
	/**
	 * Initializes recognizedWords list and adds to it all the words from the keywords hashmap.
	 */
	private void extractKeywords() {
		recognizedWords = new ArrayList<>();
		for (String[] command : keywords.values()) {
			for (String word : command) {
				recognizedWords.add(word);
			}
		}
	}
	
	public List<String> getRecognizedWords() {
		return recognizedWords;
	}

}

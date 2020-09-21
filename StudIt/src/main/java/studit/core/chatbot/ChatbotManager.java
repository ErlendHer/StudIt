package studit.core.chatbot;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ChatbotManager {
	
	private KeyboardLinker linker;
	
	public ChatbotManager() {
		writeDummyCommandsToDb();
		linker = new KeyboardLinker(loadJson("test.json"));
		System.out.println(linker.getRecognizedWords());
	}
	
	/**
	 * Manages the user entered input and executes commands accordingly.
	 * @param input - the user input to process
	 * @return chatbot response
	 */
	public String manageInput(String input) {
		// Splitting string by spaces, and removing all newline chars
		String[] command = input.replaceAll("\n", "").toLowerCase().split(" ");
		
		
		String response = "";
		
		for (String word : command) {
			if (word.contains("hei")) {
				response += "Hei. ";
				break;
			}
		}
		
		
		if (response.equals("")) {
			response += "Jeg beklager, men det forstod jeg ikke helt. Kanskje du mente...?";
		}
		return response;
		
	}
	
	/**
	 * Writes a list of dummy keyword connections to our json database
	 */
	private void writeDummyCommandsToDb() {
		
		Map<String, String[]> output = new HashMap<>();
		output.put("foo", new String[]{"random", "keywords", "here"
				+ ""});
		output.put("foo1", new String[]{"more", "random", "stuff"});
		output.put("foo2", new String[]{"you", "get", "the", "picture"});
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(Paths.get("src/main/resources/studit/db/test.json").toFile(), output);
		} catch (IOException e) {
			System.out.println("Error occured while printing dummy json to file");
			e.printStackTrace();
		}

	}
	
	/**
	 * Reads Json file on the format Map<String, String[]> and returns the list
	 * @param filename - Filename under resources/studit/db. E.g "test.json"
	 * @return Map<String, String[]> containing our values
	 */
	private Map<String, String[]> loadJson(String filename) {
		String path = "src/main/resources/studit/db/" + filename;
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			Map<String, String[]> keywords = mapper.readValue(Paths.get(path).toFile(), new TypeReference<Map<String, String[]>>(){});
			return keywords;
		} catch (IOException e) {
			System.out.println("Error occured while reading json '" + filename + "'.");
			e.printStackTrace();
		}
		return null;
	}

}

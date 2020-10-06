package studit.core.chatbot;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import studit.core.chatbot.KeywordLinker.Match;

public class ChatbotManager {

  private KeywordLinker linker;
  private CommandManager cmg;

  public ChatbotManager() {
    writeDummyCommandsToDb();
    linker = new KeywordLinker(loadJson("keywordLinks.json"));
    cmg = new CommandManager();
  }

  /**
   * Manages the user entered input and executes commands accordingly.
   * 
   * @param input - the user input to process
   * @return chatbot response
   */
  public String manageInput(String input) {
    // Splitting string by spaces, and removing all newline chars
    String[] command = input.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase().split(" ");


    String response = "";

    List<Match> matches = linker.matchCommand(command);
    int nextPrecedence = 1;

    for (Match match : matches) {
      if (match.precedence == nextPrecedence) {
        if (match.match > 0.5) {
          response += cmg.executeCommand(match.command);
        }
        nextPrecedence += 1;
      }
      System.out.println(match);
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

    List<KeywordLink> links = new ArrayList<>();


    links.add(new KeywordLink("hils",
        Map.of("hei", 1.0f, "hallo", 1.0f, "heisann", 1.0f, "hoi", 1.0f), 1));
    links.add(new KeywordLink("foo1", Map.of("more", 0.8f, "random", 0.4f, "stuff", 0.6f), 2));
    links.add(
        new KeywordLink("foo2", Map.of("you", 0.8f, "get", 0.4f, "the", 0.6f, "picture", 0.1f), 2));

    ObjectMapper mapper = new ObjectMapper();
    try {
      mapper.writeValue(Paths.get("src/main/resources/studit/db/keywordLinks.json").toFile(),
          links);
    } catch (IOException e) {
      System.out.println("Error occured while printing dummy json to file");
      e.printStackTrace();
    }

  }

  /**
   * Reads Json file on the format List<KeywordLink> and returns the list
   * 
   * @param filename - Filename under resources/studit/db. E.g "test.json"
   * @return ArrayList containing our keyword links
   */
  private List<KeywordLink> loadJson(String filename) {
    String path = "src/main/resources/studit/db/" + filename;

    ObjectMapper mapper = new ObjectMapper();
    try {
      List<KeywordLink> links =
          mapper.readValue(Paths.get(path).toFile(), new TypeReference<List<KeywordLink>>() {});
      return links;
    } catch (IOException e) {
      System.out.println("Error occured while reading json '" + filename + "'.");
      e.printStackTrace();
    }
    return null;
  }

}

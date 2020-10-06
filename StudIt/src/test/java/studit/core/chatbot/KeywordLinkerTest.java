package studit.core.chatbot;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studit.core.chatbot.KeywordLinker.Match;

public class KeywordLinkerTest {


  private KeywordLinker linker;
  private List<KeywordLink> dummyLinks;

  @BeforeEach
  public void init() {

    dummyLinks = new ArrayList<>();

    dummyLinks.add(new KeywordLink("hils", Map.of("hei", 1.0f, "hallo", 1.0f), 1));
    dummyLinks.add(new KeywordLink("hade", Map.of("hade", 0.8f), 2));

    linker = new KeywordLinker(dummyLinks);
  }

  @Test
  public void testGetRecognizedWords() {
    Map<Integer, String> words = linker.getRecognizedWords();
    List<String> recognizedWords = List.of("hei", "hallo", "hade");
  
    
    for (String word : words.values()) {
        if (!recognizedWords.contains(word)) {
          fail("Did not recognize '" + word + "'");
        }
    }
    
    
  }

  @Test
  public void testMatchCommand() {
    List<Match> match1 = linker.matchCommand(new String[] {"hei", "hva", "skjer"});
    List<Match> match2 = linker.matchCommand(new String[] {"hade", "hva", "skjer"});
    
    assertEquals(match1.get(0).match, 1.0f);
    assertEquals(match1.get(0).precedence, 1);
    
    assertEquals(match2.get(0).match, 0.0f);
    assertEquals(match2.get(1).precedence, 2);
    assertEquals(match2.get(1).match, 0.8f);
    
  }

}

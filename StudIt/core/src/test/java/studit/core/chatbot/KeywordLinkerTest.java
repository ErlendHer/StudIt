package studit.core.chatbot;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import studit.core.chatbot.KeywordLinker.Keyword;

public class KeywordLinkerTest {


  private KeywordLinker linker;
  private List<KeywordLink> dummyLinks;

  @BeforeEach
  public void init() {

    dummyLinks = new ArrayList<>();

    dummyLinks.add(new KeywordLink("hils", null, 1, List.of( Map.of("hei", 1.0f, "hallo", 0.8f))));
    dummyLinks.add(new KeywordLink("hade", null, 2, List.of( Map.of("hade", 0.8f, "adios", 0.2f, "når", 0.2f, "er", 0.1f, "eksamen", 0.7f))));

    linker = new KeywordLinker(dummyLinks);
  }

  @Test
  public void testGetRecognizedWords() {
    Map<Integer, String> words = linker.getRecognizedWords();
    List<String> recognizedWords = List.of("hei", "hallo", "hade", "adios", "når", "er", "eksamen", "eksamena");
  
    
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
    List<Match> match3 = linker.matchCommand(new String[] {"bror", "når", "er", "eksamen", "eksamena"});
    
    assertEquals(1.0f, match1.get(0).match);
    assertEquals(1.0f, match1.get(0).precedence);
    
    assertEquals(match2.get(0).match, 0.0f);
    assertEquals(match2.get(1).precedence, 2);
    assertEquals(match2.get(1).match, 0.8f);

    assertEquals(match3.get(1).match, 1.0f);
    
  }
  
  @Test
  public void testKeyword() {
    Keyword keyword = linker.new Keyword("hei", 0.5f);
    assertEquals("Keyword [ID=4, weight=0.5]", keyword.toString());
  }

}

package studit.core.chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KeywordLinkTest {
  
  private KeywordLink link;
  
  @BeforeEach
  public void init() {
    link = new KeywordLink("hils", Map.of("hei", 1.0f, "hallo", 0.8f), 1);
  }

  @Test
  public void testGetCommand() {
    assertEquals("hils", link.getCommand());
  }

  @Test
  public void testGetKeywords() {
    assertEquals(Map.of("hei", 1.0f, "hallo", 0.8f), link.getKeywords());
  }

  @Test
  public void testGetWords() {
    assertTrue(List.of("hei", "hallo").containsAll(link.getWords()));
  }

  @Test
  public void testGetPrecedence() {
    assertEquals(1, link.getPrecedence());
  }

}

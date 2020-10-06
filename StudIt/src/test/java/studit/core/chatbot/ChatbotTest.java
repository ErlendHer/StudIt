package studit.core.chatbot;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ChatbotTest {
  
  private ChatbotManager chatbotManager;

  @BeforeEach
  public void init() {
    chatbotManager = new ChatbotManager();
  }

  @Test
  public void testManageInput() {
    assertEquals(chatbotManager.manageInput("Hei!"), "Hei! ");
  }

}

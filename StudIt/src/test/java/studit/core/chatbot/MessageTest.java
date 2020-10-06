package studit.core.chatbot;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {

  private Message chatbotMessage;
  private Message userMessage;

  @BeforeEach
  public void init() {
    chatbotMessage = new Message("", "chatbot");
    userMessage = new Message("", "user");
  }

  @Test
  public void testGetText() {
    String testMsg = "test-message";
    String expectedMsg = "test-message ";
    chatbotMessage.setText(testMsg);
    userMessage.setText(testMsg);

    assertEquals(chatbotMessage.getText(), expectedMsg);
    assertEquals(userMessage.getText(), expectedMsg);
  }


  @Test
  public void testGetUser() {
    assertEquals(chatbotMessage.getUser(), "chatbot");
    assertEquals(userMessage.getUser(), "user");
  }

}

package studit.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import studit.core.chatbot.ChatbotManager;

public class ChatbotManagerTest {
	
	private ChatbotManager chatbotManager;
	
	@BeforeEach
	public void init() {
		chatbotManager = new ChatbotManager();
	}

	@Test
	public void testManageInput() {
		assertEquals(chatbotManager.manageInput("Hei!"), "Hei. ");
	}

}

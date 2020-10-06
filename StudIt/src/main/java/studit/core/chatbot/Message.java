package studit.core.chatbot;

import studit.ui.ChatbotController;

public class Message {

  private String text;
  private String user;

  public Message(String text, String user) {
    this.text = text;
    this.user = user;
  }

  /**
   * Returns formatted text with correct line breaks
   * 
   * @return String ready to be printed to the screen
   */
  public String getText() {

    String[] words = text.replace("\n", "").split(" ");

    String line = "", output = "";

    for (String word : words) {
      if (line.length() + word.length() > ChatbotController.lineBreakLength - 8) {
        output += line + '\n';
        line = word + " ";
      } else {
        line += word + " ";
      }
    }

    output += line;

    return output;

  }

  public void setText(String text) {
    this.text = text;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }
}

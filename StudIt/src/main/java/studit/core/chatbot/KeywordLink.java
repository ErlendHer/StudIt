package studit.core.chatbot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KeywordLink {

  private String command;
  private Map<String, Float> keywords;
  private int precedence;

  public KeywordLink() {

  }

  public KeywordLink(String command, Map<String, Float> keywords, int precedence) {
    this.command = command;
    this.keywords = keywords;
    this.setPrecedence(precedence);
  }

  public String getCommand() {
    return command;
  }

  public void setCommand(String command) {
    this.command = command;
  }

  public Map<String, Float> getKeywords() {
    return keywords;
  }

  public void setKeywords(Map<String, Float> keywords) {
    this.keywords = keywords;
  }

  public List<String> getWords() {
    return new ArrayList<String>(keywords.keySet());
  }

  public int getPrecedence() {
    return precedence;
  }

  public void setPrecedence(int precedence) {
    this.precedence = precedence;
  }

}

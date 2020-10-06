package studit.core.chatbot;

public class CommandManager {

  public CommandManager() {

  }

  public String executeCommand(String match) {
    String response = "";

    System.out.println(match);

    switch (match) {
      case "hils":
        response += "Hei! ";
        break;
      default:
        response += "Oops, internal error -> unrecognized command";
    }
    System.out.println("Response: " + response);
    return response;
  }

}

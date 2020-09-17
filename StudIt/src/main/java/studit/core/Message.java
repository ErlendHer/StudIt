package studit.core;

import studit.ui.ChatbotController;

public class Message {
	
	private String text;
    private String user;

    public Message(String text, String user) {
        this.text = text;
        this.user = user;
    }
    
    /*
     * Returns formatted text with correct line breaks
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
    	
    	
    	/*
    	String output = "";
    	for (int i = 0; i < text.length(); i++) {
    		output += text.charAt(i);
    		if (i % (ChatbotController.lineBreakLength - 8) == 0 && i != 0) {
    			output += "\n";
    		}
    	} */
    	

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

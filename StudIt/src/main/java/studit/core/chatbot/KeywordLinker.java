package studit.core.chatbot;

import static studit.core.Commons.contains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class KeywordLinker {

  private List<KeywordLink> links;
  private BiMap<Integer, String> recognizedWords;
  private Map<String, Keyword[]> commandIDs;
  private Map<String, Integer> precedences;

  public KeywordLinker(List<KeywordLink> links) {
    this.links = links;
    extractKeywords();

  }

  /**
   * Initializes recognizedWords and commandIDs
   */
  private void extractKeywords() {
    recognizedWords = HashBiMap.create();
    commandIDs = new HashMap<>();
    precedences = new HashMap<>();

    Set<String> uniqueWords = new HashSet<>();

    for (KeywordLink link : links) {
      uniqueWords.addAll(link.getWords());
      precedences.put(link.getCommand(), link.getPrecedence());
    }

    int id = 0;

    for (String word : uniqueWords) {
      recognizedWords.put(id, word);
      id += 1;
    }

    for (KeywordLink link : links) {
      commandIDs.put(link.getCommand(), getIDArray(link.getKeywords()));
    }

  }

  /**
   * Finds the word id's in a string array
   * 
   * @param weightedKeywords HashMap containing our words and weights
   * @return Keyword[] of weights and IDs
   */
  private Keyword[] getIDArray(Map<String, Float> weightedKeywords) {

    Keyword[] keywords = new Keyword[weightedKeywords.size()];


    int i = 0;
    for (String word : weightedKeywords.keySet()) {

      keywords[i] = new Keyword(word, weightedKeywords.get(word));
      i += 1;
    }

    return keywords;
  }

  public Map<Integer, String> getRecognizedWords() {
    return recognizedWords;
  }

  /**
   * Iterates over recognized words in our vocabulary and finds the closest match
   * 
   * @param word the word we want to match
   * @return id if match is over 65% confident, -1 if no match found
   */
  private int getKeywordID(String word) {

    int matchID = -1;
    float matchPct = 0.0f;

    for (Integer key : recognizedWords.keySet()) {
      int unions = 0, complements = 0;
      for (int i = 0; i < word.length(); i++) {
        char c = word.charAt(i);

        if (recognizedWords.get(key).indexOf(c) >= 0) {
          unions++;
        } else {
          complements++;
        }
      }

      float pct = (float) (unions - complements) / (unions + complements);
      if (pct >= 0.65f && pct > matchPct) {
        matchID = key;
        matchPct = pct;
      }
    }

    // System.out.println(recognizedWords.get(matchID));
    return matchID;

  }

  public List<Match> matchCommand(String[] words) {

    Integer[] matchIDs = new Integer[words.length];

    for (int i = 0; i < words.length; i++) {
      matchIDs[i] = getKeywordID(words[i]);
    }

    List<Match> matches = new ArrayList<>();

    for (String key : commandIDs.keySet()) {
      float match = 0.0f;
      for (Keyword keyword : commandIDs.get(key)) {
        if (contains(matchIDs, keyword.ID)) {
          match += keyword.weight;
        }
      }

      matches.add(new Match(key, match, precedences.get(key)));

    }

    Collections.sort(matches);
    return matches;
  }

  class Keyword {
    public int ID;
    public float weight;

    public Keyword(String word, float weight) {
      ID = recognizedWords.inverse().get(word);
      this.weight = weight;
    }

    @Override
    public String toString() {
      return "Keyword [ID=" + ID + ", weight=" + weight + "]";
    }
  }

  class Match implements Comparable<Match> {
    public float match;
    public String command;
    public int precedence;

    public Match(String command, float match, int precedence) {
      // super();
      this.match = match;
      this.command = command;
      this.precedence = precedence;
    }

    @Override
    public int compareTo(Match o) {
      if (o.precedence < this.precedence)
        return 1;
      return o.match < this.match ? -1 : 1;
    }

    @Override
    public String toString() {
      return "Match [match=" + match + ", command=" + command + ", precedence=" + precedence + "]";
    }

  }


}


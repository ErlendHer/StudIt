package studit.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import studit.core.mainpage.CourseItem;

import java.io.IOException;

public class CourseItemDeserializer extends JsonDeserializer<CourseItem> {

  /**
   * Deserialiezes a CourseItem. 
   *
   */
  @Override
  public CourseItem deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    return deserialize((JsonNode) treeNode);
  }

  CourseItem deserialize(JsonNode jsonNode) {
    if (jsonNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) jsonNode;
      CourseItem item = new CourseItem();

      JsonNode codeNode = objectNode.get("code");
      if (codeNode instanceof TextNode) {
        item.setFagkode(((TextNode) codeNode).asText());
      }

      JsonNode nameNode = objectNode.get("name");
      if (nameNode instanceof TextNode) {
        item.setFagnavn(((TextNode) nameNode).asText());
      }

      JsonNode rateNode = objectNode.get("rate");
      if (rateNode instanceof TextNode) {
        item.setScore(((TextNode) rateNode).asText());
      }

      JsonNode kommentarNode = objectNode.get("kommentar");
      if (kommentarNode instanceof TextNode) {
        item.setKommentar(((TextNode) kommentarNode).asText());
      }

      return item;

    }

    return null;
  }
}

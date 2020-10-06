package studit.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import studit.core.mainpage.CourseItem;
import studit.core.mainpage.CourseList;

import java.io.IOException;


class CourseListDeserializer extends JsonDeserializer<CourseList> {

  private CourseItemDeserializer courseItemDeserializer = new CourseItemDeserializer();
  /*
   * format: { "items": [ ... ] }
   */

  @Override
  public CourseList deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    TreeNode treeNode = parser.getCodec().readTree(parser);
    if (treeNode instanceof ObjectNode) {
      ObjectNode objectNode = (ObjectNode) treeNode;
      CourseList list = new CourseList();
      JsonNode itemsNode = objectNode.get("items");
      if (itemsNode instanceof ArrayNode) {
        for (JsonNode elementNode : ((ArrayNode) itemsNode)) {
          CourseItem item = courseItemDeserializer.deserialize(elementNode);
          if (item != null) {
            list.addCourseItem(item);
          }
        }
      }
      return list;
    }
    return null;
  }   
}

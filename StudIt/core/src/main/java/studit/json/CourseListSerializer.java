package studit.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import studit.core.mainpage.CourseItem;
import studit.core.mainpage.CourseList;

public class CourseListSerializer extends JsonSerializer<CourseList> {

  /*
   * format: { "items": [ ... ] }
   */

   /**
   * Serializes a courselist
   *
   * @param list the course list you want to serialize
   */
  @Override
  public void serialize(CourseList list, JsonGenerator jsonGen, SerializerProvider serializerProvider)
      throws IOException {
    jsonGen.writeStartObject();
    jsonGen.writeArrayFieldStart("items");
    for (CourseItem item : list) {
      jsonGen.writeObject(item);
    }
    jsonGen.writeEndArray();
    jsonGen.writeEndObject();
  }
}

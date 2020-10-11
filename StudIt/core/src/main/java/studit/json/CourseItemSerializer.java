package studit.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import studit.core.mainpage.CourseItem;

public class CourseItemSerializer extends JsonSerializer<CourseItem> {

  
   /**
   * Serializes a courseitem
   *
   * @param item the item you want to serialize
   */
  @Override
  public void serialize(CourseItem item, JsonGenerator jsonGen, SerializerProvider serializerProvider)
      throws IOException {

    jsonGen.writeStartObject();

    jsonGen.writeStringField("code", item.getFagkode());
    jsonGen.writeStringField("name", item.getFagnavn());
    jsonGen.writeStringField("rate", item.getScore());
    jsonGen.writeStringField("kommentar", item.getKommentar());

    jsonGen.writeEndObject();

  }
}

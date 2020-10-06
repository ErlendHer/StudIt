package studit.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import studit.core.mainpage.CourseItem;
import studit.core.mainpage.CourseList;


@SuppressWarnings("serial")
public class CourseModule extends SimpleModule {

  private static final String NAME = "CourseModule";

  /**
   * Initializes this CourseModule with appropriate serializers and deserializers.
   */
  public CourseModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(CourseItem.class, new CourseItemSerializer());
    addSerializer(CourseList.class, new CourseListSerializer());
    addDeserializer(CourseItem.class, new CourseItemDeserializer());
    addDeserializer(CourseList.class, new CourseListDeserializer());
  }
}

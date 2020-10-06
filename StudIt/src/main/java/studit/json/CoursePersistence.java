package studit.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import com.fasterxml.jackson.databind.ObjectMapper;

import studit.core.mainpage.CourseList;
import studit.core.mainpage.CourseModel;

public class CoursePersistence {

  private ObjectMapper mapper;

  public CoursePersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new CourseModule());
  }

  public CourseList readCourseList(Reader reader) throws IOException {
    return mapper.readValue(reader, CourseList.class);
  }

  public void writeCourseList(CourseList courseList, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, courseList);
  }


}

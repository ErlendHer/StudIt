package studit.json;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import com.fasterxml.jackson.databind.ObjectMapper;

import studit.core.mainpage.CourseList;

public class CoursePersistence {

  private ObjectMapper mapper;

  public CoursePersistence() {
    mapper = new ObjectMapper();
    mapper.registerModule(new CourseModule());
  }

  /**
   * Returns a CourseList from the file provided in the argument.
   *
   * @param Reader usually a FileReader to read from a file
   */
  public CourseList readCourseList(Reader reader) throws IOException {
    return mapper.readValue(reader, CourseList.class);
  }

  /**
   * Provides the course list from the argument to a file.
   *
   * @param courseList the course list you want to write to a file.
   */
  public void writeCourseList(CourseList courseList, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, courseList);
  }



}

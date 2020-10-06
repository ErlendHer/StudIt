package studit.core.mainpage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CourseModel implements Iterable<CourseList> {

  private Collection<CourseList> courseLists = new ArrayList<>();

  public void addTodoList(CourseList list) {
    courseLists.add(list);
  }

  public void removeTodoList(CourseList list) {
    courseLists.remove(list);
  }

  @Override
  public Iterator<CourseList> iterator() {
    return courseLists.iterator();
  }
}

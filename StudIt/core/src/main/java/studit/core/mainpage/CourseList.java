package studit.core.mainpage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class CourseList implements Iterable<CourseItem> {

  private List<CourseItem> items = new ArrayList<>();

  public CourseItem createCourseItem() {
    return new CourseListItem(this);
  }

  /**
   * Adds the provided CourseItem to this CourseList. If the CourseItem is not an
   * instance of CourseListItem, its contents is copied in to a new CourseListItem
   * and that is added instead.
   *
   * @param item the CourseItem to add
   */
  public void addCourseItem(CourseItem item) {
    CourseListItem CourseListItem = null;
    if (item instanceof CourseListItem) {
      CourseListItem = (CourseListItem) item;
    } else {
      CourseListItem = new CourseListItem(this);
      CourseListItem.setFagkode(item.getFagkode());
      CourseListItem.setFagnavn(item.getFagnavn());
      CourseListItem.setKommentar(item.getKommentar());
      CourseListItem.setScore(item.getScore());
    }
    items.add(CourseListItem);
  }

  public void removeCourseItem(CourseItem item) {
    items.remove(item);
  }

  @Override
  public Iterator<CourseItem> iterator() {
    return items.iterator();
  }

  public Collection<CourseItem> getCourseItems() {
    return items;
  }



}

package studit.core.mainpage;

public class CourseListItem extends CourseItem {

  private final CourseList courseList;

  public CourseListItem(CourseList courseList) {
    this.courseList = courseList;
  }

  public CourseList getCourseList(){
      return this.courseList;
  }

}


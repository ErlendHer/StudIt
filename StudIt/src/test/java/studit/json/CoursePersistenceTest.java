// package studit.json;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import java.io.StringWriter;
// import java.util.Map;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
// import static org.junit.jupiter.api.Assertions.fail;
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;

// import studit.core.mainpage.CourseItem;
// import studit.core.mainpage.CourseList;
// import studit.core.mainpage.CourseModel;

// public class CoursePersistenceTest{


//     private CoursePersistence coursePersistence = new CoursePersistence();

//     @BeforeAll
//     public static void setUp() {

//         CourseModel model = new CourseModel();

//         CourseList list = new CourseList();
        
//         CourseItem item1 = new CourseItem();
//         item1.setFagkode("TDT4109");
//         item1.setFagnavn("ITGK for grovinger");
//         item1.setKommentar("Schpa fag");
//         item1.setScore("10");
//         list.addCourseItem(item1);

//         CourseItem item2 = new CourseItem();
//         item2.setFagkode("TMA4125");
//         item2.setFagnavn("Statistikk");
//         item2.setKommentar("Kult fag");
//         item2.setScore("8");
//         list.addCourseItem(item2);

//     }


//     @Test
//     public void testSerializersDeserializers(){
    
//         try {
//             StringWriter writer = new StringWriter();
//             coursePersistence.writeCourseModel(model, writer);
//             String json = writer.toString();
//             TodoModel model2 = todoPersistence.readTodoModel(new StringReader(json));
//             assertTrue(model2.iterator().hasNext());
//             TodoList list2 = model.iterator().next();
//             assertEquals("todo", list.getName());
//             Iterator<TodoItem> it = list2.iterator();
//             assertTrue(it.hasNext());
//             TodoModuleTest.checkTodoItem(it.next(), item1);
//             assertTrue(it.hasNext());
//             TodoModuleTest.checkTodoItem(it.next(), item2);
//             assertFalse(it.hasNext());
//         } catch (IOException e) {
//             fail();
//         }



// }
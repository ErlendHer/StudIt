package studit.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static studit.core.Commons.contains;

public class CommonsTest {

  @Test
  public void testContains() {
    Integer[] test1 = new Integer[] {1, 4, 5, 6, 8};
    String[] test2 = new String[] {"hei", "foo", "foo"};
    Float[] test3 = new Float[] {};
    
    class Foo {
      @SuppressWarnings("unused")
      private int a;
    }
    
    Foo foo = new Foo();
    
    Foo[] test4 = new Foo[] {foo, new Foo()};
    
    assertTrue(contains(test1, 4));
    assertFalse(contains(test1, 2));
    
    assertTrue(contains(test2, "hei"));
    assertFalse(contains(test2, ""));
    
    assertFalse(contains(test3, null));
    assertFalse(contains(test3, 0));
    
    assertTrue(contains(test4, foo));
    assertFalse(contains(test4, new Foo()));
    
    
  }

}

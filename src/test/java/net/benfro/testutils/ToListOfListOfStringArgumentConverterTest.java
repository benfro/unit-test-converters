package net.benfro.testutils;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ToListOfListOfStringArgumentConverterTest {

   private ToListOfListOfStringArgumentConverter instance;
   private List<List<String>> fixture;

   @BeforeEach
   void setUp() {
      instance = new ToListOfListOfStringArgumentConverter();
      fixture = Lists.newArrayList();
   }

   @Test
   void testConvertOne() {
      fixture.add(Lists.newArrayList("foo"));
      assertEquals(Lists.newArrayList(fixture), instance.convert("[[foo]]", String.class));
   }

   @Test
   void testConvertTwo() {
      fixture.add(Lists.newArrayList("foo", "bar"));
      assertEquals(fixture, instance.convert("[[foo, bar]]", String.class));
   }

   @Test
   void testConvertOnePlusOne() {
      fixture.addAll(Lists.newArrayList(Lists.newArrayList("foo"), Lists.newArrayList("bar")));
      assertEquals(fixture, instance.convert("[[foo], [bar]]", String.class));
   }

   @Test
   void testConvertOnePlusOneWithSpaces() {
      fixture.addAll(Lists.newArrayList(Lists.newArrayList("foo "), Lists.newArrayList(" bar")));
      assertEquals(fixture, instance.convert("[['foo '], [' bar']]", String.class));
   }

   @Test
   void testConvertTwoPlusTwo() {
      fixture.addAll(Lists.newArrayList(Lists.newArrayList("foo", "bar"), Lists.newArrayList("baz, qux")));
      assertEquals(fixture, instance.convert("[[foo, bar], ['baz, qux']]", String.class));
   }


}
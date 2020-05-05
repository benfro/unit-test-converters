package net.benfro.testutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ToListOfStringArgumentConverterTest {

   private ToListOfStringArgumentConverter instance;

   @BeforeEach
   void setUp() {
      instance = new ToListOfStringArgumentConverter();
   }

   @Test
   void testNormalListOneEntry() {
      assertEquals(Lists.newArrayList("foo"), instance.convert("[foo]", String.class));
   }

   @Test
   void testNormalList() {
      assertEquals(Lists.newArrayList("foo", "bar"), instance.convert("[foo, bar]", String.class));
   }

   @Test
   void testListWithComma() {
      assertEquals(Lists.newArrayList("foo", "baz, qux"), instance.convert("[foo, 'baz, qux']", String.class));
   }

   @Test
   void testListWithCommaAndSpace() {
      assertEquals(Lists.newArrayList("foo", " baz, qux "), instance.convert("[foo, ' baz, qux ']", String.class));
   }

   @Test
   void testListWithEmptyString() {
      assertEquals(Lists.newArrayList("foo", ""), instance.convert("[foo, '']", String.class));
   }

   @Test
   void testListWithNullValue() {
      assertEquals(Lists.newArrayList("foo", null), instance.convert("[foo, ]", String.class));
   }

   @Test
   void testEmptyList() {
      assertEquals(Lists.newArrayList(), instance.convert("[]", String.class));
   }

}
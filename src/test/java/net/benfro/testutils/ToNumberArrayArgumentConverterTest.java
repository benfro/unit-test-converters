package net.benfro.testutils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;



class ToNumberArrayArgumentConverterTest {

   private ToNumberArrayArgumentConverter instance;

   @BeforeEach
   void setUp() {
      instance = new ToNumberArrayArgumentConverter();
   }

   @Test
   void testNormal() {
      assertArrayEquals(Lists.newArrayList(2.0f, 3.0f, 4.0f).toArray(new Number[0]), (Number[])instance.convert("[2.0, 3.0, 4.0]", String.class));
      assertArrayEquals(Lists.newArrayList(2.0, 3.0, 4.0).toArray(new Number[0]), (Number[])instance.convert("[2.0d, 3.0d, 4.0d]", String.class));
      assertArrayEquals(Lists.newArrayList(2, 3, 4).toArray(new Number[0]), (Number[])instance.convert("[2, 3, 4]", String.class));
   }

   @Test
   void testThrows() {
      ArgumentConversionException msg = assertThrows(
               ArgumentConversionException.class,
               () -> instance.convert("[2, 3, apa]", String.class)
      );
      assertEquals("Could not convert 'apa'", msg.getMessage());
   }
}
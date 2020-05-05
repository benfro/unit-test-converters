package net.benfro.testutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;



class ToNumberArgumentConverterTest {

   @Test
   void testNormal() {
      ToNumberArgumentConverter instance = new ToNumberArgumentConverter();
      assertEquals(2.0, instance.convert("2.0d", String.class));
      assertEquals(2.0f, instance.convert("2.0", String.class));
      assertEquals(2.0f, instance.convert("2.0f", String.class));
      assertEquals(2, instance.convert("2", String.class));
   }

   @Test
   void testThrows() {
      ToNumberArgumentConverter instance = new ToNumberArgumentConverter();
      assertThrows(
               ArgumentConversionException.class,
               () -> instance.convert("apa", String.class)
      );
   }
}
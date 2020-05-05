package net.benfro.testutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;



class CharArgumentConverterTest {

   private CharArgumentConverter instance;

   @BeforeEach
   void setUp() {
      instance = new CharArgumentConverter();
   }

   @Test
   void testThrowsWhenWrongClass() {
      assertThrows (
               ArgumentConversionException.class,
               () -> instance.convert("s", String.class)
      );
   }

   @Test
   void testConvert() {
      assertEquals('s', instance.convert("s", Character.class));
   }

   @Test
   void testConvertNotTrimmed() {
      assertEquals('s', instance.convert(" s", Character.class));
      assertEquals('s', instance.convert("s ", Character.class));
      assertEquals('s', instance.convert(" s ", Character.class));
   }

   @Test
   void testThrowsWhenLongerThanOne() {
      assertThrows (
               ArgumentConversionException.class,
               () -> instance.convert("ss", Character.class)
      );
   }

   @Test
   void testSpecials() {
      //assertEquals(' ', instance.convert("' '", Character.class));
      assertEquals('\t', instance.convert("\t", Character.class));
      assertEquals('\n', instance.convert("\n", Character.class));
      // Horizontal tab, Unicode
      assertEquals('\t', instance.convert("\u0009", Character.class));
   }
}
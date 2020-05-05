package net.benfro.testutils;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;



class ToNumberListArgumentConverterTest {

   private ToNumberListArgumentConverter instance;

   @BeforeEach
   void setUp() {
      instance = new ToNumberListArgumentConverter();
   }

   @Test
   void testNormal() {
      assertEquals(Lists.newArrayList(2.0f, 3.0f, 4.0f), instance.convert("[2.0, 3.0, 4.0]", String.class));
      assertEquals(Lists.newArrayList(2.0, 3.0, 4.0), instance.convert("[2.0d, 3.0d, 4.0d]", String.class));
      assertEquals(Lists.newArrayList(2, 3, 4), instance.convert("[2, 3, 4]", String.class));
   }

   @Test
   void testThrows() {
      ArgumentConversionException msg = assertThrows(
               ArgumentConversionException.class,
               () -> instance.convert("[2, 3, apa]", String.class)
      );
      assertEquals("Could not convert 'apa'", msg.getMessage());
   }

   @Test
   void testNumberUtilBehavior() {
      assertTrue(NumberUtils.createNumber("0d") instanceof BigDecimal);
      //assertTrue(NumberUtils.createNumber("0.0d") instanceof BigDecimal);
   }
}
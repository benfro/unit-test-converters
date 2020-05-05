package net.benfro.testutils;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;



public class ToNumberArgumentConverter extends SimpleArgumentConverter {
   /**
    * For use in a @CsvSource. Convert a string to a Number
    * @param input 2.0 and 2.0f => Float, 2.0 => Double and 2 => Integer
    * @param aClass No significance here
    * @return A Number from a string in @CsvSource
    * @throws ArgumentConversionException
    */
   @Override
   protected Object convert(Object input, Class<?> aClass) throws ArgumentConversionException {
      String instr = (String) input;
      try {
         return NumberUtils.createNumber(instr);
      } catch (NumberFormatException e) {
         throw new ArgumentConversionException("Could not convert '" + instr + "'", e);
      }
   }
}
package net.benfro.testutils;

import java.util.List;
import org.junit.jupiter.params.converter.ArgumentConversionException;



public class ToNumberArrayArgumentConverter extends ToNumberListArgumentConverter {
   /**
    * For use in a @CsvSource. Convert a string on the form [x, y, z] to a Number array
    * 2.0 and 2.0f => Float, 2.0 => Double and 2 => Integer
    * @param input Example: Argument in the form String '[1.0, 2.0]'
    * @return A Number[] from the string argument
    * @throws ArgumentConversionException
    */
   @Override
   protected Object convert(Object input, Class<?> aClass) throws ArgumentConversionException{
      return ((List<Number>)super.convert(input, aClass)).toArray(new Number[0]);
   }
}
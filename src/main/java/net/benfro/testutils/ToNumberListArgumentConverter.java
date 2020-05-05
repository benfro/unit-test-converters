package net.benfro.testutils;

import java.util.List;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;



public class ToNumberListArgumentConverter extends SimpleArgumentConverter {

   /**
    * For use in a @CsvSource. Convert a string on the form [x, y, z] to a Number array
    * 2.0 and 2.0f => Float, 2.0 => Double and 2 => Integer
    * @param input Example: Argument in the form String '[1.0, 2.0]'
    * @return A Number[] from the string argument
    * @throws ArgumentConversionException
    */
   @Override
   protected Object convert(Object input, Class<?> aClass) throws ArgumentConversionException {
      List<Number> numbers = Lists.newArrayList();
      String source = (String) input;
      source = source.replace("[", "").replace("]", "").trim();
      String[] split = source.split(",");

      for (String s : split) {
         try {
            numbers.add(NumberUtils.createNumber(s.trim()));
         } catch (NumberFormatException e) {
            throw new ArgumentConversionException("Could not convert '" + s.trim() + "'", e);
         }
      }

      return numbers;
   }
}

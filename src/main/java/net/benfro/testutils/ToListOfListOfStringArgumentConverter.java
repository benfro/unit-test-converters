package net.benfro.testutils;

import java.util.List;
import com.google.common.collect.Lists;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;



/**
 * Allows a user to define a list of list of strings in the format '[[foo], [bar]]'
 * See tests for usage tips
 */
public class ToListOfListOfStringArgumentConverter extends SimpleArgumentConverter {

   private final static WellFormedCharacterValidator VALIDATOR = new WellFormedCharacterValidator('[', ']');

   @Override
   protected Object convert(Object input, Class<?> aClass) throws ArgumentConversionException {

      String indata = ((String) input).trim();
      if (!VALIDATOR.isBalanced(indata)) {
         throw new ArgumentConversionException("Expression is not well balanced");
      }
      indata = removeFirstAndLastBrackets(indata);

      final String regexpThatSplitsOnCommasOutsideSquareBrackets = ",(?=(((?!\\]).)*\\[)|[^\\[\\]]*$)";

      ToListOfStringArgumentConverter oneListArgumentConverter = new ToListOfStringArgumentConverter();

      List<List<String>> result = Lists.newArrayList();

      for (String s : indata.split(regexpThatSplitsOnCommasOutsideSquareBrackets)) {
         result.add(oneListArgumentConverter.convert(s, String.class));
      }

      return result;
   }

   private String removeFirstAndLastBrackets(String indata) {
      indata = indata.replaceFirst("\\[", "");
      indata = indata.substring(0, indata.lastIndexOf(']'));
      return indata;
   }

}

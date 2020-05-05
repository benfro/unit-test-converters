package net.benfro.testutils;

import java.util.List;
import java.util.Map;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;



public class ToMapOfStringToListOfStringArgumentConverter extends SimpleArgumentConverter {
   private final static WellFormedCharacterValidator VALIDATOR = new WellFormedCharacterValidator('[', ']');
   private final static ToListOfStringArgumentConverter LIST_ARGUMENT_CONVERTER = new ToListOfStringArgumentConverter();
   private final static List<String> LIST_OF_STRINGS = Lists.newArrayList();
   private final static String REG_EXP_THAT_MATCHES_ON_COMMAS_OUTSIDE_BRACKETS = ",(?![^\\[\\]]*(?:\\[[^\\[\\]]*\\])?\\])";

   protected Object convert(Object input, Class<?> aClass) throws ArgumentConversionException {
      String indata = ((String) input).trim();
      if (!VALIDATOR.isBalanced(indata)) {
         throw new ArgumentConversionException("Expression is not well balanced");
      }
      indata = removeFirstAndLastBrackets(indata);

      Map<String, List<String>> result = Maps.newHashMap();
      if (indata.isEmpty()) {
         return result;
      }
      String[] mapEntries = indata.split(REG_EXP_THAT_MATCHES_ON_COMMAS_OUTSIDE_BRACKETS);
      for (String entry : mapEntries) {
         String[] splitEntry = entry.split(":");
         result.put(splitEntry[0].trim(), LIST_ARGUMENT_CONVERTER.convert(splitEntry[1].trim(), LIST_OF_STRINGS.getClass()));
      }
      return result;
   }

   private String removeFirstAndLastBrackets(String indata) {
      indata = indata.replaceFirst("\\[", "");
      indata = indata.substring(0, indata.lastIndexOf(']'));
      return indata;
   }
}

package net.benfro.testutils;

import java.util.List;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;



/**
 * Allow users to defina lists in the form '[foo, bar]' in CsvSurce tags. Behaves as the normal CSV Source string.
 * Please refer to tests to check usage.
 *
 * https://stackoverflow.com/questions/34257547/split-string-on-comma-and-ignore-comma-in-double-quotes
 */
public class ToListOfStringArgumentConverter extends SimpleArgumentConverter  {
   protected List<String> convert(Object o, Class<?> aClass) throws ArgumentConversionException {
      String indata = ((String) o).trim();
      List<String> output = Lists.newArrayList();
      indata = indata.replace("[", "").replace("]", "").trim();
      if (indata.isEmpty()) {
         return output;
      }
      String regexpThatSplitsOnCommasOutsideQuotes = ",(?=(?:[^']*'[^']*')*[^']*$)";
      Splitter splitter = Splitter.onPattern(regexpThatSplitsOnCommasOutsideQuotes);
      for (String s : splitter.splitToList(indata)) {
         if (s.isEmpty()) {
            output.add(null);
         } else {
            output.add(trimAndRemoveCitations(s));
         }
      }

      return output;
   }

   private String trimAndRemoveCitations(String str) {
      str = str.trim();
      return str.replace("'", "");
   }
}

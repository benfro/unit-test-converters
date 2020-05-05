package net.benfro.testutils;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;



/**
 * Supports characters in CsvSources. Unicode characters supported!
 * NB: blank step and octal representations not supported!
 */
public class CharArgumentConverter extends SimpleArgumentConverter {
   @Override
   protected Object convert(Object o, Class<?> aClass) throws ArgumentConversionException {
      if (!aClass.equals(Character.class)) {
         throw new ArgumentConversionException("Must be a character");
      }
      String incoming = (String) o;
      incoming = StringUtils.replace(incoming, " ", "");
      if (incoming.length() > 1 && !incoming.startsWith("\\")) {
         throw new ArgumentConversionException("Indata too long, must be a character");
      }
      return convertStringToCharacter(incoming);
   }

   /**
    * https://community.oracle.com/thread/2048619
    */
   private Character convertStringToCharacter(String str) {
      char[] strArr = str.toCharArray();
      boolean escape = false;
      Character out = null;
      if (strArr[0] == '\\') {
         escape = true;
      } else {
         out = strArr[0];
      }
      if (escape) {
         for (int i = 1; i < strArr.length; ++i) {
            if (strArr[i] == 'b') {
               out = '\b';
            } else if (strArr[i] == 't') {
               out = '\t';
            } else if (strArr[i] == 'n') {
               out = '\n';
            } else if (strArr[i] == 'r') {
               out = '\r';
            } else if (strArr[i] == 'f') {
               out = '\f';
            } else if (strArr[i] == 'u') {
               // Unicode escape
               int utf = Integer.parseInt(str.substring(i + 1, i + 5), 16);
               out = (char) utf;
            }
         }
      }
      return out;
   }
}

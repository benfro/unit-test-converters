package net.benfro.testutils;

import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.converter.ArgumentConversionException;



class ToMapOfStringToListOfStringArgumentConverterTest {

   private ToMapOfStringToListOfStringArgumentConverter instance;
   private Map<String, List<String>> theMap;

   @BeforeEach
   void setUp() {
      instance = new ToMapOfStringToListOfStringArgumentConverter();
      theMap = Maps.newHashMap();
   }

   @Test
   void testSimple() {
      assertEquals(theMap, instance.convert("[]", theMap.getClass()));
   }

   @Test
   void testOneEntry() {
      theMap.put("0", Lists.newArrayList("1"));
      assertEquals(theMap, instance.convert("[0:[1]]", theMap.getClass()));
   }

   @Test
   void testTwoEntries() {
      theMap.put("0", Lists.newArrayList("1"));
      theMap.put("2", Lists.newArrayList("3"));
      assertEquals(theMap, instance.convert("[0:[1], 2:[3]]", theMap.getClass()));
   }

   @Test
   void testOneEntryLongerList() {
      theMap.put("0", Lists.newArrayList("1", "2"));
      assertEquals(theMap, instance.convert("[0:[1, 2]]", theMap.getClass()));
   }

   @Test
   void testTwoEntriesMuchLongerLists() {
      theMap.put("0", Lists.newArrayList("1", "2", "3", "4", "5"));
      theMap.put("6", Lists.newArrayList("7", "8", "9", "10"));
      assertEquals(theMap, instance.convert("[0 :   [1,2 , 3, 4,5], 6 : [7,8,9   ,  10]]", theMap.getClass()));
   }

   @Test
   void testThrowsOnNotWellFormed() {
      assertThrows(
               ArgumentConversionException.class,
               () -> instance.convert("[0:[1,2], 1: [4,5]", Map.class)
      );
   }
}
package net.benfro.testutils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;



class WellFormedCharacterValidatorTest {

   private WellFormedCharacterValidator instance;

   @BeforeEach
   void setUp() {
      instance = new WellFormedCharacterValidator('[', ']');
   }

   @ParameterizedTest
   @ValueSource(strings = {"", "[]", "[0:[1]]", "[0:[1], 1:[3,4,5,6,7]]"})
   void testShouldBeBalanced(String candidate) {
      assertTrue(instance.isBalanced(candidate));
   }

   @ParameterizedTest
   @ValueSource(strings = {"[", "[[]]]", "[[[][]]", "[0:1]]", "[ [ ]", "[[]][", "][[]]"})
   void testShouldNotBeBalanced(String candidate) {
      assertFalse(instance.isBalanced(candidate));
   }

   @Test
   void testMatematicallyWellBalacedButNotConceptually() {
      assertFalse(instance.isBalanced("[][]"));
   }
}
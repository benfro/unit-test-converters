package net.benfro.testutils;

import java.util.EmptyStackException;
import java.util.Stack;



public class WellFormedCharacterValidator {

   private final char startCharacter;
   private final char endCharacter;
   private int counter = 0;

   public WellFormedCharacterValidator(char startCharacter, char endCharacter) {
      this.startCharacter = startCharacter;
      this.endCharacter = endCharacter;
   }

   public boolean isBalanced(String instr) {
      counter = 0;
      final int indataLength = instr.length();
      final Stack<Integer> trackStack = new Stack<>();
      try {
         instr.chars().forEach(c -> pushOrPop(trackStack, (char) c, indataLength));
      } catch (EmptyStackException e) {
         return false;
      }
      return trackStack.isEmpty();
   }

   private void pushOrPop(Stack<Integer> stack, char candidate, int indataLength) {
      if (candidate == startCharacter) {
         stack.push((int)candidate);
      } else if (candidate == endCharacter) {
         stack.pop();
         if (stack.isEmpty() && counter < indataLength - 1) {
            throw new EmptyStackException();
         }
      }
      counter++;
   }
}

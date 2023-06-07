package net.benfro.testutils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UsageTest {
    @ParameterizedTest
    @CsvSource(value = {
         "[apa, banan, äpple] | 13"
    }, delimiter = '|')
    void testListOfStringsUsage(@ConvertWith(ToListOfStringArgumentConverter.class)List<String> in, int expected) {
        assertEquals(expected, sumOfCharactersInListOfStrings(in));
    }

    @ParameterizedTest
    @CsvSource(value = {
            "[[apa, banan], [äpple, skrott]] | 19"
    }, delimiter = '|')
    void testListOfListOfStringsUsage(@ConvertWith(ToListOfListOfStringArgumentConverter.class)List<List<String>> in, int expected) {
        assertEquals(expected, sumOfCharactersInListOfListOfStrings(in));
    }

    static int sumOfCharactersInListOfStrings(List<String> in) {
        return in.stream()
                .mapToInt(String::length)
                .sum();
    }

    static int sumOfCharactersInListOfListOfStrings(List<List<String>> in) {
        return in.stream().
                flatMapToInt(i -> i.stream().mapToInt(String::length)).sum();
    }
}

package com.demoqa.utils;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class MyArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                arguments("Kristina Tomash","test@gmail.com","TelAviv"),
                arguments("Lev Tomash","test1@gmail.com","TelAviv"),
                arguments("Ivan Tomash","test2@gmail.com","TelAviv")
        );
    }
}

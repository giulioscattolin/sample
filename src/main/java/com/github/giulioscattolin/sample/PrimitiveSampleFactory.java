package com.github.giulioscattolin.sample;

public class PrimitiveSampleFactory {
    public static Sample toSample(int value) {
        return new ImmutableIntegerSample(value);
    }
}

package com.github.giulioscattolin.sample;

public interface IntegerSample extends Sample {
    int getValue();

    default void accept(SampleVisitor visitor) {
        visitor.visit(getValue());
    }
}

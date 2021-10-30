package com.github.giulioscattolin.sample;

public interface SampleVisitor {
    default void visit(CompositeSample compositeSample) {
    }

    default void visit(KeyValueSample keyValueSample) {
    }

    default void visit(int value) {
    }
}

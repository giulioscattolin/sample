package com.github.giulioscattolin.sample;

import java.util.List;

public interface CompositeSample extends Sample {
    List<Sample> getSamples();

    default void accept(SampleVisitor visitor) {
        visitor.visit(this);
    }

    CompositeSampleBuilder toBuilder();
}

package com.github.giulioscattolin.sample;

public interface CompositeSampleBuilder {
    CompositeSampleBuilder include(Sample sample);

    CompositeSampleBuilder exclude(Sample sample);

    CompositeSample build();

    CompositeSampleBuilder clear();
}

package com.github.giulioscattolin.sample;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

public class MutableSampleListBuilder implements CompositeSampleBuilder {
    private final List<Sample> itsSamples;
    private final UnaryOperator<List<Sample>> itsDuplicator;

    private MutableSampleListBuilder(UnaryOperator<List<Sample>> duplicator, List<Sample> samples) {
        itsSamples = samples;
        itsDuplicator = duplicator;
    }

    public static CompositeSampleBuilder toCompositeSampleBuilder(UnaryOperator<List<Sample>> duplicator, List<Sample> samples) {
        return new MutableSampleListBuilder(duplicator, samples);
    }

    public static CompositeSampleBuilder toCompositeSampleBuilder(UnaryOperator<List<Sample>> duplicator) {
        return new MutableSampleListBuilder(duplicator, new LinkedList<>());
    }

    public CompositeSampleBuilder include(Sample sample) {
        itsSamples.add(sample);
        return this;
    }

    public CompositeSampleBuilder exclude(Sample sample) {
        itsSamples.remove(sample);
        return this;
    }

    public CompositeSample build() {
        return new ImmutableSampleList(itsDuplicator, itsSamples);
    }

    public CompositeSampleBuilder clear() {
        itsSamples.clear();
        return this;
    }
}

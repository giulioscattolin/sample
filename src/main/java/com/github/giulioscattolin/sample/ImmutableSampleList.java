package com.github.giulioscattolin.sample;

import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;

import static com.github.giulioscattolin.sample.MutableSampleListBuilder.toCompositeSampleBuilder;

class ImmutableSampleList implements CompositeSample {
    private final UnaryOperator<List<Sample>> itsDuplicator;
    private final List<Sample> itsSamples;

    ImmutableSampleList(UnaryOperator<List<Sample>> duplicator, List<Sample> samples) {
        itsSamples = duplicator.apply(samples);
        itsDuplicator = duplicator;
    }

    public List<Sample> getSamples() {
        return itsSamples;
    }

    public CompositeSampleBuilder toBuilder() {
        return toCompositeSampleBuilder(itsDuplicator, itsDuplicator.apply(itsSamples));
    }

    public String toString() {
        return itsSamples.toString();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableSampleList that = (ImmutableSampleList) o;
        return itsSamples.equals(that.itsSamples);
    }

    public int hashCode() {
        return Objects.hash(itsSamples);
    }
}

package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;
import static com.google.common.truth.Truth.assertThat;
import static java.lang.Double.NaN;

public class MutableSingleMapKeyValueSampleTest implements SampleVisitor {
    KeyValueSampleBuilder itsBuilder;
    KeyValueSample itsSample;

    @Test
    public void verifyVisitor() {
        build();

        itsSample.accept(this);

        assertThat(itsSample).isNotNull();
    }

    @Test
    public void givenEmptyBuilder_thenHasObjectReturnsFalse() {
        build();

        assertThat(itsSample.hasObject("Person")).isFalse();
    }

    @Test
    public void givenEmptyBuilder_thenHasDoubleReturnsFalse() {
        build();

        assertThat(itsSample.hasDouble("Weight")).isFalse();
    }

    @Test
    public void givenEmptyBuilder_thenHasIntegerReturnsFalse() {
        build();

        assertThat(itsSample.hasInteger("Age")).isFalse();
    }

    @Test
    public void givenEmptyBuilder_thenHasLongReturnsFalse() {
        build();

        assertThat(itsSample.hasLong("Age")).isFalse();
    }

    @Test
    public void testObjectInsertion() {
        itsBuilder.insert("Name", "Giulio");

        build();

        assertThat(itsSample.hasObject("Name")).isTrue();
        assertThat(itsSample.getObject("Name")).isEqualTo("Giulio");
    }

    @Test
    public void testIntegerInsertion() {
        itsBuilder.insert("Age", 27);

        build();

        assertThat(itsSample.hasObject("Age")).isTrue();
        assertThat(itsSample.getInteger("Age")).isEqualTo(27);
    }

    @Test
    public void testDoubleInsertion() {
        itsBuilder.insert("Weight", NaN);

        build();

        assertThat(itsSample.hasObject("Weight")).isTrue();
        assertThat(itsSample.getDouble("Weight")).isEqualTo(NaN);
    }

    @Test
    public void testObjectRemoval() {
        itsBuilder.insert("Weight", NaN);
        itsBuilder.remove("Weight");

        build();

        assertThat(itsSample.hasObject("Weight")).isFalse();
    }

    @Test
    public void whenSampleIsBuilt_thenStateIsNotShared() {
        itsBuilder.insert("Location", "Italy");
        build();

        itsBuilder.insert("Location", "Venezuela");

        assertThat(itsSample.getObject("Location")).isEqualTo("Italy");
    }

    @Test
    public void whenBuilderIsCreated_thenStateIsNotShared() {
        itsBuilder.insert("Location", "Italy");
        build();
        itsBuilder = itsSample.toBuilder();

        itsBuilder.insert("Location", "Venezuela");

        assertThat(itsSample.getObject("Location")).isEqualTo("Italy");
    }

    @Before
    public void setup() {
        itsBuilder = toKeyValueSampleBuilder(HashMap::new);
        itsSample = null;
    }

    public void visit(KeyValueSample keyValueSample) {
        itsSample = keyValueSample;
    }

    private void build() {
        itsSample = itsBuilder.build();
    }
}
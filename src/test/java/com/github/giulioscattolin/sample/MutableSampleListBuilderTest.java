package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static com.github.giulioscattolin.sample.MutableSampleListBuilder.toCompositeSampleBuilder;
import static com.google.common.truth.Truth.assertThat;

public class MutableSampleListBuilderTest {
    CompositeSampleBuilder itsBuilder;
    CompositeSample itsSample;
    Sample itsSampleDummy;

    @Before
    public void clean() {
        itsBuilder = toCompositeSampleBuilder(LinkedList::new);
        itsSampleDummy = new SampleDummy();
    }

    @Test
    public void givenEmptyBuilder_thenSampleListIsEmpty() {
        build();

        assertThat(itsSample.getSamples()).isEmpty();
    }

    @Test
    public void givenSampleIsIncluded_whenSampleIsBuilt_thenSampleListIsNonEmpty() {
        itsBuilder.include(itsSampleDummy);

        build();

        assertThat(itsSample.getSamples()).hasSize(1);
    }

    @Test
    public void givenSampleIsIncludedAndExcluded_whenSampleIsBuilt_thenSampleListIsEmpty() {
        itsBuilder.include(itsSampleDummy);
        itsBuilder.exclude(itsSampleDummy);

        build();

        assertThat(itsSample.getSamples()).isEmpty();
    }

    @Test
    public void givenSampleIsIncludedBeforeSampleIsBuilt_thenStateIsNotShared() {
        itsBuilder.include(itsSampleDummy);
        build();
        itsBuilder.exclude(itsSampleDummy);

        assertThat(itsSample.getSamples()).isNotEmpty();
    }

    @Test
    public void givenSampleIsIncludedAfterSampleIsBuilt_thenStateIsNotShared() {
        build();
        itsBuilder.include(itsSampleDummy);

        assertThat(itsSample.getSamples()).isEmpty();
    }

    private void build() {
        itsSample = itsBuilder.build();
    }
}
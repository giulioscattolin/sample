package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;
import static com.google.common.truth.Truth.assertThat;

public class LongKeyValueAssociationTest {
    KeyValueSampleBuilder itsSampleBuilder;

    @Test
    public void verifyThatEmptySampleDoesNotIncludeAttribute() {
        assertThat(Age.isIncludedIn(itsSampleBuilder.build())).isFalse();
    }

    @Test
    public void givenAttributeHasBeenIncluded_verifyAttributeIsIncludedInSample() {
        Age.include(itsSampleBuilder, Long.MAX_VALUE);

        assertThat(Age.isIncludedIn(itsSampleBuilder.build())).isTrue();
        assertThat(Age.get(itsSampleBuilder.build())).isEqualTo(Long.MAX_VALUE);
    }

    @Before
    public void clean() {
        itsSampleBuilder = toKeyValueSampleBuilder(HashMap::new);
    }

    private static class Age extends LongKeyValueAssociation {
        private static final Age itsInstance = new Age();

        public static long get(Sample sample) {
            return itsInstance.getValue(sample);
        }

        public static void include(KeyValueSampleBuilder builder, long value) {
            itsInstance.includeValue(builder, value);
        }

        public static boolean isIncludedIn(Sample sample) {
            return itsInstance.isValueIncludedIn(sample);
        }

        public String getKey() {
            return "Age";
        }
    }
}
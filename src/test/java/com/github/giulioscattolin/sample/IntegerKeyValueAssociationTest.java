package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;
import static com.google.common.truth.Truth.assertThat;

public class IntegerKeyValueAssociationTest {
    KeyValueSampleBuilder itsSampleBuilder;

    @Test
    public void verifyThatEmptySampleDoesNotIncludeAttribute() {
        assertThat(Age.isIncludedIn(itsSampleBuilder.build())).isFalse();
    }

    @Test
    public void givenAttributeHasBeenIncluded_verifyAttributeIsIncludedInSample() {
        Age.include(itsSampleBuilder, 27);

        assertThat(Age.isIncludedIn(itsSampleBuilder.build())).isTrue();
        assertThat(Age.get(itsSampleBuilder.build())).isEqualTo(27);
    }

    @Before
    public void clean() {
        itsSampleBuilder = toKeyValueSampleBuilder(HashMap::new);
    }

    private static class Age extends IntegerKeyValueAssociation {
        private static final Age itsInstance = new Age();

        public static int get(Sample sample) {
            return itsInstance.getValue(sample);
        }

        public static void include(KeyValueSampleBuilder builder, int value) {
            itsInstance.includeValue(builder, value);
        }

        public static boolean isIncludedIn(Sample sample) {
            return itsInstance.isValueIncludedIn(sample);
        }

        public Object getKey() {
            return "Age";
        }
    }
}
package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;
import static com.google.common.truth.Truth.assertThat;

public class BooleanKeyValueAssociationTest {
    KeyValueSampleBuilder itsSampleBuilder;

    @Test
    public void verifyThatEmptySampleDoesNotIncludeAttribute() {
        assertThat(IsCompliant.isIncludedIn(itsSampleBuilder.build())).isFalse();
    }

    @Test
    public void givenAttributeHasBeenIncluded_verifyAttributeIsIncludedInSample() {
        IsCompliant.include(itsSampleBuilder, true);

        assertThat(IsCompliant.isIncludedIn(itsSampleBuilder.build())).isTrue();
        assertThat(IsCompliant.get(itsSampleBuilder.build())).isEqualTo(true);
    }

    @Before
    public void clean() {
        itsSampleBuilder = toKeyValueSampleBuilder(HashMap::new);
    }

    private static class IsCompliant extends BooleanKeyValueAssociation {
        private static final IsCompliant itsInstance = new IsCompliant();

        public static boolean get(Sample sample) {
            return itsInstance.getValue(sample);
        }

        public static void include(KeyValueSampleBuilder builder, boolean value) {
            itsInstance.includeValue(builder, value);
        }

        public static boolean isIncludedIn(Sample sample) {
            return itsInstance.isValueIncludedIn(sample);
        }

        public String getKey() {
            return "IsCompliant";
        }
    }
}
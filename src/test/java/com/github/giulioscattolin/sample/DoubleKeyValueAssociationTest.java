package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;
import static com.google.common.truth.Truth.assertThat;

public class DoubleKeyValueAssociationTest {
    KeyValueSampleBuilder itsSampleBuilder;

    @Test
    public void verifyThatEmptySampleDoesNotIncludeAttribute() {
        assertThat(Temperature.isIncludedIn(itsSampleBuilder.build())).isFalse();
    }

    @Test
    public void givenAttributeHasBeenIncluded_verifyAttributeIsIncludedInSample() {
        Temperature.include(itsSampleBuilder, 24.3);

        assertThat(Temperature.isIncludedIn(itsSampleBuilder.build())).isTrue();
        assertThat(Temperature.get(itsSampleBuilder.build())).isEqualTo(24.3);
    }

    @Before
    public void clean() {
        itsSampleBuilder = toKeyValueSampleBuilder(HashMap::new);
    }

    private static class Temperature extends DoubleKeyValueAssociation {
        private static final Temperature itsInstance = new Temperature();

        public static double get(Sample sample) {
            return itsInstance.getValue(sample);
        }

        public static void include(KeyValueSampleBuilder builder, double value) {
            itsInstance.includeValue(builder, value);
        }

        public static boolean isIncludedIn(Sample sample) {
            return itsInstance.isValueIncludedIn(sample);
        }

        public Object getKey() {
            return "Temperature";
        }
    }
}
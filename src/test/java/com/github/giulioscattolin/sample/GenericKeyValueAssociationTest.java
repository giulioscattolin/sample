package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashMap;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;
import static com.google.common.truth.Truth.assertThat;

public class GenericKeyValueAssociationTest {
    KeyValueSampleBuilder itsSampleBuilder;

    @Test
    public void verifyThatEmptySampleDoesNotIncludeAttribute() {
        assertThat(Birthday.isIncludedIn(itsSampleBuilder.build())).isFalse();
    }

    @Test
    public void givenAttributeHasBeenIncluded_verifyAttributeIsIncludedInSample() {
        Birthday.include(itsSampleBuilder, LocalDate.of(1994, 3, 5));

        assertThat(Birthday.isIncludedIn(itsSampleBuilder.build())).isTrue();
        assertThat(Birthday.get(itsSampleBuilder.build())).isEqualTo(LocalDate.of(1994, 3, 5));
    }

    @Before
    public void clean() {
        itsSampleBuilder = toKeyValueSampleBuilder(HashMap::new);
    }

    private static class Birthday extends GenericKeyValueAssociation<LocalDate> {
        private static final Birthday itsInstance = new Birthday();

        public static LocalDate get(Sample sample) {
            return itsInstance.getValue(sample);
        }

        public static void include(KeyValueSampleBuilder builder, LocalDate value) {
            itsInstance.includeValue(builder, value);
        }

        public static boolean isIncludedIn(Sample sample) {
            return itsInstance.isValueIncludedIn(sample);
        }

        public Object getKey() {
            return "Birthday";
        }

        public Class<LocalDate> getValueClass() {
            return LocalDate.class;
        }
    }
}
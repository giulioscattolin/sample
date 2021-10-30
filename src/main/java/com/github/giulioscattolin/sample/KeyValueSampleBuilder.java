package com.github.giulioscattolin.sample;

public interface KeyValueSampleBuilder {
    KeyValueSample build();

    KeyValueSampleBuilder insert(Object key, int value);

    KeyValueSampleBuilder insert(Object key, long value);

    KeyValueSampleBuilder insert(Object key, double value);

    KeyValueSampleBuilder insert(Object key, Object value);

    KeyValueSampleBuilder remove(Object key);
}

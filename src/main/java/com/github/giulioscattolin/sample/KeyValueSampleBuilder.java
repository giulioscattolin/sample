package com.github.giulioscattolin.sample;

public interface KeyValueSampleBuilder {
    KeyValueSample build();

    KeyValueSampleBuilder insert(String key, int value);

    KeyValueSampleBuilder insert(String key, long value);

    KeyValueSampleBuilder insert(String key, double value);

    KeyValueSampleBuilder insert(String key, Object value);

    KeyValueSampleBuilder remove(String key);
}

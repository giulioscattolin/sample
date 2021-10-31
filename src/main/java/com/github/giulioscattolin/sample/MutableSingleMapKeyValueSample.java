package com.github.giulioscattolin.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class MutableSingleMapKeyValueSample implements KeyValueSampleBuilder {
    private final Map<String, Object> itsMap;
    private final UnaryOperator<Map<String, Object>> itsMapDuplicator;

    private MutableSingleMapKeyValueSample(UnaryOperator<Map<String, Object>> duplicator, Map<String, Object> initialMap) {
        itsMap = initialMap;
        itsMapDuplicator = duplicator;
    }

    public static KeyValueSampleBuilder toKeyValueSampleBuilder(UnaryOperator<Map<String, Object>> duplicator) {
        return toKeyValueSampleBuilder(duplicator, new HashMap<>());
    }

    public static KeyValueSampleBuilder toKeyValueSampleBuilder(UnaryOperator<Map<String, Object>> duplicator, Map<String, Object> map) {
        return new MutableSingleMapKeyValueSample(duplicator, map);
    }

    public KeyValueSample build() {
        return new ImmutableSingleMapKeyValueSample(itsMapDuplicator, itsMapDuplicator.apply(itsMap));
    }

    public KeyValueSampleBuilder insert(String key, int value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder insert(String key, long value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder insert(String key, double value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder insert(String key, Object value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder remove(String key) {
        itsMap.remove(key);
        return this;
    }
}

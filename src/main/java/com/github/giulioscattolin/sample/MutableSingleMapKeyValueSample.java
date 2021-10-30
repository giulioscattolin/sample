package com.github.giulioscattolin.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

public class MutableSingleMapKeyValueSample implements KeyValueSampleBuilder {
    private final Map<Object, Object> itsMap;
    private final UnaryOperator<Map<Object, Object>> itsMapDuplicator;

    private MutableSingleMapKeyValueSample(UnaryOperator<Map<Object, Object>> duplicator, Map<Object, Object> initialMap) {
        itsMap = initialMap;
        itsMapDuplicator = duplicator;
    }

    public static KeyValueSampleBuilder toKeyValueSampleBuilder(UnaryOperator<Map<Object, Object>> duplicator) {
        return toKeyValueSampleBuilder(duplicator, new HashMap<>());
    }

    public static KeyValueSampleBuilder toKeyValueSampleBuilder(UnaryOperator<Map<Object, Object>> duplicator, Map<Object, Object> map) {
        return new MutableSingleMapKeyValueSample(duplicator, map);
    }

    public KeyValueSample build() {
        return new ImmutableSingleMapKeyValueSample(itsMapDuplicator, itsMapDuplicator.apply(itsMap));
    }

    public KeyValueSampleBuilder insert(Object key, int value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder insert(Object key, long value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder insert(Object key, double value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder insert(Object key, Object value) {
        itsMap.put(key, value);
        return this;
    }

    public KeyValueSampleBuilder remove(Object key) {
        itsMap.remove(key);
        return this;
    }
}

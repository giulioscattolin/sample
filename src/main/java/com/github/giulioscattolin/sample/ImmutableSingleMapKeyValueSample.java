package com.github.giulioscattolin.sample;

import java.util.Map;
import java.util.function.UnaryOperator;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;

class ImmutableSingleMapKeyValueSample implements KeyValueSample {
    private final Map<String, Object> itsMap;
    private final UnaryOperator<Map<String, Object>> itsDuplicator;

    ImmutableSingleMapKeyValueSample(UnaryOperator<Map<String, Object>> duplicator, Map<String, Object> initialMap) {
        itsMap = initialMap;
        itsDuplicator = duplicator;
    }

    public KeyValueSampleBuilder toBuilder() {
        return toKeyValueSampleBuilder(itsDuplicator, itsDuplicator.apply(itsMap));
    }

    public boolean hasBoolean(String key) {
        return hasObject(key) && getObject(key) instanceof Boolean;
    }

    public boolean hasInteger(String key) {
        return hasObject(key) && getObject(key) instanceof Integer;
    }

    public boolean hasLong(String key) {
        return hasObject(key) && getObject(key) instanceof Long;
    }

    public boolean hasDouble(String key) {
        return hasObject(key) && getObject(key) instanceof Double;
    }

    public boolean hasObject(String key) {
        return itsMap.containsKey(key);
    }

    public boolean getBoolean(String key) {
        return (boolean) getObject(key);
    }

    public int getInteger(String key) {
        return (int) getObject(key);
    }

    public long getLong(String key) {
        return (long) getObject(key);
    }

    public double getDouble(String key) {
        return (double) getObject(key);
    }

    public Object getObject(String key) {
        return itsMap.get(key);
    }
}

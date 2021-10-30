package com.github.giulioscattolin.sample;

import java.util.Map;
import java.util.function.UnaryOperator;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;

class ImmutableSingleMapKeyValueSample implements KeyValueSample {
    private final Map<Object, Object> itsMap;
    private final UnaryOperator<Map<Object, Object>> itsDuplicator;

    ImmutableSingleMapKeyValueSample(UnaryOperator<Map<Object, Object>> duplicator, Map<Object, Object> initialMap) {
        itsMap = initialMap;
        itsDuplicator = duplicator;
    }

    public KeyValueSampleBuilder toBuilder() {
        return toKeyValueSampleBuilder(itsDuplicator, itsDuplicator.apply(itsMap));
    }

    public boolean hasInteger(Object key) {
        return hasObject(key) && getObject(key) instanceof Integer;
    }

    public boolean hasLong(Object key) {
        return hasObject(key) && getObject(key) instanceof Long;
    }

    public boolean hasDouble(Object key) {
        return hasObject(key) && getObject(key) instanceof Double;
    }

    public boolean hasObject(Object key) {
        return itsMap.containsKey(key);
    }

    public int getInteger(Object key) {
        return (int) getObject(key);
    }

    public long getLong(Object key) {
        return (long) getObject(key);
    }

    public double getDouble(Object key) {
        return (double) getObject(key);
    }

    public Object getObject(Object key) {
        return itsMap.get(key);
    }
}

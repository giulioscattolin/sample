package com.github.giulioscattolin.sample;

public interface KeyValueSample extends Sample {
    default void accept(SampleVisitor visitor) {
        visitor.visit(this);
    }

    KeyValueSampleBuilder toBuilder();

    boolean hasInteger(Object key);

    boolean hasLong(Object key);

    boolean hasDouble(Object key);

    boolean hasObject(Object key);

    int getInteger(Object key);

    long getLong(Object key);

    double getDouble(Object key);

    Object getObject(Object key);
}

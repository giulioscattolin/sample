package com.github.giulioscattolin.sample;

public interface KeyValueSample extends Sample {
    default void accept(SampleVisitor visitor) {
        visitor.visit(this);
    }

    KeyValueSampleBuilder toBuilder();

    boolean hasBoolean(String key);

    boolean hasInteger(String key);

    boolean hasLong(String key);

    boolean hasDouble(String key);

    boolean hasObject(String key);

    boolean getBoolean(String key);

    int getInteger(String key);

    long getLong(String key);

    double getDouble(String key);

    Object getObject(String key);
}

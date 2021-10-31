package com.github.giulioscattolin.sample;

public abstract class GenericKeyValueAssociation<T> {
    public T getValue(Sample sample) {
        return getValue((KeyValueSample) sample);
    }

    private T getValue(KeyValueSample sample) {
        return (T) getObject(sample);
    }

    public void includeValue(KeyValueSampleBuilder builder, T value) {
        builder.insert(getKey(), value);
    }

    public boolean isValueIncludedIn(Sample sample) {
        if (sample instanceof KeyValueSample)
            return isValueIncludedIn((KeyValueSample) sample);
        else
            return false;
    }

    private boolean isValueIncludedIn(KeyValueSample sample) {
        return sample.hasObject(getKey()) && getValueClass().isAssignableFrom(getObject(sample).getClass());
    }

    private Object getObject(KeyValueSample sample) {
        return sample.getObject(getKey());
    }

    public abstract String getKey();

    public abstract Class<T> getValueClass();
}

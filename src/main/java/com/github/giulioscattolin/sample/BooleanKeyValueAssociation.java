package com.github.giulioscattolin.sample;

public abstract class BooleanKeyValueAssociation {
    public boolean getValue(Sample sample) {
        return getValue((KeyValueSample) sample);
    }

    private boolean getValue(KeyValueSample sample) {
        return sample.getBoolean(getKey());
    }

    public void includeValue(KeyValueSampleBuilder builder, boolean value) {
        builder.insert(getKey(), value);
    }

    public boolean isValueIncludedIn(Sample sample) {
        if (sample instanceof KeyValueSample)
            return isValueIncludedIn((KeyValueSample) sample);
        else
            return false;
    }

    private boolean isValueIncludedIn(KeyValueSample sample) {
        return sample.hasBoolean(getKey());
    }

    public abstract String getKey();
}

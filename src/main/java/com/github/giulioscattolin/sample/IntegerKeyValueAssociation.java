package com.github.giulioscattolin.sample;

public abstract class IntegerKeyValueAssociation {
    public int getValue(Sample sample) {
        return getValue((KeyValueSample) sample);
    }

    private int getValue(KeyValueSample sample) {
        return sample.getInteger(getKey());
    }

    public void includeValue(KeyValueSampleBuilder builder, int value) {
        builder.insert(getKey(), value);
    }

    public boolean isValueIncludedIn(Sample sample) {
        if (sample instanceof KeyValueSample)
            return isValueIncludedIn((KeyValueSample) sample);
        else
            return false;
    }

    private boolean isValueIncludedIn(KeyValueSample sample) {
        return sample.hasInteger(getKey());
    }

    public abstract String getKey();
}

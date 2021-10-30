package com.github.giulioscattolin.sample;

public abstract class LongKeyValueAssociation {
    public long getValue(Sample sample) {
        return getValue((KeyValueSample) sample);
    }

    private long getValue(KeyValueSample sample) {
        return sample.getLong(getKey());
    }

    public void includeValue(KeyValueSampleBuilder builder, long value) {
        builder.insert(getKey(), value);
    }

    public boolean isValueIncludedIn(Sample sample) {
        if (sample instanceof KeyValueSample)
            return isValueIncludedIn((KeyValueSample) sample);
        else
            return false;
    }

    private boolean isValueIncludedIn(KeyValueSample sample) {
        return sample.hasLong(getKey());
    }

    public abstract Object getKey();
}

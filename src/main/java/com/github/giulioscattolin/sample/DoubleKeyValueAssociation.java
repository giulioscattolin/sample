package com.github.giulioscattolin.sample;

public abstract class DoubleKeyValueAssociation {
    public double getValue(Sample sample) {
        return getValue((KeyValueSample) sample);
    }

    private double getValue(KeyValueSample sample) {
        return sample.getDouble(getKey());
    }

    public void includeValue(KeyValueSampleBuilder builder, double value) {
        builder.insert(getKey(), value);
    }

    public boolean isValueIncludedIn(Sample sample) {
        if (sample instanceof KeyValueSample)
            return isValueIncludedIn((KeyValueSample) sample);
        else
            return false;
    }

    private boolean isValueIncludedIn(KeyValueSample sample) {
        return sample.hasDouble(getKey());
    }

    public abstract String getKey();
}

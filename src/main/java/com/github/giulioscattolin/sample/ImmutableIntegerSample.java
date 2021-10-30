package com.github.giulioscattolin.sample;

import java.util.Objects;

class ImmutableIntegerSample implements IntegerSample {
    private final int itsValue;

    ImmutableIntegerSample(int value) {
        itsValue = value;
    }

    public int getValue() {
        return itsValue;
    }

    public String toString() {
        return String.valueOf(getValue());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImmutableIntegerSample that = (ImmutableIntegerSample) o;
        return itsValue == that.itsValue;
    }

    public int hashCode() {
        return Objects.hash(itsValue);
    }
}

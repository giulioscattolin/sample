package com.github.giulioscattolin.sample;

public interface SampleCollectorDecorator {
    static SampleCollector reduce(SampleCollector initial, SampleCollectorDecorator... decorators) {
        SampleCollector current = initial;
        for (SampleCollectorDecorator decorator : decorators)
            current = decorator.decorate(current);
        return current;
    }

    SampleCollector decorate(SampleCollector collector);
}

package com.github.giulioscattolin.sample;

public abstract class CompositeSampleEmitter implements SampleCollector {
    private final SampleCollector itsSampleCollector;
    private final CompositeSampleBuilder itsSampleBuilder;

    protected CompositeSampleEmitter(SampleCollector sampleCollector, CompositeSampleBuilder sampleBuilder) {
        itsSampleCollector = sampleCollector;
        itsSampleBuilder = sampleBuilder;
    }

    public void collect(Sample sample) {
        switch (getAction(sample)) {
            case INCLUDE_SAMPLE:
                itsSampleBuilder.include(sample);
                return;
            case EMIT_SAMPLE:
                itsSampleCollector.collect(itsSampleBuilder.build());
                itsSampleBuilder.clear();
                itsSampleBuilder.include(sample);
                return;
        }
    }

    public void flush() {
        itsSampleCollector.collect(itsSampleBuilder.build());
    }

    protected abstract Action getAction(Sample sample);

    public enum Action {
        INCLUDE_SAMPLE,
        EMIT_SAMPLE
    }
}

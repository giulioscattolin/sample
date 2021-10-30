package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.github.giulioscattolin.sample.CompositeSampleEmitter.Action.EMIT_SAMPLE;
import static com.github.giulioscattolin.sample.CompositeSampleEmitter.Action.INCLUDE_SAMPLE;
import static com.github.giulioscattolin.sample.MutableSampleListBuilder.toCompositeSampleBuilder;
import static com.github.giulioscattolin.sample.PrimitiveSampleFactory.toSample;
import static com.google.common.truth.Truth.assertThat;

public class CompositeSampleEmitterTest {
    CompositeSampleBuilder itsInputSampleBuilder;
    CompositeSampleBuilder itsOutputSampleBuilder;
    CompositeSample itsCompositeSample;
    List<Sample> itsInputSamples;

    @Before
    public void setup() {
        itsInputSampleBuilder = toCompositeSampleBuilder(LinkedList::new);
        itsOutputSampleBuilder = toCompositeSampleBuilder(LinkedList::new);
    }

    @Test
    public void testEvenOddSampler() {
        itsInputSamples = itsInputSampleBuilder
            .include(toSample(1))
            .include(toSample(3))
            .include(toSample(2))
            .include(toSample(4))
            .include(toSample(6))
            .include(toSample(5))
            .build()
            .getSamples();

        runSampler();

        assertThat(itsCompositeSample.getSamples()).hasSize(3);
        assertThat(itsCompositeSample.getSamples().get(0)).isEqualTo(
            toCompositeSampleBuilder(LinkedList::new)
                .include(toSample(1))
                .include(toSample(3))
                .build()
        );
        assertThat(itsCompositeSample.getSamples().get(1)).isEqualTo(
            toCompositeSampleBuilder(LinkedList::new)
                .include(toSample(2))
                .include(toSample(4))
                .include(toSample(6))
                .build()
        );
        assertThat(itsCompositeSample.getSamples().get(2)).isEqualTo(
            toCompositeSampleBuilder(LinkedList::new)
                .include(toSample(5))
                .build()
        );
    }

    private void runSampler() {
        EvenOddSampler sampler = new EvenOddSampler(itsOutputSampleBuilder::include);
        for (Sample sample : itsInputSamples)
            sampler.collect(sample);
        sampler.flush();
        itsCompositeSample = itsOutputSampleBuilder.build();
    }

    static class EvenOddSampler extends CompositeSampleEmitter {
        char evenOrOdd = '?';

        EvenOddSampler(SampleCollector sampleCollector) {
            super(sampleCollector, toCompositeSampleBuilder(LinkedList::new));
        }

        protected Action getAction(Sample sample) {
            return new GetAction(sample).itsAction;
        }

        class GetAction implements SampleVisitor {
            public Action itsAction;
            private boolean isEven;

            public GetAction(Sample sample) {
                sample.accept(this);
            }

            public void visit(int value) {
                tellIfIsEven(value);
                initializeEvenOrOdd();
                selectAction();
                updateEvenOrOdd();
            }

            private void tellIfIsEven(int value) {
                isEven = value % 2 == 0;
            }

            private void initializeEvenOrOdd() {
                if (evenOrOdd == '?')
                    updateEvenOrOdd();
            }

            private void selectAction() {
                switch (evenOrOdd) {
                    case 'E':
                        if (isEven)
                            itsAction = INCLUDE_SAMPLE;
                        else
                            itsAction = EMIT_SAMPLE;
                        break;
                    case 'O':
                        if (isEven)
                            itsAction = EMIT_SAMPLE;
                        else
                            itsAction = INCLUDE_SAMPLE;
                }
            }

            private void updateEvenOrOdd() {
                evenOrOdd = isEven ? 'E' : 'O';
            }
        }
    }
}
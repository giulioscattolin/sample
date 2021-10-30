package com.github.giulioscattolin.sample;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static com.github.giulioscattolin.sample.MutableSingleMapKeyValueSample.toKeyValueSampleBuilder;
import static com.google.common.truth.Truth.assertThat;

public class SampleCollectorDecoratorTest {
    private KeyValueSample itsFinalSample;

    @Test
    public void test() {
        SampleCollectorDecorator.reduce(
                this::setFinalSample,
                new MultiplyOperands(),
                new IncludeSecondOperand(),
                new IncludeFirstOperand()
            )
            .collect(buildEmptyKeyValueSample());

        assertThat(itsFinalSample.getInteger("FirstOperand")).isEqualTo(7);
        assertThat(itsFinalSample.getInteger("SecondOperand")).isEqualTo(3);
        assertThat(itsFinalSample.getInteger("Result")).isEqualTo(21);
    }

    private KeyValueSample buildEmptyKeyValueSample() {
        return toKeyValueSampleBuilder(HashMap::new).build();
    }

    @Before
    public void clean() {
        itsFinalSample = null;
    }

    private void setFinalSample(Sample itsFinalSample) {
        this.itsFinalSample = (KeyValueSample) itsFinalSample;
    }

    private static class IncludeFirstOperand implements SampleCollectorDecorator {
        public SampleCollector decorate(SampleCollector collector) {
            return (sample) -> sample.accept(new SampleVisitor() {
                public void visit(KeyValueSample keyValueSample) {
                    collector.collect(
                        keyValueSample.toBuilder()
                            .insert("FirstOperand", 7)
                            .build()
                    );
                }
            });
        }
    }

    private static class IncludeSecondOperand implements SampleCollectorDecorator {
        public SampleCollector decorate(SampleCollector collector) {
            return (sample) -> sample.accept(new SampleVisitor() {
                public void visit(KeyValueSample keyValueSample) {
                    collector.collect(
                        keyValueSample.toBuilder()
                            .insert("SecondOperand", 3)
                            .build()
                    );
                }
            });
        }
    }

    private static class MultiplyOperands implements SampleCollectorDecorator {
        public SampleCollector decorate(SampleCollector collector) {
            return (sample) -> sample.accept(new SampleVisitor() {
                public void visit(KeyValueSample keyValueSample) {
                    int firstOperand = keyValueSample.getInteger("FirstOperand");
                    int secondOperand = keyValueSample.getInteger("SecondOperand");
                    int result = firstOperand * secondOperand;
                    collector.collect(
                        keyValueSample.toBuilder()
                            .insert("Result", result)
                            .build()
                    );
                }
            });
        }
    }
}
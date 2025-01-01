package com.segence.commons.jmx.collector;

import java.util.Optional;

public record MBeanMetricResult(Throwable error, MBeanMetric mBeanMetric) {

    public MBeanMetricResult(Throwable error) {
        this(error, null);
    }

    public MBeanMetricResult(MBeanMetric mBeanMetric) {
        this(null, mBeanMetric);
    }

    public Optional<Throwable> getError() {
        return Optional.ofNullable(error);
    }

    public Optional<MBeanMetric> getMBeanMetric() {
        return Optional.ofNullable(mBeanMetric);
    }
}

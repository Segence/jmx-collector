package com.segence.commons.jmx.collector;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public final class MBeanMetricResult implements Serializable {

    private Throwable error;
    private MBeanMetric mBeanMetric;

    public MBeanMetricResult(MBeanMetric mBeanMetric) {
        this.mBeanMetric = mBeanMetric;
    }

    public MBeanMetricResult(Throwable error) {
        this.error = error;
    }

    public Optional<Throwable> getError() {
        return Optional.ofNullable(error);
    }

    public Optional<MBeanMetric> getMBeanMetric() {
        return Optional.ofNullable(mBeanMetric);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MBeanMetricResult that = (MBeanMetricResult) o;
        return Objects.equals(error, that.error) && Objects.equals(mBeanMetric, that.mBeanMetric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, mBeanMetric);
    }

    @Override
    public String toString() {
        return "MBeanMetricResult{"
            + "error="
            + error
            + ", mBeanMetric="
            + mBeanMetric
            + '}';
    }
}

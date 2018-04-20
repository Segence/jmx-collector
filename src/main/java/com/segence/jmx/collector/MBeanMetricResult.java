package com.segence.jmx.collector;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

public class MBeanMetricResult implements Serializable {

    private Optional<Throwable> error = Optional.empty();
    private Optional<MBeanMetric> mBeanMetric = Optional.empty();

    public MBeanMetricResult(MBeanMetric mBeanMetric) {
        this.mBeanMetric = Optional.of(mBeanMetric);
    }

    public MBeanMetricResult(Throwable error) {
        this.error = Optional.of(error);
    }

    public Optional<Throwable> getError() {
        return error;
    }

    public Optional<MBeanMetric> getmBeanMetric() {
        return mBeanMetric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MBeanMetricResult that = (MBeanMetricResult) o;
        return Objects.equals(error, that.error) &&
                Objects.equals(mBeanMetric, that.mBeanMetric);
    }

    @Override
    public int hashCode() {

        return Objects.hash(error, mBeanMetric);
    }

    @Override
    public String toString() {
        return "MBeanMetricResult{" +
                "error=" + error +
                ", mBeanMetric=" + mBeanMetric +
                '}';
    }
}

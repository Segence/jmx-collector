package com.segence.commons.jmx.collector;

import javax.management.Attribute;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MBeanMetric implements Serializable {

    private String mbeanClassName;
    private List<Attribute> attributes;

    public MBeanMetric(String mbeanClassName, List<Attribute> attributes) {
        this.mbeanClassName = mbeanClassName;
        this.attributes = attributes;
    }

    public String getMbeanClassName() {
        return mbeanClassName;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MBeanMetric that = (MBeanMetric) o;
        return Objects.equals(mbeanClassName, that.mbeanClassName) &&
                Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(mbeanClassName, attributes);
    }

    @Override
    public String toString() {
        return "MBeanMetric{" +
                "mbeanClassName='" + mbeanClassName + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}

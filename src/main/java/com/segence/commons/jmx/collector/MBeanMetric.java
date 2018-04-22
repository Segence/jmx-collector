package com.segence.commons.jmx.collector;

import javax.management.Attribute;
import javax.management.ObjectInstance;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class MBeanMetric implements Serializable {

    private ObjectInstance objectInstance;
    private List<Attribute> attributes;

    public MBeanMetric(ObjectInstance objectInstance, List<Attribute> attributes) {
        this.objectInstance = objectInstance;
        this.attributes = attributes;
    }

    public ObjectInstance getObjectInstance() {
        return objectInstance;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MBeanMetric that = (MBeanMetric) o;
        return Objects.equals(objectInstance, that.objectInstance) &&
                Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {

        return Objects.hash(objectInstance, attributes);
    }

    @Override
    public String toString() {
        return "MBeanMetric{" +
                "objectInstance='" + objectInstance + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}

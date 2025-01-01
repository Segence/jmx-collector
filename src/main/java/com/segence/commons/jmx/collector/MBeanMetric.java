package com.segence.commons.jmx.collector;

import java.util.Collections;
import java.util.List;

import javax.management.Attribute;
import javax.management.ObjectInstance;

public record MBeanMetric(ObjectInstance objectInstance, List<Attribute> attributes) {
    public MBeanMetric {
        attributes = Collections.unmodifiableList(attributes);
    }
}

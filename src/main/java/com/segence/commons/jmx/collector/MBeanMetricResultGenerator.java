package com.segence.commons.jmx.collector;

import java.util.Collections;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ReflectionException;

record MBeanMetricResultGenerator(MBeanServer mBeanServer) {
    MBeanMetricResult getAttributes(ObjectInstance objectInstance, String[] objectNameAndAttributes) {
        try {
            final var attributes = mBeanServer.getAttributes(
                objectInstance.getObjectName(),
                objectNameAndAttributes
            );
            return new MBeanMetricResult(new MBeanMetric(
                objectInstance,
                Collections.unmodifiableList(attributes.asList())
            ));
        } catch (InstanceNotFoundException | ReflectionException e) {
            return new MBeanMetricResult(e);
        }
    }
}

package com.segence.commons.jmx.collector;

import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JmxCollector {

    private static MBeanServer server = ManagementFactory.getPlatformMBeanServer();

    public static Stream<MBeanMetricResult> query(Map<ObjectName, Set<String>> objectNames) {
        return objectNames.entrySet().stream().flatMap(objectNameAndAttributes ->
            server.queryMBeans(objectNameAndAttributes.getKey(), null).stream().map(objectInstance -> {
                try {
                    AttributeList attributes = server.getAttributes(
                        objectInstance.getObjectName(),
                        objectNameAndAttributes.getValue().stream().toArray(String[]::new)
                    );
                    return new MBeanMetricResult((new MBeanMetric(objectInstance.getClassName(), attributes.asList())));
                } catch (Throwable e) {
                    return new MBeanMetricResult(e);
                }
            })
        );
    }

    public static Set<MBeanMetricResult> queryAsSet(Map<ObjectName, Set<String>> objectNames) {
        return query(objectNames).collect(Collectors.toSet());
    }
}

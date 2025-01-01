package com.segence.commons.jmx.collector;

import java.lang.management.ManagementFactory;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.MBeanServer;
import javax.management.ObjectName;

public final class JmxCollector {

    private static final MBeanServer MBEAN_SERVER = ManagementFactory.getPlatformMBeanServer();

    private static final MBeanMetricResultGenerator MBEAN_METRIC_RESULT_GENERATOR =
        new MBeanMetricResultGenerator(MBEAN_SERVER);

    private JmxCollector() { }

    /**
     * Queries a collection of MBeans.
     *
     * <p>This method takes a map where each key represents an {@link ObjectName} corresponding to a specific MBean,
     * and each value is a set of attribute names for that MBean. The method queries the MBeans for the specified
     * attributes using the {@code M_BEAN_SERVER}, processes the results, and wraps them in {@link MBeanMetricResult}
     * objects. If an error occurs during the retrieval of attributes, the exception is captured in an
     * {@code MBeanMetricResult} object.</p>
     *
     * @param objectNames a map where keys are {@link ObjectName}s representing MBeans, and values are sets of attribute
     *                    names to query for each MBean. The map must not be {@code null}.
     * @return a stream of {@link MBeanMetricResult} objects, each containing either the retrieved attributes for a
     *         specific MBean or an exception if an error occurred during the query process.
     *
     * @throws NullPointerException if {@code objectNames} or any of its keys/values are {@code null}.
     * @see javax.management.ObjectName
     * @see javax.management.AttributeList
     * @see MBeanMetricResult
     * @see MBeanMetric
     */
    public static Stream<MBeanMetricResult> query(Map<ObjectName, Set<String>> objectNames) {
        // CHECKSTYLE:OFF: checkstyle:NeedBraces
        return objectNames.entrySet().stream().flatMap(objectNameAndAttributes ->
            MBEAN_SERVER.queryMBeans(objectNameAndAttributes.getKey(), null)
                        .stream().map(objectInstance ->
                                            MBEAN_METRIC_RESULT_GENERATOR.getAttributes(
                                                objectInstance,
                                                objectNameAndAttributes.getValue().toArray(String[]::new)
                                            )
            )
        );
        // CHECKSTYLE:ON: checkstyle:NeedBraces
    }

    public static Set<MBeanMetricResult> queryAsSet(Map<ObjectName, Set<String>> objectNames) {
        return query(objectNames).collect(Collectors.toSet());
    }
}

package com.segence.commons.jmx.collector;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.management.Attribute;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.junit.jupiter.api.Test;

class JmxCollectorTest {

    private final static String CURRENT_SYSTEM_ARCHITECTURE = System.getProperty("os.arch");
    private final static int NUMBER_OF_CPU_CORES = Runtime.getRuntime().availableProcessors();

    @Test
    public void shouldReportInvalidMbeansAndAttributeValues() throws MalformedObjectNameException {

        final Map<ObjectName, Set<String>> mbeansAndAttributesToQuery = new HashMap<>() {{
            try {
                put(
                    new ObjectName("java.lang:type=OperatingSystem"),
                    new HashSet<String>() {{
                        add("non-valid-attribute");
                    }}
                );
                put(
                    new ObjectName("java.lang:type=not-valid-object"),
                    new HashSet<String>() {{
                        add("another-non-valid-attribute");
                    }}
                );
            } catch (MalformedObjectNameException e) {
                e.printStackTrace();
            }
        }};

        final var objectInstance = new ObjectInstance(
            "java.lang:type=OperatingSystem",
            "com.sun.management.internal.OperatingSystemImpl"
        );

        final var expectedResult = Stream.of(
            new MBeanMetricResult(
                new MBeanMetric(objectInstance, Collections.emptyList())
            )).collect(Collectors.toSet());

        final var result = JmxCollector.queryAsSet(mbeansAndAttributesToQuery);

        assertThat(result, is(expectedResult));
    }

    @Test
    public void shouldReturnAllValidMbeansAndAttributeValues() throws MalformedObjectNameException {

        final Map<ObjectName, Set<String>> mbeansAndAttributesToQuery = new HashMap<>() {{
            try {
                put(
                    new ObjectName("java.lang:type=OperatingSystem"),
                    new HashSet<String>() {{
                        add("Arch");
                        add("AvailableProcessors");
                    }}
                );
            } catch (MalformedObjectNameException e) {
                e.printStackTrace();
            }
        }};

        final var objectInstance = new ObjectInstance(
            "java.lang:type=OperatingSystem",
            "com.sun.management.internal.OperatingSystemImpl"
        );

        final var expectedResult = Stream.of(
            new MBeanMetricResult(
                new MBeanMetric(
                    objectInstance,
                    Arrays.asList(
                        new Attribute("AvailableProcessors", NUMBER_OF_CPU_CORES),
                        new Attribute("Arch", CURRENT_SYSTEM_ARCHITECTURE)
                    )
                )
        )).collect(Collectors.toSet());

        final var result = JmxCollector.queryAsSet(mbeansAndAttributesToQuery);

        assertThat(result, is(expectedResult));
    }
}

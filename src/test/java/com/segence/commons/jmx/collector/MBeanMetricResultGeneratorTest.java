package com.segence.commons.jmx.collector;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MBeanMetricResultGeneratorTest {

    @Mock
    private MBeanServer mbeanServer;

    @Mock
    private InstanceNotFoundException instanceNotFoundException;

    @Mock
    private ObjectInstance objectInstance;

    @Mock
    private ObjectName objectName;

    @Test
    void shouldGenerateMBeanMetricResultWithAnException() throws ReflectionException, InstanceNotFoundException {

        when(objectInstance.getObjectName()).thenReturn(objectName);
        when(mbeanServer.getAttributes(eq(objectName), any(String[].class))).thenThrow(instanceNotFoundException);

        final var result = new MBeanMetricResultGenerator(mbeanServer).getAttributes(objectInstance, new String[]{});

        assertTrue(result.getMBeanMetric().isEmpty());
        assertTrue(result.getError().isPresent());
        assertThat(result.getError().get(), is(instanceNotFoundException));
    }
}

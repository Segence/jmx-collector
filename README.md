JMX Collector
=============

[![Build Status](https://travis-ci.org/Segence/jmx-collector.svg?branch=master)](https://travis-ci.org/Segence/jmx-collector)
[ ![Download](https://api.bintray.com/packages/segence/maven-oss-releases/jmx-collector/images/download.svg) ](https://bintray.com/segence/maven-oss-releases/jmx-collector/_latestVersion)

A library to collect JMX metrics.

Usage
-----

1. Add the Segence OSS Releases Maven repo to your project, [click here](https://bintray.com/segence/maven-oss-releases/jmx-collector) for instructions
2. Import the artifact, [click here](https://bintray.com/segence/maven-oss-releases/jmx-collector) for instructions
3. Query JMX metrics by specifying some `MBean` names and attributes. Example:
```java
import com.segence.commons.jmx.collector.JmxCollector

...

Map<ObjectName, Set<String>> mbeansAndAttributesToQuery = new HashMap<ObjectName, Set<String>>() {{
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

Set<MBeanMetricResult> results = JmxCollector.queryAsSet(mbeansAndAttributesToQuery);
```


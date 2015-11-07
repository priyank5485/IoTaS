package com.hortonworks.iotas.datastream;

import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
public interface DataStreamConfig {
    // Method for any one time initialization code
    public void initialize ();

    // Return components supported by an implementation.
    public List<DataStreamComponent> getSupportedComponents ();

    // Return types supported for a given component. For eg HDFS, HBASE for SINK
    public List<DataStreamComponentType> getSupportedTypes
            (DataStreamComponent component);

    // Return fields that need to be configured for a type of a component. eg
    // zkUrl, etc for KAFKA SOURCE
    public List<DataStreamFieldConfig> getConfigFields (DataStreamComponent
                                                                component, DataStreamComponentType componentType);
}

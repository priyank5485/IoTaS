package com.hortonworks.iotas.datastream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
public class KafkaSpoutDataStreamComponent implements StormDataStreamComponent {
    private final List<DataStreamFieldConfig> dataStreamFieldConfigs = Arrays
            .asList(
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_BROKER_HOSTS_CLASS, false, "storm.kafka.ZkHosts"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ZK_URL, false, "localhost:2182"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ZK_PATH, true, "/brokers"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_TOPIC, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ZK_ROOT, false, "/Iotas-kafka-spout"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_SPOUT_ID, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PARALLELISM, true, 1)
            );

    @Override
    public DataStreamComponent getComponent () {
        return DataStreamComponent.SOURCE;
    }

    @Override
    public DataStreamComponentType getType () {
        return DataStreamComponentType.KAFKA;
    }

    @Override
    public List<DataStreamFieldConfig> getConfigFields () {
        return dataStreamFieldConfigs;
    }
}

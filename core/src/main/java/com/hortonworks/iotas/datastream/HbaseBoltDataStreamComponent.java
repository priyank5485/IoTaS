package com.hortonworks.iotas.datastream;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
public class HbaseBoltDataStreamComponent implements StormDataStreamComponent {
    private final List<DataStreamFieldConfig> dataStreamFieldConfigs = Arrays
            .asList(
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_MAPPER_IMPL, false, "com.hortonworks.hbase.ParserOutputHBaseMapper"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ROOT_DIR, false, "hdfs://localhost:9000/hbase"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_TABLE, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_CONFIG_KEY, true, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_COLUMN_FAMILY, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PARALLELISM, true, 1)
            );
    @Override
    public DataStreamComponent getComponent () {
        return DataStreamComponent.SINK;
    }

    @Override
    public DataStreamComponentType getType () {
        return DataStreamComponentType.HBASE;
    }

    @Override
    public List<DataStreamFieldConfig> getConfigFields () {
        return dataStreamFieldConfigs;
    }
}

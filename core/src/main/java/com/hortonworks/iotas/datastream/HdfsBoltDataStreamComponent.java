package com.hortonworks.iotas.datastream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
public class HdfsBoltDataStreamComponent implements StormDataStreamComponent {
    private final List<DataStreamFieldConfig> dataStreamFieldConfigs = Arrays
            .asList(
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_FS_URL, false, "hdfs://localhost:9000"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_CONFIG_KEY, true, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_FILE_NAME_FORMAT_IMPL, false, "org.apache.storm.hdfs.bolt.format.DefaultFileNameFormat"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PATH, true, "/storm"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_NAME, true, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_RECORD_FORMAT_IMPL, false, "com.hortonworks.hdfs.IdentityHdfsRecordFormat"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_SYNC_POLICY_IMPL, false, "org.apache.storm.hdfs.bolt.sync.CountSyncPolicy"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_COUNT_POLICY_VALUE, true, 1),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ROTATION_POLICY_IMPL, false, "org.apache.storm.hdfs.bolt.rotation.TimedRotationPolicy"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ROTATION_INTERVAL, true, 10),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ROTATION_INTERVAL_UNIT, true, "SECONDS"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_ROTATION_ACTIONS, true, new ArrayList<String>()),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PARALLELISM, true, 1)
            );
    @Override
    public DataStreamComponent getComponent () {
        return DataStreamComponent.SINK;
    }

    @Override
    public DataStreamComponentType getType () {
        return DataStreamComponentType.HDFS;
    }

    @Override
    public List<DataStreamFieldConfig> getConfigFields () {
        return dataStreamFieldConfigs;
    }
}

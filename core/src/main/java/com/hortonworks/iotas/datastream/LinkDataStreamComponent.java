package com.hortonworks.iotas.datastream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
public class LinkDataStreamComponent implements StormDataStreamComponent {
    private final List<DataStreamFieldConfig> dataStreamFieldConfigs = Arrays
            .asList(
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_FROM, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_TO, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_STREAM_ID, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_GROUPING, false, "SHUFFLE"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants .JSON_KEY_GROUPING_FIELDS, false, new ArrayList<String>()),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_CUSTOM_GROUPING_IMPL, true, "")
            );

    @Override
    public DataStreamComponent getComponent () {
        return DataStreamComponent.LINK;
    }

    @Override
    public DataStreamComponentType getType () {
        return DataStreamComponentType.DEFAULTLINK;
    }

    @Override
    public List<DataStreamFieldConfig> getConfigFields () {
        return dataStreamFieldConfigs;
    }
}

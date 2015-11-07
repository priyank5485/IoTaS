package com.hortonworks.iotas.datastream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
public class ParserBoltDataStreamComponent implements StormDataStreamComponent {
    private final List<DataStreamFieldConfig> dataStreamFieldConfigs = Arrays
            .asList(
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PARSED_TUPLES_STREAM, false, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_FAILED_TUPLES_STREAM, true, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PARSER_JAR_PATH, false, "/tmp"),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PARSER_ID, true, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_DATA_SOURCE_ID, true, ""),
                    new DataStreamFieldConfig(DataStreamLayoutConstants.JSON_KEY_PARALLELISM, true, 1)
            );

    @Override
    public DataStreamComponent getComponent () {
        return DataStreamComponent.PROCESSOR;
    }

    @Override
    public DataStreamComponentType getType () {
        return DataStreamComponentType.PARSER;
    }

    @Override
    public List<DataStreamFieldConfig> getConfigFields () {
        return dataStreamFieldConfigs;
    }
}

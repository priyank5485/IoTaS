package com.hortonworks.iotas.datastream;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
public class RuleBoltDataStreamComponent implements StormDataStreamComponent {

    @Override
    public DataStreamComponent getComponent () {
        return DataStreamComponent.PROCESSOR;
    }

    @Override
    public DataStreamComponentType getType () {
        return DataStreamComponentType.RULE;
    }

    @Override
    public List<DataStreamFieldConfig> getConfigFields () {
        return new ArrayList<DataStreamFieldConfig>();
    }
}

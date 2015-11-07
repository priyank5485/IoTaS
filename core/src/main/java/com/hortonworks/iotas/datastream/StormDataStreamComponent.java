package com.hortonworks.iotas.datastream;

import java.util.List;

/**
 * Created by pshah on 11/5/15.
 */
interface StormDataStreamComponent {
    DataStreamComponent getComponent ();

    DataStreamComponentType getType ();

    List<DataStreamFieldConfig> getConfigFields ();
}

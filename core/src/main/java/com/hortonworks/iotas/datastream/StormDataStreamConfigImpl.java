package com.hortonworks.iotas.datastream;

import com.hortonworks.iotas.datastream.DataStreamComponent;
import com.hortonworks.iotas.datastream.DataStreamComponentType;
import com.hortonworks.iotas.datastream.DataStreamConfig;
import com.hortonworks.iotas.datastream.DataStreamFieldConfig;
import com.hortonworks.iotas.util.ReflectionHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by pshah on 11/5/15.
 */
public class StormDataStreamConfigImpl implements DataStreamConfig {
    private final String PACKAGE_PREFIX = "com.hortonworks.iotas.datastream.";
    private final Logger LOG = LoggerFactory.getLogger
            (StormDataStreamConfigImpl.class);
    private final String[] STORM_COMPONENTS = {
            PACKAGE_PREFIX +"KafkaSpoutDataStreamComponent",
            PACKAGE_PREFIX + "HbaseBoltDataStreamComponent",
            PACKAGE_PREFIX + "HdfsBoltDataStreamComponent",
            PACKAGE_PREFIX + "RuleBoltDataStreamComponent",
            PACKAGE_PREFIX + "ParserBoltDataStreamComponent",
            PACKAGE_PREFIX + "LinkDataStreamComponent"
    };
    private Map<DataStreamComponent, List<StormDataStreamComponent>>
            mapOfStormComponents = new HashMap<DataStreamComponent,
            List<StormDataStreamComponent>>();

    @Override
    public List<DataStreamComponent> getSupportedComponents () {
        return new ArrayList<DataStreamComponent>(mapOfStormComponents.keySet
                ());
    }

    @Override
    public List<DataStreamComponentType> getSupportedTypes
            (DataStreamComponent component) {
        List<DataStreamComponentType> result = new ArrayList<DataStreamComponentType>();
        if (mapOfStormComponents.containsKey(component)) {
            List<StormDataStreamComponent> stormDataStreamComponents =
                    mapOfStormComponents.get(component);
            for (StormDataStreamComponent stormDataStreamComponent:
                    stormDataStreamComponents) {
                result.add(stormDataStreamComponent.getType());
            }
        }
        return result;
    }

    @Override
    public List<DataStreamFieldConfig> getConfigFields (DataStreamComponent
                                                                    component, DataStreamComponentType componentType) {
        if (mapOfStormComponents.containsKey(component)) {
            List<StormDataStreamComponent> stormDataStreamComponents =
                    mapOfStormComponents.get(component);
            for (StormDataStreamComponent stormDataStreamComponent:
                    stormDataStreamComponents) {
                if (stormDataStreamComponent.getType().equals(componentType)) {
                    return stormDataStreamComponent.getConfigFields();
                }
            }
        }
        return new ArrayList<DataStreamFieldConfig>();
    }

    @Override
    public void initialize () {
        StormDataStreamComponent stormComponent;
        for (String strStormComponent: STORM_COMPONENTS) {
            try {
                stormComponent = (StormDataStreamComponent) ReflectionHelper
                        .newInstance(strStormComponent);
                if (!mapOfStormComponents.containsKey(stormComponent
                        .getComponent())) {
                    mapOfStormComponents.put(stormComponent.getComponent(), new
                            ArrayList<StormDataStreamComponent>());
                }
                mapOfStormComponents.get(stormComponent.getComponent()).add
                        (stormComponent);
            } catch (Exception ex) {
                LOG.error("Unable to instantiate a StormDataStreamComponent " +
                        "implementation ", ex);
            }
        }
    }
}

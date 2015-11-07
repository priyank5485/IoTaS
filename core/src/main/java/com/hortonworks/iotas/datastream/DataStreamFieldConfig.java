package com.hortonworks.iotas.datastream;

/**
 * Created by pshah on 11/5/15.
 */
public class DataStreamFieldConfig {
    private String name;
    private boolean isOptional;
    private Object defaultValue;

    public DataStreamFieldConfig(String name, boolean isOptional, Object
            defaultVale) {
        this.name = name;
        this.isOptional = isOptional;
        this.defaultValue = defaultVale;
    }

    public String getName() {
       return name;
    }

    public Boolean isOptional() {
        return isOptional;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }
}

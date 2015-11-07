package com.hortonworks.iotas.datastream;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * Created by pshah on 10/16/15.
 */
public class DataStreamLayoutConstants {
    // json keys
    public final static String JSON_KEY_DATA_SOURCES = "dataSources";
    public final static String JSON_KEY_UINAME = "uiname";
    public final static String JSON_KEY_ID = "id";
    public final static String JSON_KEY_TYPE = "type";
    public final static String JSON_KEY_CONFIG = "config";
    public final static String JSON_KEY_ZK_URL = "zkUrl";
    public final static String JSON_KEY_TOPIC = "topic";
    public final static String JSON_KEY_PROCESSORS = "processors";
    public final static String JSON_KEY_DATA_SINKS = "dataSinks";
    public final static String JSON_KEY_ROOT_DIR = "rootDir";
    public final static String JSON_KEY_TABLE = "table";
    public final static String JSON_KEY_COLUMN_FAMILY = "columnFamily";
    public final static String JSON_KEY_ROW_KEY = "rowKey";
    public final static String JSON_KEY_MAPPER_IMPL =
            "hBaseMapperImplClassName";
    public final static String JSON_KEY_FS_URL = "fsUrl";
    public final static String JSON_KEY_PATH = "path";
    public final static String JSON_KEY_NAME = "name";
    public final static String JSON_KEY_FILE_NAME_FORMAT_IMPL =
            "filenameFormatImpl";
    public final static String JSON_KEY_RECORD_FORMAT_IMPL = "recordFormatImpl";
    public final static String JSON_KEY_SYNC_POLICY_IMPL = "syncPolicyImpl";
    public final static String JSON_KEY_COUNT_POLICY_VALUE = "countPolicyValue";
    public final static String JSON_KEY_ROTATION_POLICY_IMPL =
            "rotationPolicyImpl";
    public final static String JSON_KEY_ROTATION_INTERVAL = "rotationInterval";
    public final static String JSON_KEY_ROTATION_INTERVAL_UNIT =
            "rotationIntervalUnit";
    public final static String JSON_KEY_ROTATION_ACTIONS = "rotationActions";
    public final static String JSON_KEY_LINKS = "links";
    public final static String JSON_KEY_FROM = "from";
    public final static String JSON_KEY_TO = "to";
    public final static String JSON_KEY_CONFIG_KEY = "configKey";
    public final static String JSON_KEY_BROKER_HOSTS_CLASS = "storm.kafka" +
            ".ZkHosts";
    public final static String JSON_KEY_ZK_PATH = "zkPath";
    public final static String JSON_KEY_ZK_ROOT = "zkRoot";
    public final static String JSON_KEY_SPOUT_ID = "kafkaSpoutId";
    public final static String JSON_KEY_PARSED_TUPLES_STREAM =
            "parsedTuplesStream";
    public final static String JSON_KEY_FAILED_TUPLES_STREAM =
            "failedTuplesStream";
    public final static String JSON_KEY_PARSER_JAR_PATH =
            "parserJarPath";
    public final static String JSON_KEY_PARSER_ID =
            "parserId";
    public final static String JSON_KEY_DATA_SOURCE_ID =
            "dataSourceId";
    public final static String JSON_KEY_STREAM_ID =
            "streamId";
    public final static String JSON_KEY_GROUPING = "grouping";
    public final static String JSON_KEY_GROUPING_FIELDS = "groupingFields";
    public final static String JSON_KEY_CUSTOM_GROUPING_IMPL = "customGroupingImpl";
    public final static String JSON_KEY_PARALLELISM = "parallelism";

    // validation error messages
    public final static String ERR_MSG_UINAME_DUP = "Uiname %s is already " +
            "used by other component.";
    public final static String ERR_MSG_LINK_FROM = "Link from property %s " +
            "has to be a data source component or a processor out component " +
            "(for e.g. a rule).";
    public final static String ERR_MSG_LINK_TO = "Link to property %s " +
            "has to be a data sink component or a processor in component.";
    public final static String ERR_MSG_DISCONNETED_DATA_SOURCE = "Data Source" +
            " %s is not linked to any component.";
    public final static String ERR_MSG_DISCONNETED_DATA_SINK = "Data Sink " +
            "%s is not linked to any component.";
    public static final String ERR_MSG_DISCONNETED_PROCESSOR_IN = "Processor " +
            "%s does not take an input.";
    public static final String ERR_MSG_DISCONNETED_PROCESSOR_OUT = "Processor" +
            " %s does not connect to an output.";
    public static final String ERR_MSG_DATA_SOURCE_NOT_FOUND = "Data Source " +
            "with id %s not found in catalog.";
    public static final String ERR_MSG_DATA_SOURCE_INVALID_TYPE = "Data " +
            "Source type %s is not a valid type.";
    public static final String ERR_MSG_DATA_SINK_INVALID_TYPE = "Data " +
            "Sink type %s is not a valid type.";
    public static final String ERR_MSG_DATA_SOURCE_MISSING_CONFIG = "Config " +
            "parameters missing for Data Source %s.";
    public static final String ERR_MSG_DATA_SINK_MISSING_CONFIG = "Config " +
            "parameters missing for Data Sink %s.";

    // artifact
    public static final String STORM_ARTIFACTS_LOCATION_KEY =
            "stormArtifactsDirectory";
    public static final String STORM_JAR_LOCATION_KEY = "iotasStormJar";
    // yaml key constants
    public static final String YAML_KEY_NAME = "name";
    public static final String YAML_KEY_CATALOG_ROOT_URL = "catalog.root.url";
    public static final String YAML_KEY_LOCAL_PARSER_JAR_PATH = "local" +
                    ".parser.jar.path";
    // TODO: add hbase conf to topology config when processing data sinks
    public static final String YAML_KEY_CONFIG = "config";
    public static final String YAML_KEY_HBASE_CONF = "hbase.conf";
    public static final String YAML_KEY_HBASE_ROOT_DIR = "hbase.root.dir";
    public static final String YAML_KEY_COMPONENTS = "components";
    public static final String YAML_KEY_SPOUTS = "spouts";
    public static final String YAML_KEY_BOLTS = "bolts";
    public static final String YAML_KEY_STREAMS = "streams";
    public static final String YAML_KEY_ID = "id";
    public static final String YAML_KEY_CLASS_NAME = "className";
    public static final String YAML_KEY_CONSTRUCTOR_ARGS = "constructorArgs";
    public static final String YAML_KEY_REF = "ref";
    public static final String YAML_KEY_ARGS = "args";
    public static final String YAML_KEY_CONFIG_METHODS = "configMethods";
    public static final String YAML_KEY_FROM = "from";
    public static final String YAML_KEY_TO = "to";
    public static final String YAML_KEY_GROUPING = "grouping";
    public static final String YAML_KEY_TYPE = "type";
    public static final String YAML_PARSED_TUPLES_STREAM =
            "parsed_tuples_stream";
    public static final String YAML_FAILED_TO_PARSE_TUPLES_STREAM =
            "failed_to_parse_tuples_stream";
    public static final String YAML_KEY_STREAM_ID = "streamId";

    public enum DataSourceType {
        KAFKA {
            boolean isValidConfig (Map config) {
                String zkUrl = (String) config.get(JSON_KEY_ZK_URL);
                String topic = (String) config.get(JSON_KEY_TOPIC);
                if (StringUtils.isEmpty(zkUrl) || StringUtils.isEmpty(topic)) {
                    return false;
                }
                return true;
            }
        };
        abstract boolean isValidConfig (Map config);
    }

    public enum ProcessorType {
        RULE {
            boolean isValidConfig (Map config) {
                //TODO: Add rule related config check here
                return true;
            }
        };
        abstract boolean isValidConfig (Map config);
    }

    public enum DataSinkType {
        HBASE {
            boolean isValidConfig (Map config) {
                String rootDir = (String) config.get(JSON_KEY_ROOT_DIR);
                String table = (String) config.get(JSON_KEY_TABLE);
                String columnFamily = (String) config.get(JSON_KEY_COLUMN_FAMILY);
                String rowKey = (String) config.get(JSON_KEY_ROW_KEY);
                if (StringUtils.isEmpty(rootDir) || StringUtils.isEmpty
                        (table) || StringUtils.isEmpty(columnFamily) ||
                        StringUtils.isEmpty(rowKey)) {
                    return false;
                }
                return true;
            }
        },
        HDFS {
            boolean isValidConfig (Map config) {
                String fsUrl = (String) config.get(JSON_KEY_FS_URL);
                String path = (String) config.get(JSON_KEY_PATH);
                String name = (String) config.get(JSON_KEY_NAME);
                if (StringUtils.isEmpty(fsUrl) || StringUtils.isEmpty (path)
                        || StringUtils.isEmpty(name)) {
                    return false;
                }
                return true;
            }
        };
        abstract boolean isValidConfig (Map config);
    }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hortonworks.bolt;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import com.hortonworks.bolt.rules.RulesBolt;
import com.hortonworks.iotas.common.IotasEvent;
import com.hortonworks.iotas.common.IotasEventImpl;
import com.hortonworks.iotas.layout.runtime.processor.RuleProcessorRuntimeStorm;
import com.hortonworks.iotas.layout.runtime.rule.RuleRuntimeStorm;
import com.hortonworks.iotas.layout.runtime.rule.topology.RuleProcessorMockBuilder;
import com.hortonworks.iotas.layout.runtime.rule.topology.RulesTopologyTest;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.VerificationsInOrder;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

@RunWith(JMockit.class)
public class RulesBoltTest extends RulesTopologyTest {
    protected static final Logger log = LoggerFactory.getLogger(RulesBoltTest.class);

    // JUnit constructs for printing which tests are being run
    public
    @Rule
    TestName testName = new TestName();
    public
    @Rule
    TestWatcher watchman = new TestWatcher() {
        @Override
        public void starting(final Description method) {
            log.debug("RUNNING TEST [{}] ", method.getMethodName());
        }
    };

    private static final IotasEvent IOTAS_EVENT = new IotasEventImpl(new HashMap<String, Object>() {{
        put(RuleProcessorMockBuilder.TEMPERATURE, 101);
        put(RuleProcessorMockBuilder.HUMIDITY, 51);
    }}, "dataSrcId_1", "1");

    private static final Values IOTAS_EVENT_VALUES = new Values(IOTAS_EVENT);

    private static final IotasEvent IOTAS_EVENT_INVALID_FIELDS = new IotasEventImpl(new HashMap<String, Object>() {{
        put("non_existent_field1", 101);
        put("non_existent_field2", 51);
        put("non_existent_field3", 23);
    }}, "dataSrcId_2", "2");

    private static final IotasEvent IOTAS_EVENT_GOOD_AND_INVALID_FIELDS = new IotasEventImpl(new HashMap<String, Object>() {{
        put("non_existent_field1", 101);
        put(RuleProcessorMockBuilder.TEMPERATURE, 101);
        put("non_existent_field2", 51);
        put(RuleProcessorMockBuilder.HUMIDITY, 51);
        put("non_existent_field3", 23);
    }}, "dataSrcId_2", "3");

    private static final Values IOTAS_EVENT_GOOD_AND_INVALID_FIELDS_VALUES = new Values(IOTAS_EVENT_GOOD_AND_INVALID_FIELDS);

    private
    @Tested
    RulesBolt rulesBolt;
    private
    @Injectable
    OutputCollector mockOutputCollector;
    private
    @Injectable
    Tuple mockTuple;
    private RuleProcessorRuntimeStorm ruleProcessorRuntime;

    @Before
    public void setup() throws Exception {
        ruleProcessorRuntime = createRulesProcessorRuntime();
        rulesBolt = (RulesBolt) createRulesBolt(ruleProcessorRuntime);
    }

    @Test
    public void test_tupleAllFieldsValid_oneRuleEvaluates_acks() throws Exception {
        new Expectations() {{
            mockTuple.getValues();
            result = IOTAS_EVENT_VALUES;
            mockTuple.getValueByField(IotasEvent.IOTAS_EVENT);
            returns(IOTAS_EVENT);
        }};

        executeAndVerifyCollectorAcks(1, IOTAS_EVENT_VALUES);
    }

    @Test
    public void test_tupleSomeFieldsValid_oneRuleEvaluates_acks() throws Exception {
        new Expectations() {{
            mockTuple.getValues();
            result = IOTAS_EVENT_GOOD_AND_INVALID_FIELDS_VALUES;
            mockTuple.getValueByField(IotasEvent.IOTAS_EVENT);
            returns(IOTAS_EVENT_GOOD_AND_INVALID_FIELDS);
        }};

        executeAndVerifyCollectorAcks(1, IOTAS_EVENT_GOOD_AND_INVALID_FIELDS_VALUES);
    }

    @Test
    public void test_tupleInvalidFields_ruleDoesNotEvaluate_acks() throws Exception {
        new Expectations() {{
            mockTuple.getValueByField(IotasEvent.IOTAS_EVENT);
            returns(null);
        }};

        executeAndVerifyCollectorAcks(0, null);
    }

    @Test
    public void test_tupleInvalidFields_ruleDoesNotEvaluate_fails() throws Exception {
//        test_tupleAllFieldsValid_oneRuleEvaluates_acks();
        new Expectations() {{
            mockTuple.getValueByField(IotasEvent.IOTAS_EVENT);
            returns(IOTAS_EVENT_INVALID_FIELDS);
        }};

        executeAndVerifyCollectorAcks(0, null);
    }

    private void executeAndVerifyCollectorAcks(final int rule2NumTimes, final Values expectedValues) {
        rulesBolt.execute(mockTuple);

        new VerificationsInOrder() {{
            mockOutputCollector.emit(((RuleRuntimeStorm) ruleProcessorRuntime.getRulesRuntime().get(0)).getStreamId(),
                    mockTuple, IOTAS_EVENT_VALUES);
            times = 0;  // rule 1 does not trigger

            Values actualValues;
            mockOutputCollector.emit(((RuleRuntimeStorm) ruleProcessorRuntime.getRulesRuntime().get(1)).getStreamId(),
                    mockTuple, actualValues = withCapture());
            times = rule2NumTimes;    // rule 2 triggers rule2NumTimes

            Assert.assertEquals(expectedValues, actualValues);
            mockOutputCollector.ack(mockTuple);
            times = 1;
        }};
    }

    private void executeAndVerifyCollectorFails(final boolean isSuccess, final int rule2NumTimes, final Values expectedValues) {
        rulesBolt.execute(mockTuple);
        new VerificationsInOrder() {{
            mockOutputCollector.fail(mockTuple);
        }};
    }
}

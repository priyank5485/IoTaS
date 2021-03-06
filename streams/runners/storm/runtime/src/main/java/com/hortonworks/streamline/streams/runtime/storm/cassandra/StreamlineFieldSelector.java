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
package com.hortonworks.streamline.streams.runtime.storm.cassandra;

import org.apache.storm.cassandra.query.selector.FieldSelector;
import org.apache.storm.tuple.ITuple;
import com.hortonworks.streamline.streams.StreamlineEvent;


/**
 *
 */
public class StreamlineFieldSelector extends FieldSelector {

    public StreamlineFieldSelector(String field, String as) {
        this(field, as, false);
    }

    public StreamlineFieldSelector(String field, String as, boolean isNow) {
        super(field);
        as(as);
        if(isNow) {
            now();
        }
    }

    protected Object getFieldValue(ITuple tuple) {
        StreamlineEvent streamlineEvent = (StreamlineEvent) tuple.getValueByField(StreamlineEvent.STREAMLINE_EVENT);

//        return streamlineEvent.get(field);
        return null;
    }

}

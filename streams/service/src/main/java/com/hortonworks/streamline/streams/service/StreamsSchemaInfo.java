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
package com.hortonworks.streamline.streams.service;

import com.google.common.base.Preconditions;
import com.hortonworks.registries.schemaregistry.SchemaMetadata;
import com.hortonworks.registries.schemaregistry.SchemaVersion;

/**
 *
 */
public class StreamsSchemaInfo {
    private SchemaMetadata schemaMetadata;
    private SchemaVersion schemaVersion;

    /*for jackson*/
    private StreamsSchemaInfo() {
    }

    public StreamsSchemaInfo(SchemaMetadata schemaMetadata, SchemaVersion schemaVersion) {
        Preconditions.checkNotNull(schemaMetadata, "schemaMetadata can not be null");
        Preconditions.checkNotNull(schemaVersion, "schemaVersion can not be null");

        this.schemaMetadata = schemaMetadata;
        this.schemaVersion = schemaVersion;
    }

    public SchemaMetadata getSchemaMetadata() {
        return schemaMetadata;
    }

    public SchemaVersion getSchemaVersion() {
        return schemaVersion;
    }
}

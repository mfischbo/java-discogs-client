/*
 *
 *   Copyright 2017 M. Fischboeck
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package net.fischboeck.discogs.model.release;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author M.Fischböck
 */
public class Series {

    private long id;

    private String name;

    @JsonProperty("entity_type")
    private String entityType;

    @JsonProperty("resource_url")
    private String resourceUrl;

    @JsonProperty("entity_type_name")
    private String entityTypeName;

    @JsonProperty("catno")
    private String catalogNumber;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEntityType() {
        return entityType;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public String getEntityTypeName() {
        return entityTypeName;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }
}

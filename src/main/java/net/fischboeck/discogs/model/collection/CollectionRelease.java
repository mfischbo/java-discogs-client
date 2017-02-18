//
//  Copyright 2016 M. Fischboeck
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
package net.fischboeck.discogs.model.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import net.fischboeck.discogs.model.BaseRelease;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRelease {

	private long id;
	
	@JsonProperty("instance_id")
	private long instanceId;
	
	private int rating;
	
	@JsonProperty("folder_id")
	private long folderId;
	
	@JsonProperty("date_added")
	private OffsetDateTime dateAdded;
	
	@JsonProperty("basic_information")
	private BaseRelease releaseBasics;

	public long getId() {
		return id;
	}

	public long getInstanceId() {
		return instanceId;
	}

	public int getRating() {
		return rating;
	}

	public long getFolderId() {
		return folderId;
	}

	public OffsetDateTime getDateAdded() {
		return dateAdded;
	}

	public BaseRelease getReleaseBasics() {
		return releaseBasics;
	}
}

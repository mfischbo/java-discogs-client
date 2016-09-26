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

package net.fischboeck.discogs.model.label;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sublabel {

	@JsonProperty("resource_url")
	private String resourceUrl;
	
	private long id;
	
	private String name;

	/**
	 * Returns an URL to this entity
	 * @return The URL
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}

	/**
	 * Returns the id of this entity
	 * @return The id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns the name of this (sub/parent) label
	 * @return
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sublabel [id=" + id + ", name=" + name + "]";
	}
}

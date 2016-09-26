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

package net.fischboeck.discogs.model.artist;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an alias name for an {@link Artist}.
 * 
 * @author M. Fischboeck
 *
 */
public class Alias {

	private long id;

	@JsonProperty("resource_url")
	private String resourceUrl;
	private String name;

	/**
	 * Returns the id for that alias
	 * @return The id
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Returns the resource URL for this alias
	 * @return The resource URL 
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}
	
	/**
	 * Returns the name of this alias
	 * @return The actual alias name
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
		return "Alias [id=" + id + ", name=" + name + "]";
	}
}

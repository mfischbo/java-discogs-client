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
 * Represents a person that is member of an {@link Artist}.
 * 
 * @author M. Fischboeck
 *
 */
public class Member {

	private long id;
	private boolean active;
	private String name;
	
	@JsonProperty("resource_url")
	private String resourceUrl;

	/**
	 * Returns the id of this member
	 * @return The id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns whether or not the member is being considered an active member
	 * @return True if the member is active, false otherwise
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Returns the name of the member
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a URL to the the entity
	 * @return The URL
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}
}

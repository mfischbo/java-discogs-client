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

import java.util.List;

import net.fischboeck.discogs.model.Image;
import net.fischboeck.discogs.model.release.Release;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a music label / company that is publishing music
 * 
 * @author M. Fischboeck
 *
 */
public class Label {

	private String profile;
	
	@JsonProperty("releases_url")
	private String releasesUrl;
	
	private String name;
	
	@JsonProperty("contact_info")
	private String contactInfo;
	
	private String uri;
	
	private List<Sublabel> sublabels;
	
	@JsonProperty("urls")
	private String websites;
	
	private List<Image> images;
	
	@JsonProperty("resource_url")
	private String resourceUrl;
	
	private long id;
	
	@JsonProperty("data_quality")
	private String dataQuality;

	@JsonProperty("parent_label")
	private Sublabel parentLabel;

	/**
	 * Returns some descriptive information about the label
	 * @return The labels profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * Returns an URL to retrieve {@link Release}s for the label 
	 * @return A URL
	 */
	public String getReleasesUrl() {
		return releasesUrl;
	}

	/**
	 * Returns the name of the label
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns contact information about this label
	 * @return The contact information
	 */
	public String getContactInfo() {
		return contactInfo;
	}

	/**
	 * Returns a URI to this resource
	 * @return The URI
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Returns a list of {@link Sublabel}s if the label has any.
	 * @return A list of sublabels. Might be null.
	 */
	public List<Sublabel> getSublabels() {
		return sublabels;
	}

	/**
	 * Returns a list of URL's to web sites of this label
	 * @return A list of URL's or null if none are available
	 */
	public String getWebsites() {
		return websites;
	}

	/**
	 * Returns a list of {@link Image}s for the label
	 * @return A list of {@link Image}s or null if none are available
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * Returns a URL to this entity 
	 * @return The URL
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}

	/**
	 * Returns the id of this label
	 * @return The id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns a string representing the data quality of this entity
	 * @return The data quality string
	 */
	public String getDataQuality() {
		return dataQuality;
	}

	/**
	 * Returns a {@link Sublabel} that is the parent label for this label.
	 * @return The parent label or null if not available
	 */
	public Sublabel getParentLabel() {
		return parentLabel;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Label [name=" + name + ", contactInfo=" + contactInfo + ", id="
				+ id + ", dataQuality=" + dataQuality + "]";
	}
}

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

import java.util.List;

import net.fischboeck.discogs.model.Image;
import net.fischboeck.discogs.model.release.Release;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents an actual artist.
 * In most cases this are musicians, however sound-engineers, producers, etc. may also appear as artists
 *  
 * @author M. Fischboeck
 *
 */
public class Artist {

	private long id;
	
	@JsonProperty("namevariations")
	private List<String> nameVariations;
	
	private String name;
	
	private List<Alias> aliases;
	
	private String profile;
	
	@JsonProperty("releases_url")
	private String releasesUrl;
	
	@JsonProperty("resource_url")
	private String resourceUrl;
	
	private String uri;
	
	@JsonProperty("urls")
	private List<String> websites;
	
	@JsonProperty("data_quality")
	private String dataQuality;
	
	private List<Image> images;
	
	private List<Member> members;

	/**
	 * Returns the id for this artist
	 * @return The id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Returns a list of name variations.
	 * That are different kinds of spelling the name
	 * @return A list of variations of the artists name
	 */
	public List<String> getNameVariations() {
		return nameVariations;
	}

	/**
	 * Returns the artists official name
	 * @return The name of the artist
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns a list of {@link Alias}es the artist is also known as
	 * @return The list of {@link Alias}es
	 */
	public List<Alias> getAliases() {
		return aliases;
	}

	/**
	 * Returns the artists profile
	 * @return The artists profile
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * Returns an URL from where all {@link Release}es of a artist can be retrieved
	 * @return The URL
	 */
	public String getReleasesUrl() {
		return releasesUrl;
	}

	/**
	 * Returns the URL to this entity
	 * @return The URL
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}

	/**
	 * Returns an URI to this resource
	 * @return The URI
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Returns a list of URL's to web pages incorporated with the artist 
	 * @return A list of URL's
	 */
	public List<String> getWebsites() {
		return websites;
	}

	/**
	 * Returns a string for that represents the quality of data for this entity
	 * @return The data quality string
	 */
	public String getDataQuality() {
		return dataQuality;
	}

	/**
	 * Returns a list of images for the artist
	 * @return A List of {@link Image}es
	 */
	public List<Image> getImages() {
		return images;
	}

	/**
	 * If the artist is a band, returns a list of {@link Member}s.
	 * @return The list of band {@link Member}s or null if the artist is not considered a band
	 */
	public List<Member> getMembers() {
		return members;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", aliases=" + aliases
				+ ", members=" + members + "]";
	}
}

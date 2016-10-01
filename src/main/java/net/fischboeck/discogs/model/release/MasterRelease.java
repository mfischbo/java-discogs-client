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

package net.fischboeck.discogs.model.release;

import java.util.List;

import net.fischboeck.discogs.model.Image;
import net.fischboeck.discogs.model.Track;
import net.fischboeck.discogs.model.Video;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MasterRelease {

	private long id;
	
	private String title;
	
	@JsonProperty("main_release")
	private long mainRelease;
	
	@JsonProperty("main_release_url")
	private String mainReleaseUrl;
	
	@JsonProperty("uri")
	private String URI;
	
	private List<String> styles;
	private List<String> genres;

	private List<ReleaseArtist> artists;
	
	private List<Video> videos;

	@JsonProperty("versions_url")
	private String versionsUrl;
	
	private Long year;
	
	private List<Image> images;

	@JsonProperty("resource_url")
	private String resourceUrl;
	
	private List<Track> tracklist;

	@JsonProperty("num_for_sale")
	private Integer amountForSale;

	@JsonProperty("lowest_price")
	private Double lowestPrice;

	@JsonProperty("data_quality")
	private String dataQuality;
	
	private String notes;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public long getMainRelease() {
		return mainRelease;
	}

	public String getMainReleaseUrl() {
		return mainReleaseUrl;
	}

	public String getURI() {
		return URI;
	}

	public List<String> getStyles() {
		return styles;
	}

	public List<String> getGenres() {
		return genres;
	}

	public List<ReleaseArtist> getArtists() {
		return artists;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public String getVersionsUrl() {
		return versionsUrl;
	}

	public Long getYear() {
		return year;
	}

	public List<Image> getImages() {
		return images;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public List<Track> getTracklist() {
		return tracklist;
	}

	public Integer getAmountForSale() {
		return amountForSale;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public String getDataQuality() {
		return dataQuality;
	}
	
	public String getNotes() {
		return notes;
	}

	@Override
	public String toString() {
		return "MasterRelease [id=" + id + ", title=" + title + ", artists="
				+ artists + ", year=" + year + "]";
	}
}

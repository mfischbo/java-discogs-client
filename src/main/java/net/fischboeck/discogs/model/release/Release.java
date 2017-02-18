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

import com.fasterxml.jackson.annotation.JsonProperty;
import net.fischboeck.discogs.model.BaseRelease;
import net.fischboeck.discogs.model.Image;
import net.fischboeck.discogs.model.Track;
import net.fischboeck.discogs.model.Video;

import java.time.OffsetDateTime;
import java.util.List;

public class Release extends BaseRelease {

	@JsonProperty("data_quality")
	private String dataQuality;
	
	private Community community;
	
	private List<Company> companies;
	
	private String country;

	@JsonProperty("date_added")
	private OffsetDateTime dateAdded;

	@JsonProperty("date_changed")
	private OffsetDateTime dateChanged;
	
	@JsonProperty("estimated_weight")
	private double estimatedWeight;

	@JsonProperty("extraartists")
	private List<ReleaseArtist> extraArtists;
	
	@JsonProperty("format_quantity")
	private int formatQuantity;
	
	private List<String> genres;
	
	private List<String> styles;
	
	private List<Identifier> identifiers;
	
	private List<Image> images;
	
	@JsonProperty("lowest_price")
	private Double lowestPrice;
	
	@JsonProperty("master_id")
	private int masterId;
	
	@JsonProperty("master_url")
	private String masterUrl;
	
	private String notes;
	
	@JsonProperty("num_for_sale")
	private int numForSale;
	
	private String released;

	@JsonProperty("released_formatted")
	private String releasedFormatted;

	private List<Series> series;

	private String status;
	
	private List<Track> tracklist;
	
	private String uri;
	
	private List<Video> videos;

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public List<ReleaseArtist> getArtists() {
		return artists;
	}

	public String getDataQuality() {
		return dataQuality;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public Community getCommunity() {
		return community;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public String getCountry() {
		return country;
	}

	public OffsetDateTime getDateAdded() {
		return dateAdded;
	}

	public OffsetDateTime getDateChanged() {
		return dateChanged;
	}

	public double getEstimatedWeight() {
		return estimatedWeight;
	}

	public List<ReleaseArtist> getExtraArtists() {
		return extraArtists;
	}

	public int getFormatQuantity() {
		return formatQuantity;
	}

	public List<Format> getFormats() {
		return formats;
	}

	public List<String> getGenres() {
		return genres;
	}

	public List<String> getStyles() {
		return styles;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public List<Image> getImages() {
		return images;
	}

	public List<Company> getLabels() {
		return labels;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public int getMasterId() {
		return masterId;
	}

	public String getMasterUrl() {
		return masterUrl;
	}

	public String getNotes() {
		return notes;
	}

	public int getNumForSale() {
		return numForSale;
	}

	public String getReleased() {
		return released;
	}

	public String getReleasedFormatted() {
		return releasedFormatted;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public List<Series> getSeries() {
		return series;
	}

	public String getStatus() {
		return status;
	}

	public List<Track> getTracklist() {
		return tracklist;
	}

	public String getUri() {
		return uri;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public Integer getYear() {
		return year;
	}
}

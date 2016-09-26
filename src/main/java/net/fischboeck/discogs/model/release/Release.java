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

import java.time.OffsetDateTime;
import java.util.List;

import net.fischboeck.discogs.model.Image;
import net.fischboeck.discogs.model.Track;
import net.fischboeck.discogs.model.Video;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Release {

	private long id;
	private String title;
	private List<ReleaseArtist> artists;

	@JsonProperty("data_quality")
	private String dataQuality;
	
	@JsonProperty("thumb")
	private String thumbnail;
	
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
	
	private List<Format> formats;
	
	private List<String> genres;
	
	private List<String> styles;
	
	private List<Identifier> identifiers;
	
	private List<Image> images;
	
	private List<Company> labels;

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
	
	@JsonProperty("resource_url")
	private String resourceUrl;
	
	private List<String> series;
	
	private String status;
	
	private List<Track> tracklist;
	
	private String uri;
	
	private List<Video> videos;
	
	private Integer year;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ReleaseArtist> getArtists() {
		return artists;
	}

	public void setArtists(List<ReleaseArtist> artists) {
		this.artists = artists;
	}

	public String getDataQuality() {
		return dataQuality;
	}

	public void setDataQuality(String dataQuality) {
		this.dataQuality = dataQuality;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public OffsetDateTime getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(OffsetDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}

	public OffsetDateTime getDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(OffsetDateTime dateChanged) {
		this.dateChanged = dateChanged;
	}

	public double getEstimatedWeight() {
		return estimatedWeight;
	}

	public void setEstimatedWeight(double estimatedWeight) {
		this.estimatedWeight = estimatedWeight;
	}

	public List<ReleaseArtist> getExtraArtists() {
		return extraArtists;
	}

	public void setExtraArtists(List<ReleaseArtist> extraArtists) {
		this.extraArtists = extraArtists;
	}

	public int getFormatQuantity() {
		return formatQuantity;
	}

	public void setFormatQuantity(int formatQuantity) {
		this.formatQuantity = formatQuantity;
	}

	public List<Format> getFormats() {
		return formats;
	}

	public void setFormats(List<Format> formats) {
		this.formats = formats;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getStyles() {
		return styles;
	}

	public void setStyles(List<String> styles) {
		this.styles = styles;
	}

	public List<Identifier> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<Identifier> identifiers) {
		this.identifiers = identifiers;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<Company> getLabels() {
		return labels;
	}

	public void setLabels(List<Company> labels) {
		this.labels = labels;
	}

	public Double getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(Double lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public String getMasterUrl() {
		return masterUrl;
	}

	public void setMasterUrl(String masterUrl) {
		this.masterUrl = masterUrl;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getNumForSale() {
		return numForSale;
	}

	public void setNumForSale(int numForSale) {
		this.numForSale = numForSale;
	}

	public String getReleased() {
		return released;
	}

	public void setReleased(String released) {
		this.released = released;
	}

	public String getReleasedFormatted() {
		return releasedFormatted;
	}

	public void setReleasedFormatted(String releasedFormatted) {
		this.releasedFormatted = releasedFormatted;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public List<String> getSeries() {
		return series;
	}

	public void setSeries(List<String> series) {
		this.series = series;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Track> getTracklist() {
		return tracklist;
	}

	public void setTracklist(List<Track> tracklist) {
		this.tracklist = tracklist;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
}

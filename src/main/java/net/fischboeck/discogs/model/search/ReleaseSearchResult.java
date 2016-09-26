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

package net.fischboeck.discogs.model.search;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReleaseSearchResult {

	private List<String> style;
	
	@JsonProperty("thumb")
	private String thumbnail;
	
	private List<String> format;
	
	private String country;
	
	private List<String> barcode;
	
	private String uri;
	
	private List<String> label;

	@JsonProperty("catno")
	private String catNo;
	
	private Integer year;
	
	private List<String> genre;
	
	private String title;
	
	@JsonProperty("resourc_url")
	private String resourceUrl;
	
	private long id;

	public List<String> getStyle() {
		return style;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public List<String> getFormat() {
		return format;
	}

	public String getCountry() {
		return country;
	}

	public List<String> getBarcode() {
		return barcode;
	}

	public String getUri() {
		return uri;
	}

	public List<String> getLabel() {
		return label;
	}

	public String getCatNo() {
		return catNo;
	}

	public Integer getYear() {
		return year;
	}

	public List<String> getGenre() {
		return genre;
	}

	public String getTitle() {
		return title;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public long getId() {
		return id;
	}
}

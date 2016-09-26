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

public class SimpleRelease {

	@JsonProperty("catno")
	private String catNo;
	
	@JsonProperty("artist")
	private String artistName;
	
	private long id;
	
	@JsonProperty("main_release")
	private long mainReleaseId;
	
	@JsonProperty("resource_url")
	private String resourceUrl;
	
	private String role;
	
	@JsonProperty("thumb")
	private String thumbnail;
	
	private String title;
	
	private String type;
	
	private Integer year;
	
	private String status;
	
	private String format;
	
	private String label;
	
	private String trackinfo;

	public String getArtistName() {
		return artistName;
	}

	public long getId() {
		return id;
	}

	public long getMainReleaseId() {
		return mainReleaseId;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public String getRole() {
		return role;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public String getTitle() {
		return title;
	}

	public String getType() {
		return type;
	}

	public Integer getYear() {
		return year;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getFormat() {
		return format;
	}
	
	public String getLabel() {
		return label;
	}

	public String getTrackinfo() {
		return trackinfo;
	}

	@Override
	public String toString() {
		return "ArtistRelease [artistName=" + artistName + ", id=" + id
				+ ", title=" + title + "]";
	}
}

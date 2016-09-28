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

package net.fischboeck.discogs.model;

import java.util.List;

import net.fischboeck.discogs.model.release.Company;
import net.fischboeck.discogs.model.release.Format;
import net.fischboeck.discogs.model.release.ReleaseArtist;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRelease {

	protected long id;

	@JsonProperty("resource_url")
	protected String resourceUrl;
	
	protected String title;

	protected List<ReleaseArtist> artists;
	
	protected List<Company> labels;
	protected List<Format> formats;
	
	@JsonProperty("thumb")
	protected String thumbnail;
	
	protected Integer year;

	public long getId() {
		return id;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public String getTitle() {
		return title;
	}

	public List<ReleaseArtist> getArtists() {
		return artists;
	}

	public List<Company> getLabels() {
		return labels;
	}

	public List<Format> getFormats() {
		return formats;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public Integer getYear() {
		return year;
	}
}

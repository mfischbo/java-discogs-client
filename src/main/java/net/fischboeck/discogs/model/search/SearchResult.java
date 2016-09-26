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

import java.util.ArrayList;
import java.util.List;

import net.fischboeck.discogs.deserializer.SearchResultDeserializer;
import net.fischboeck.discogs.model.PageInfo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = SearchResultDeserializer.class)
public class SearchResult {

	public SearchResult() {
		
	}
	
	public SearchResult(PageInfo pageInfo) {
		this.page = pageInfo;
	}
	
	private PageInfo page;
	private List<ArtistSearchResult> artistResults;
	private List<ReleaseSearchResult> masterResults;
	private List<ReleaseSearchResult> releaseResults;
	private List<LabelSearchResult> labelResults;

	public PageInfo getPage() {
		return page;
	}

	public List<ArtistSearchResult> getArtistResults() {
		if (artistResults == null)
			artistResults = new ArrayList<ArtistSearchResult>();
		return artistResults;
	}
	
	public List<ReleaseSearchResult> getMasterReleaseResults() {
		if (masterResults == null)
			masterResults = new ArrayList<ReleaseSearchResult>();
		return masterResults;
	}
	
	public List<ReleaseSearchResult> getReleaseResults() {
		if (releaseResults == null)
			releaseResults = new ArrayList<ReleaseSearchResult>();
		return releaseResults;
	}
	
	public List<LabelSearchResult> getLabelResults() {
		if (labelResults == null) 
			labelResults = new ArrayList<LabelSearchResult>();
		return labelResults;
	}
}

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

import net.fischboeck.discogs.PageRequest;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Generic type to wrap a list of objects that are returned from any pageable API.
 * 
 * @author M. Fischboeck
 *
 * @param <T> 
 */
public class Page<T> {

	@JsonProperty("pagination")
	private PageInfo page;

	private List<T> _content;

	public PageInfo getPage() {
		return page;
	}

	public List<T> getContent() {
		return this._content;
	}
	
	@JsonAnySetter
	public void set(String name, List<T> value) {
		this._content = value;
	}
	
	public boolean hasNextPage() {
		return page.getCurrentPage() < page.getTotalPages();
	}
	
	public PageRequest nextPage() {
		return new PageRequest(page.getCurrentPage() + 1,
				page.getItemsPerPage());
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Page [page=" + page + "]";
	}
}

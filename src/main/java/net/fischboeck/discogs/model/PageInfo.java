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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Encapsulates data about the current page that is retrieved in any pageaple API request.
 *
 * @author M. Fischboeck
 */

@JsonIgnoreProperties({"urls"})
public class PageInfo {

	@JsonProperty("per_page")
	private int itemsPerPage;

	@JsonProperty("items")
	private int numberOfItems;
	
	@JsonProperty("page")
	private int currentPage;
	
	@JsonProperty("pages")
	private int totalPages;

	/**
	 * Returns the amount of items on the current page
	 * @return The amount of items
	 */
	public int getItemsPerPage() {
		return itemsPerPage;
	}

	/**
	 * Returns the amount of objects that can be retrieved for the given request
	 * @return The amount of total items
	 */
	public int getNumberOfItems() {
		return numberOfItems;
	}

	/**
	 * Returns the current page
	 * @return The current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Returns the total amount of pages that can be retrieved using the
	 * page size that yielded this PageInfo
	 * @return The maximum amount of pages
	 */
	public int getTotalPages() {
		return totalPages;
	}
}

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

package net.fischboeck.discogs;

/**
 * Provides a way for the user to specify the page he wants to retrieve in pagable requests.
 * The default page size is 50 and the default page is 1.
 * 
 * @author M. Fischboeck
 *
 */
public class PageRequest {

	public static final int DEFAULT_PAGE = 1;
	public static final int DEFAULT_PAGE_SIZE = 50;
	
	private int page;
	private int size;

	/**
	 * Constructs a new PageRequest with default values
	 */
	public PageRequest() {
		this.page = DEFAULT_PAGE;
		this.size = DEFAULT_PAGE_SIZE;
	}

	/**
	 * Constructs a PageRequest with the specified values
	 * @param page The page that should be retrieved
	 * @param size The maximum amount of items on that page
	 */
	public PageRequest(int page, int size) {
		this.page = page;
		this.size = size;
	}

	/**
	 * @return The current page number for this request
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @return The maximum amount of items for that page
	 */
	public int getSize() {
		return size;
	}
}

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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class encapsulates data representing an image that can be retrieved.
 * 
 * @author M. Fischboeck
 *
 */
public class Image {

	public enum ImageType {
		primary, secondary
	}
	
	private int height;
	private int width;
	
	@JsonProperty("resource_url")
	private String resourceUrl;

	private String uri;
	
	@JsonProperty("uri150")
	private String thumbnailUrl;
	
	private ImageType type;

	/**
	 * Returns the height of the image in pixel
	 * @return The height of the image
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the width of the image in pixel
	 * @return The width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the URL from which this image can be retrieved
	 * @return The url of the image
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}

	/**
	 * Returns the URI property of the image
	 * @return The URI
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * Returns the URL to a minified version of the image
	 * @return The URL
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	/**
	 * Returns whether this image is {@link ImageType#primary} or {@link ImageType#secondary} 
	 * @return The type
	 */
	public ImageType getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Image [height=" + height + ", width=" + width
				+ ", resourceUrl=" + resourceUrl + ", uri=" + uri
				+ ", thumbnailUrl=" + thumbnailUrl + ", type=" + type + "]";
	}
}

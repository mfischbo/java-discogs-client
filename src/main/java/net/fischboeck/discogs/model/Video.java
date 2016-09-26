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

import net.fischboeck.discogs.model.release.Release;

/**
 * Represents a video resource for the given {@link Release}.
 * @author M. Fischboeck
 *
 */
public class Video {

	private int duration;
	private String description;
	private String uri;
	private String title;
	private boolean embed;

	/**
	 * Returns the duration of the video
	 * @return The duration of the video
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Returns a descriptive text about the video
	 * @return The description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the URI for the video
	 * @return The URI
	 */
	public String getUri() {
		return uri;
	}
	
	/**
	 * Returns the title of the video
	 * @return The title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Returns whether or not this video can be embedded 
	 * @return True if the video is embeddable, false otherwise
	 */
	public boolean isEmbed() {
		return embed;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Video [duration=" + duration + ", title=" + title + ", embed="
				+ embed + "]";
	}
}

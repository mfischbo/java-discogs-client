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

import net.fischboeck.discogs.model.release.Release;
import net.fischboeck.discogs.model.release.ReleaseArtist;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a track that can be retrieved from a {@link Release}'s tracklist property.
 * 
 * @author M. Fischboeck
 *
 */
public class Track {

	private String duration;
	private String position;
	
	@JsonProperty("type_")
	private String type;
	
	private String title;

	@JsonProperty("extraartists")
	private List<ReleaseArtist> extraArtists;
	
	private List<ReleaseArtist> artists;

	/**
	 * Returns the duration of the track in the notation "mm:ss"
	 * @return The duration of the track
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Returns the position of this track on the {@link Release}
	 * This might be "1", "2", etc.. on digital releases and 
	 * "A1", "A2", "B1", ... etc on vinyl releases
	 * @return The position of the track
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Returns the type of the track
	 * @return The type of the track
	 */
	public String getType() {
		return type;
	}

	/**
	 * Returns the tracks title
	 * @return The title of the track
	 */
	public String getTitle() {
		return title;
	}
	

	public List<ReleaseArtist> getArtists() {
		return artists;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Track [duration=" + duration + ", position=" + position
				+ ", type=" + type + ", title=" + title + ", extraArtists="
				+ extraArtists + "]";
	}
}

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

public class CommunityRating {

	@JsonProperty("release_id")
	private long releaseId;

	private Rating rating;
	
	public long getReleaseId() {
		return releaseId;
	}

	public Rating getRating() {
		return rating;
	}

	static class Rating {

		@JsonProperty("count")
		private int count;
	
		@JsonProperty("average")
		private double average;

		public int getCount() {
			return count;
		}

		public double getAverage() {
			return average;
		}
	}
}

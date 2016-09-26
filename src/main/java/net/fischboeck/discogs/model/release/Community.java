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

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Community {

	private List<User> contributors;
	private User submitter;
	
	@JsonProperty("data_quality")
	private String dataQuality;
	
	
	private Rating rating;
	
	private String status;
	
	private int have;
	private int want;

	
	public List<User> getContributors() {
		return contributors;
	}
	
	public void setContributors(List<User> contributors) {
		this.contributors = contributors;
	}
	
	public User getSubmitter() {
		return submitter;
	}
	
	public void setSubmitter(User submitter) {
		this.submitter = submitter;
	}
	
	public String getDataQuality() {
		return dataQuality;
	}
	
	public void setDataQuality(String dataQuality) {
		this.dataQuality = dataQuality;
	}
	
	public Rating getRating() {
		return rating;
	}
	
	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getHave() {
		return have;
	}
	
	public void setHave(int have) {
		this.have = have;
	}
	
	public int getWant() {
		return want;
	}
	
	public void setWant(int want) {
		this.want = want;
	}
}


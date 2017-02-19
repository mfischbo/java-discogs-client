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
package net.fischboeck.discogs.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.fischboeck.discogs.model.Currency;

import java.time.OffsetDateTime;

public class UserProfile {

	private long id;
	private String profile;
	
	@JsonProperty("wantlist_url")
	private String wantlistUrl;
	
	private int rank;
	
	@JsonProperty("num_pending")
	private int numPending;
	
	@JsonProperty("num_for_sale")
	private int numForSale;
	
	@JsonProperty("home_page")
	private String homePage;
	
	private String location;
	
	@JsonProperty("collection_folders_url")
	private String collectionFoldersUrl;
	
	private String username;
	
	@JsonProperty("collection_fields_url")
	private String collectionFieldsUrl;
	
	@JsonProperty("releases_contributed")
	private int releasesContributed;
	
	private OffsetDateTime registered;

	@JsonProperty("rating_avg")
	private double ratingAvg;

	@JsonProperty("num_collection")
	private int numCollection;

	@JsonProperty("releases_rated")
	private int releasesRated;

	@JsonProperty("num_lists")
	private int numLists;

	private String name;

	@JsonProperty("num_wantlist")
	private int numWantlist;

	@JsonProperty("inventory_url")
	private String inventoryUrl;

	@JsonProperty("avatar_url")
	private String avatarUrl;

	private String uri;

	@JsonProperty("resource_url")
	private String resourceUrl;

	@JsonProperty("buyer_rating")
	private int buyerRating;

	@JsonProperty("buyer_rating_stars")
	private int buyerRatingStars;

	@JsonProperty("buyer_num_ratings")
	private int buyerNumRatings;

	@JsonProperty("seller_rating")
	private int sellerRating;

	@JsonProperty("seller_rating_stars")
	private int sellerRatingStars;

	@JsonProperty("seller_num_ratings")
	private int sellerNumRatings;

	@JsonProperty("curr_abbr")
	private Currency currency;

	private String email;

	public long getId() {
		return id;
	}

	public String getProfile() {
		return profile;
	}

	public String getWantlistUrl() {
		return wantlistUrl;
	}

	public int getRank() {
		return rank;
	}

	public int getNumPending() {
		return numPending;
	}

	public int getNumForSale() {
		return numForSale;
	}

	public String getHomePage() {
		return homePage;
	}

	public String getLocation() {
		return location;
	}

	public String getCollectionFoldersUrl() {
		return collectionFoldersUrl;
	}

	public String getUsername() {
		return username;
	}

	public String getCollectionFieldsUrl() {
		return collectionFieldsUrl;
	}

	public int getReleasesContributed() {
		return releasesContributed;
	}

	public OffsetDateTime getRegistered() {
		return registered;
	}

	public double getRatingAvg() {
		return ratingAvg;
	}

	public int getNumCollection() {
		return numCollection;
	}

	public int getReleasesRated() {
		return releasesRated;
	}

	public int getNumLists() {
		return numLists;
	}

	public String getName() {
		return name;
	}

	public int getNumWantlist() {
		return numWantlist;
	}

	public String getInventoryUrl() {
		return inventoryUrl;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public String getUri() {
		return uri;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public int getBuyerRating() {
		return buyerRating;
	}

	public int getBuyerRatingStars() {
		return buyerRatingStars;
	}

	public int getBuyerNumRatings() {
		return buyerNumRatings;
	}

	public int getSellerRating() {
		return sellerRating;
	}

	public int getSellerRatingStars() {
		return sellerRatingStars;
	}

	public int getSellerNumRatings() {
		return sellerNumRatings;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getEmail() { return this.email; }
}

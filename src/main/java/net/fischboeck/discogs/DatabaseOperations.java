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

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fischboeck.discogs.commands.UserReleaseRatingUpdateCommand;
import net.fischboeck.discogs.model.Currency;
import net.fischboeck.discogs.model.Page;
import net.fischboeck.discogs.model.artist.Artist;
import net.fischboeck.discogs.model.label.Label;
import net.fischboeck.discogs.model.release.*;
import net.fischboeck.discogs.model.search.SearchResult;
import net.fischboeck.discogs.security.AuthorizationStrategy;
import net.fischboeck.discogs.security.NullAuthorizationStrategy;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Operations implementation to access the database API methods.
 * https://www.discogs.com/developers/#page:database
 */
final public class DatabaseOperations extends BaseOperations {

	public enum QueryParam {
		type, title, release_title, credit, artist, anv, label, genre,
		style, country, year, format, catno, barcode, track, submitter,
		contributor
	}
	
	
	DatabaseOperations(CloseableHttpClient client, ObjectMapper mapper, AuthorizationStrategy strategy) {
		super(client, mapper, strategy);
	}


	/**
	 * Returns the {@link MasterRelease} for the specified id
	 * @param id The id of the {@link MasterRelease} to be retrieved.
	 * @return The {@link MasterRelease} or null if no entity is found for the specified id
	 * @throws ClientException On any communications error
	 */
	public MasterRelease getMasterRelease(long id) throws ClientException {

		String urlBuilder = DEFAULT_BASE_URL +
				"/masters/" +
				id;
		return doGetRequest(urlBuilder, MasterRelease.class);
	}

	/**
	 * Returns the {@link Release} for the specified id
	 * @param id The id of the {@link Release} to be retrieved
	 * @return the {@link Release} or <code>null</code> if no entity exits for the specified id
	 * @throws ClientException On any communications error
	 */
	public Release getRelease(long id) throws ClientException {
		return doGetRequest(fromTokens("/releases/", id), Release.class);
	}

	
	/**
	 * Returns the {@link Release} for the specified id with currencies returned in the specified format
	 * @param id The id of the {@link Release} to be retrieved
	 * @param currency The {@link Currency} to format any monetary values in. Accepts 
	 * <code>null</code> in which this method behaves as {{@link #getRelease(long)} 
	 * @return The {@link Release} or <code>null</code> if no such entity exists
	 * @throws ClientException On any communications error
	 */
	public Release getRelease(long id, Currency currency) throws ClientException {
		
		if (currency == null) {
			return getRelease(id);
		}
		
		return doGetRequest(fromTokens("/releases/", id, "?curr_abbr=", currency), Release.class);
	}

	/**
	 * Returns a {@link Page} of {@link Version} for the specified id of {@link MasterRelease}
	 * @param id The id of the {@link MasterRelease}
	 * @param page The {@link PageRequest} containing paging information or <code>null</code>
	 * @return The {@link Page} of results
	 * @throws ClientException On any communications error
	 */
	public Page<Version> getMasterReleaseVersions(long id, PageRequest page) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
			.constructParametricType(Page.class, Version.class);
		return doGetRequest(fromTokensAndPage(page, "/masters/", id, "/versions"), t);
	}


	/**
	 * Returns the {@link UserReleaseRating} for the specified user and release
	 * @param releaseId The id of the {@link Release} to retrieve the rating for
	 * @param username The name of the user to retrieve the rating for
	 * @return The {@link UserReleaseRating}
	 * @throws ClientException On any unexpected error
	 */
	public UserReleaseRating getUserReleaseRating(long releaseId, String username) throws ClientException {
	
		return doGetRequest(fromTokens("/releases/", releaseId, "/rating/", username), 
				UserReleaseRating.class);
	}


	/**
	 * Updates the release rating for the specified release and user.
	 * Note that this operation requires a valid authorization other than {@link NullAuthorizationStrategy}
	 * @param releaseId The id of the release to update the rating for
	 * @param username The username
	 * @param rating The rating to be set
	 * @return The result of the operation as {@link UserReleaseRating}
	 * @throws ClientException On any unexpected error
	 */
	public UserReleaseRating updateUserReleaseRating(long releaseId, String username, int rating) throws ClientException {

		if (authorizationStrategy == null || authorizationStrategy instanceof NullAuthorizationStrategy) {
			throw new IllegalStateException("This operation requires an authenticated client.");
		}
		
		if (rating < 1 || rating > 5) {
			throw new IllegalArgumentException("The value of rating must be between 1 and 5");
		}
		
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("A username is required for this operation");
		}

		UserReleaseRatingUpdateCommand command = new UserReleaseRatingUpdateCommand(releaseId, username, rating);

		return doPutRequest(fromTokens("/releases/", releaseId, "/rating/", username),
				command, UserReleaseRating.class);
	}


	/**
	 * Deletes the user release rating for the specified release and user.
	 * Note that this request requires a valid authentication other than {@link NullAuthorizationStrategy}
	 * @param releaseId The id of the release to remove the rating for
	 * @param username The username
	 * @throws IllegalStateException If the client is not authenticated
	 * @throws IllegalArgumentException If the username is empty or {@code null}.
	 */
	public void deleteUserReleaseRating(long releaseId, String username) {
		
		if (authorizationStrategy == null || authorizationStrategy instanceof NullAuthorizationStrategy) {
			throw new IllegalStateException("This operation requires an authenticated client");
		}
		
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("A username is required for this operation");
		}
	
		doDeleteRequest(fromTokens("/releases/", releaseId, "/rating/", username));
	}
	

	/**
	 * Returns the {@link CommunityRating} for the {@link Release} specified by it's id
	 * @param releaseId The id of the release
	 * @return The {@link CommunityRating} or <code>null</code> if no rating exists
	 * @throws ClientException On any communications error
	 */
	public CommunityRating getCommunityReleaseRating(long releaseId) throws ClientException {
		
		return doGetRequest(fromTokens("/releases/", releaseId, "/rating"), 
				CommunityRating.class);
	}
	

	/**
	 * Returns the {@link Artist} for the specified id
	 * @param id The id of the artist
	 * @return The {@link Artist} or <code>null</code> if no such artist exists
	 * @throws ClientException On any unexpected error
	 */
	public Artist getArtist(long id) throws ClientException {
		
		return doGetRequest(fromTokens("/artists/", id), Artist.class);
	}

	
	/**
	 * Returns a {@link Page} of {@link SimpleRelease}s for the specified artist
	 * @param artistId The id of the artist to return the {@link SimpleRelease}s for
	 * @param page The paging parameters
	 * @return The page of releases
	 * @throws ClientException On any client error
	 */
	public Page<SimpleRelease> getArtistReleases(long artistId, PageRequest page) throws ClientException {

		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, SimpleRelease.class);
		return doGetRequest(fromTokensAndPage(page, "/artists/", 
				artistId, "/releases"), t);
	}
	

	/**
	 * Returns the {@link Label} for the specified id
	 * @param id The id of the {@link Label}
	 * @return The {@link Label} or <code>null</code> if no such label exists
	 * @throws ClientException On any client error
	 */
	public Label getLabel(long id) throws ClientException {
		
		return doGetRequest(fromTokens("/labels/", id), Label.class);
	}
	

	/**
	 * Returns a list of a labels releases
	 * @param labelId The id of the label to return releases for
	 * @param page The paging parameters
	 * @return A page of {@link SimpleRelease}
	 * @throws ClientException On any client error
	 */
	public Page<SimpleRelease> getLabelReleases(long labelId, PageRequest page) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, SimpleRelease.class);
		return doGetRequest(fromTokensAndPage(page, "/labels/", labelId, "/releases"), t);
	}


	/**
	 * Returns an input stream for a specified image url
	 * @param url The url of the image
	 * @return A {@link InputStream} to read the image data from
	 * @throws ClientException On any unexpected client error
	 */
	public InputStream getImage(String url) throws ClientException {
		return doImageRequest(url);
	}


	/**
	 * Returns a {@link SearchResult} for the given parameters.
	 * Note that this operation is only available with an authenticated client
	 * @param query The search query to look up
	 * @param page The paging parameters
	 * @param params The type of fields to look for
	 * @return A {@link SearchResult}
	 * @throws ClientException On any unexpected error
	 */
	public SearchResult search(String query, PageRequest page, QueryParam ... params) throws ClientException {

		if (query == null || query.isEmpty())
			throw new IllegalArgumentException("Search query must not be null or empty");
		
		if (authorizationStrategy == null || authorizationStrategy instanceof  NullAuthorizationStrategy)
			throw new IllegalStateException("This operation requires an authenticated client");
	
		
		StringBuilder uriBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/database/search");
		uriBuilder = createPageParameters(uriBuilder, page);
		
		if (page == null) {
			uriBuilder.append("?q=");
		} else {
			uriBuilder.append("&q=");
		}
		try {
			uriBuilder.append(URLEncoder.encode(query, "UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			throw new ClientException("Failed to encode the query string. Unsupported encoding UTF-8");
		}
		
		if (params.length > 0) {
			uriBuilder.append("&?");
			
			for (QueryParam p : params) {
				uriBuilder.append(p).append(",");
			}
			uriBuilder.deleteCharAt(uriBuilder.length()-1);
		}
	
		return doGetRequest(uriBuilder.toString(), SearchResult.class);
	}
}

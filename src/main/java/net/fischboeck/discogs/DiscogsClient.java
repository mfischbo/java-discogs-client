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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.fischboeck.discogs.model.Page;
import net.fischboeck.discogs.model.artist.Artist;
import net.fischboeck.discogs.model.label.Label;
import net.fischboeck.discogs.model.release.CommunityRating;
import net.fischboeck.discogs.model.release.MasterRelease;
import net.fischboeck.discogs.model.release.Release;
import net.fischboeck.discogs.model.release.SimpleRelease;
import net.fischboeck.discogs.model.release.UserReleaseRating;
import net.fischboeck.discogs.model.release.Version;
import net.fischboeck.discogs.model.search.SearchResult;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class DiscogsClient {

	private static final String DEFAULT_BASE_URL = "https://api.discogs.com";
	
	public enum Currency {
		USD, GBP, EUR, CAD, AUD, JPY, CHF, MXN, BRL, NZD, SEK, ZAR
	}
	
	public enum QueryParam {
		type, title, release_title, credit, artist, anv, label, genre,
		style, country, year, format, catno, barcode, track, submitter,
		contributor
	}

	private CloseableHttpClient httpClient;
	private ObjectMapper mapper;
	private boolean isAuthenticatedClient;
	
	private String authenticationToken;

	/**
	 * Creates a non authenticated client to the discogs API
	 */
	public DiscogsClient() {
		init();
	}

	/**
	 * Creates an authenticated client to the discogs API
	 * @param authToken The authentication token to be used for requests
	 */
	public DiscogsClient(String authToken) {
		this.authenticationToken = authToken;
		this.isAuthenticatedClient = true;
		init();
	}
	

	private void init() {
		this.httpClient = HttpClients.createDefault();
		mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	}
	

	/**
	 * Returns the {@link MasterRelease} for the specified id
	 * @param id The id of the {@link MasterRelease} to be retrieved.
	 * @return The {@link MasterRelease} or null if no entity is found for the specified id
	 * @throws ClientException On any communications error
	 */
	public MasterRelease getMasterRelease(long id) throws ClientException {

		StringBuilder urlBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/masters/")
			.append(id);
		return doRequest(urlBuilder.toString(), MasterRelease.class);
	}

	/**
	 * Returns the {@link Release} for the specified id
	 * @param id The id of the {@link Release} to be retrieved
	 * @return the {@link Release} or <code>null</code> if no entity exits for the specified id
	 * @throws ClientException On any communications error
	 */
	public Release getRelease(long id) throws ClientException {
		
		StringBuilder urlBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/releases/")
			.append(id);
		return doRequest(urlBuilder.toString(), Release.class);
	}

	
	/**
	 * Returns the {@link Release} for the specified id with currencies returned in the specified format
	 * @param id The id of the {@link Release} to be retrieved
	 * @param currency The {@link Currency} to format any monetary values in. Accepts 
	 * <code>null</code> in which this method behaves as {{@link #getRelease(long)} 
	 * @return The {@link Relase} or <code>null</code> if no such entity exists 
	 * @throws ClientException On any communications error
	 */
	public Release getRelease(long id, Currency currency) throws ClientException {
		
		if (currency == null) {
			return getRelease(id);
		}
		
		StringBuilder urlBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/releases/")
			.append(id)
			.append("?curr_abbr=")
			.append(currency);
		return doRequest(urlBuilder.toString(), Release.class);
	}

	/**
	 * Returns a {@link Page} of {@link Versions} for the specified id of {@link MasterRelease}
	 * @param id The id of the {@link MasterRelease}
	 * @param page The {@link PageRequest} containing paging information or <code>null</code>
	 * @return The {@link Page} of results
	 * @throws ClientException On any communications error
	 */
	public Page<Version> getMasterReleaseVersions(long id, PageRequest page) throws ClientException {
		
		StringBuilder urlBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/masters/")
			.append(id)
			.append("/versions");
		
		urlBuilder = createPageParameters(urlBuilder, page);
		
		JavaType t = mapper.getTypeFactory()
			.constructParametricType(Page.class, Version.class);
		Page<Version> retval = doRequest(urlBuilder.toString(), t);
		return retval;
	}


	/**
	 * 
	 * @param releaseId
	 * @param username
	 * @return
	 * @throws ClientException
	 */
	public UserReleaseRating getUserReleaseRating(long releaseId, String username) throws ClientException {
		
		StringBuilder uriBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/releases/")
			.append(releaseId)
			.append("/rating/")
			.append(username);
		
		return doRequest(uriBuilder.toString(), UserReleaseRating.class);
	}
	
	
	public void updateUserReleaseRating(long releaseId, String username, int rating) throws ClientException {

		if (!this.isAuthenticatedClient) {
			throw new IllegalStateException("This operation requires an authenticated client.");
		}
		
		if (rating < 1 || rating > 5) {
			throw new IllegalArgumentException("The value of rating must be between 1 and 5");
		}
		
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("A username is required for this operation");
		}
		
		throw new ClientException("Not implemented yet");
	}

	
	public void deleteUserReleaseRating(long releaseId, String username) throws ClientException {
		
		if (!this.isAuthenticatedClient) {
			throw new IllegalStateException("This operation requires an authenticated client");
		}
		
		if (username == null || username.isEmpty()) {
			throw new IllegalArgumentException("A username is required for this operation");
		}
	
		throw new ClientException("Not implemented yet");
	}
	

	/**
	 * Returns the {@link CommunityRating} for the {@link Release} specified by it's id
	 * @param releaseId The id of the release
	 * @return The {@link CommunityRating} or <code>null</code> if no rating exists
	 * @throws ClientException On any communications error
	 */
	public CommunityRating getCommunityReleaseRating(long releaseId) throws ClientException {
		
		StringBuilder uriBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/releases/")
			.append(releaseId)
			.append("/rating");
		
		return doRequest(uriBuilder.toString(), CommunityRating.class);
	}
	

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ClientException
	 */
	public Artist getArtist(long id) throws ClientException {
		
		StringBuilder uriBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/artists/")
			.append(id);
		return doRequest(uriBuilder.toString(), Artist.class);
	}

	
	/**
	 * 
	 * @param artistId
	 * @param page
	 * @return
	 * @throws ClientException
	 */
	public Page<SimpleRelease> getArtistReleases(long artistId, PageRequest page) throws ClientException {

		StringBuilder uriBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/artists/")
			.append(artistId)
			.append("/releases");

		uriBuilder = createPageParameters(uriBuilder, page);
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, SimpleRelease.class);
		return doRequest(uriBuilder.toString(), t);
	}
	

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ClientException
	 */
	public Label getLabel(long id) throws ClientException {
		
		StringBuilder b = new StringBuilder(DEFAULT_BASE_URL)
			.append("/labels/")
			.append(id);
		
		return doRequest(b.toString(), Label.class);
	}
	

	/**
	 * 
	 * @param labelId
	 * @param page
	 * @return
	 * @throws ClientException
	 */
	public Page<SimpleRelease> getLabelReleases(long labelId, PageRequest page) throws ClientException {
		
		StringBuilder uriBuilder = new StringBuilder(DEFAULT_BASE_URL)
			.append("/labels/")
			.append(labelId)
			.append("/releases");
		
		uriBuilder = createPageParameters(uriBuilder, page);
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, SimpleRelease.class);
		return doRequest(uriBuilder.toString(), t);
	}


	/**
	 * 
	 * @param query
	 * @param page
	 * @param params
	 * @return
	 * @throws ClientException
	 */
	public SearchResult search(String query, PageRequest page, QueryParam ... params) throws ClientException {

		if (query == null || query.isEmpty())
			throw new IllegalArgumentException("Search query must not be null or empty");
		
		if (!this.isAuthenticatedClient) 
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
			
		}
		
		if (params.length > 0) {
			uriBuilder.append("&?");
			
			for (QueryParam p : params) {
				uriBuilder.append(p).append(",");
			}
			uriBuilder.deleteCharAt(uriBuilder.length()-1);
		}
	
//		JavaType t = mapper.getTypeFactory()
//				.constructParametricType(Page.class, SearchResult.class);
		
		SearchResult retval = doRequest(uriBuilder.toString(), SearchResult.class);
		return retval;
	}

	
	
	private StringBuilder createPageParameters(StringBuilder urlBuilder, PageRequest page) {
		if (page != null) {
			urlBuilder.append("?page=");
			
			if (page.getPage() > PageRequest.DEFAULT_PAGE) {
				urlBuilder.append(page.getPage());
			} else {
				urlBuilder.append(PageRequest.DEFAULT_PAGE);
			}
			
			if (page.getSize() > 0) {
				urlBuilder.append(page.getSize());
			} else {
				urlBuilder.append(PageRequest.DEFAULT_PAGE_SIZE);
			}
		}
		return urlBuilder;
	}
	
	private <T> T doRequest(String url, Class<T> type) throws ClientException {
		
		CloseableHttpResponse response = null;
		
		try {
			response = doHttpRequest(url);
			HttpEntity entity = response.getEntity();
		
			BufferedInputStream in2 = new BufferedInputStream(entity.getContent());
			
			T retval = mapper.readValue(in2, type);
			return retval;
		} catch (EntityNotFoundException ex) {
			return null;
		} catch (JsonMappingException jme) {
			throw new ClientException(jme.getMessage());
		} catch (JsonParseException jpe) {
			throw new ClientException(jpe.getMessage());
		} catch (IOException ioe) {
			throw new ClientException(ioe.getMessage());
		} finally {
			closeSafe(response);
		}
	}
	
	
	private <T> T doRequest(String url, JavaType type) throws ClientException {
		
		CloseableHttpResponse response = null;
		
		try {
			response = doHttpRequest(url);
			HttpEntity entity = response.getEntity();
			
			BufferedInputStream in2 = new BufferedInputStream(entity.getContent());
			
			T retval = mapper.readValue(in2, type);
			return retval;
		} catch (EntityNotFoundException ex) {
			return null;
		} catch (JsonMappingException jme) {
			throw new ClientException(jme.getMessage());
		} catch (JsonParseException jpe) {
			throw new ClientException(jpe.getMessage());
		} catch (IOException ioe) {
			throw new ClientException(ioe.getMessage());
		} finally {
			closeSafe(response);
		}
	}
	
	
	private CloseableHttpResponse doHttpRequest(String url) throws EntityNotFoundException, ClientException {
		
		HttpGet request = new HttpGet(url);
		request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
		
		if (this.authenticationToken != null && !this.authenticationToken.isEmpty()) {
			request.addHeader(HttpHeaders.AUTHORIZATION,
					"Discogs token=" + this.authenticationToken);
		}
		
		CloseableHttpResponse response = null;
		
		try {
			response = this.httpClient.execute(request);
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
				response.close();
				throw new EntityNotFoundException("API returned 404 on request GET " + url);
			}
			
			return response;
		
		} catch (ClientProtocolException cpe) {
			throw new ClientException(cpe.getMessage());
		} catch (IOException ioe) {
			throw new ClientException(ioe.getMessage());
		}
	}

	private void closeSafe(CloseableHttpResponse response) {
		if (response != null) {
			try {
				response.close();
			} catch (IOException ex) {
				// noop
			}
		}
	}
}

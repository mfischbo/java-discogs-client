package net.fischboeck.discogs;

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

import org.apache.http.impl.client.CloseableHttpClient;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

final public class DatabaseOperations extends BaseOperations {

	public enum Currency {
		USD, GBP, EUR, CAD, AUD, JPY, CHF, MXN, BRL, NZD, SEK, ZAR
	}
	
	public enum QueryParam {
		type, title, release_title, credit, artist, anv, label, genre,
		style, country, year, format, catno, barcode, track, submitter,
		contributor
	}
	
	
	DatabaseOperations(CloseableHttpClient client, ObjectMapper mapper) {
		super(client, mapper);
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
		return doGetRequest(urlBuilder.toString(), MasterRelease.class);
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
	 * @return The {@link Relase} or <code>null</code> if no such entity exists 
	 * @throws ClientException On any communications error
	 */
	public Release getRelease(long id, Currency currency) throws ClientException {
		
		if (currency == null) {
			return getRelease(id);
		}
		
		return doGetRequest(fromTokens("/releases/", id, "?curr_abbr=", currency), Release.class);
	}

	/**
	 * Returns a {@link Page} of {@link Versions} for the specified id of {@link MasterRelease}
	 * @param id The id of the {@link MasterRelease}
	 * @param page The {@link PageRequest} containing paging information or <code>null</code>
	 * @return The {@link Page} of results
	 * @throws ClientException On any communications error
	 */
	public Page<Version> getMasterReleaseVersions(long id, PageRequest page) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
			.constructParametricType(Page.class, Version.class);
		Page<Version> retval = doGetRequest(fromTokensAndPage(page, "/masters/", id, "/versions"), t);
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
	
		return doGetRequest(fromTokens("/releases/", releaseId, "/rating/", username), 
				UserReleaseRating.class);
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
		
		return doGetRequest(fromTokens("/releases/", releaseId, "/rating"), 
				CommunityRating.class);
	}
	

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ClientException
	 */
	public Artist getArtist(long id) throws ClientException {
		
		return doGetRequest(fromTokens("/artists/", id), Artist.class);
	}

	
	/**
	 * 
	 * @param artistId
	 * @param page
	 * @return
	 * @throws ClientException
	 */
	public Page<SimpleRelease> getArtistReleases(long artistId, PageRequest page) throws ClientException {

		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, SimpleRelease.class);
		return doGetRequest(fromTokensAndPage(page, "/artists/", 
				artistId, "/releases"), t);
	}
	

	/**
	 * 
	 * @param id
	 * @return
	 * @throws ClientException
	 */
	public Label getLabel(long id) throws ClientException {
		
		return doGetRequest(fromTokens("/labels/", id), Label.class);
	}
	

	/**
	 * 
	 * @param labelId
	 * @param page
	 * @return
	 * @throws ClientException
	 */
	public Page<SimpleRelease> getLabelReleases(long labelId, PageRequest page) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, SimpleRelease.class);
		return doGetRequest(fromTokensAndPage(page, "/labels/", labelId, "/releases"), t);
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
	
		SearchResult retval = doGetRequest(uriBuilder.toString(), SearchResult.class);
		return retval;
	}
}

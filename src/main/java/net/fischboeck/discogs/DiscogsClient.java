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

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.fischboeck.discogs.security.AuthorizationStrategy;
import net.fischboeck.discogs.security.OAuthFlow;
import net.fischboeck.discogs.security.OAuthVector;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Facade class to access the underlying operations.
 * This is the entry point and the class you want to use in order to access the API.
 * The class provides getters for different types of operations. Currently implemented are:
 * <ul>
 *     <li>{@link DatabaseOperations} to retrieve basic data such as artists, releases, master releases and labels</li>
 *     <li>{@link UserCollectionOperations} to modify a users collection (requires authentication)</li>
 *     <li>{@link UserOperations} to gain access to any user specific data</li>
 * </ul>
 */
public final class DiscogsClient {

	private static final int REQUEST_TIMEOUT = 30;

	private CloseableHttpClient httpClient;
	private ObjectMapper mapper;

	/**
	 * Creates a non authenticated client to the discogs API
	 */
	public DiscogsClient() {
		init();
	}


	private void init() {
		HttpClientBuilder builder = HttpClients.custom();
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectionRequestTimeout(REQUEST_TIMEOUT)
				.build();

		this.httpClient = builder.setDefaultHeaders(this.getDefaultHeaders())
				.setDefaultRequestConfig(requestConfig)
				.build();

		this.mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
	
	
	private Collection<Header> getDefaultHeaders() {
		Set<Header> defaultHeaders = new HashSet<Header>();
	
		// set accept and content type headers
		defaultHeaders.add(
				new BasicHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType()));
		defaultHeaders.add(
				new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType()));

		return defaultHeaders;
	}

	/**
	 * Returns a new {@link DatabaseOperations} client
	 * @return A operations client for database operations
	 */
	public DatabaseOperations getDatabaseOperations(AuthorizationStrategy strategy) {
		return new DatabaseOperations(httpClient, mapper, strategy);
	}
	
	/**
	 * Returns the {@link UserCollectionOperations} client
	 * @return The client to access the user collection API's
	 */
	public UserCollectionOperations getUserCollectionOperations(AuthorizationStrategy strategy) {
		return new UserCollectionOperations(httpClient, mapper, strategy);
	}

	/**
	 * Returns the {@link UserOperations} client
	 * @param strategy The {@link AuthorizationStrategy} to be used to authenticate against the API
	 * @return The client for user operations
	 */
	public UserOperations getUserOperations(AuthorizationStrategy strategy) {
		return new UserOperations(httpClient, mapper, strategy);
	}

	/**
	 * Returns a client to establish an OAuth authorization with discogs
	 * @param vector The applications consumer key and consumer secret
	 * @return The DiscogsOAuthFlow to create an authentication
	 */
	public OAuthFlow getOAuthFlow(OAuthVector vector) {
		return new OAuthFlow(this.httpClient, vector);
	}
}

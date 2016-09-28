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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class DiscogsClient {

	private CloseableHttpClient httpClient;
	private ObjectMapper mapper;
	
	private String authToken;
	private String authUsername;
	private String authPassword;
	
	private DatabaseOperations dbOps;
	private UserCollectionOperations userCollectionOps;

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
		this.authToken = authToken;
		init();
	}

	/**
	 * Creates an authenticated client to the discogs API
	 * @param username The username of the user that will be used
	 * @param password The password to the provided username
	 */
	public DiscogsClient(String username, String password) {
		this.authUsername = username;
		this.authPassword = password;
		init();
	}

	
	private void init() {
		HttpClientBuilder builder = HttpClients.custom();
		this.httpClient = builder.setDefaultHeaders(this.getDefaultHeaders())
			.build();
		
		this.mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
	}
	
	
	private Collection<Header> getDefaultHeaders() {
		Set<Header> defaultHeaders = new HashSet<Header>();
	
		// set accept and content type headers
		defaultHeaders.add(
				new BasicHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType()));
		defaultHeaders.add(
				new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType()));

		// set the auth token if provided
		if (this.authToken != null) {
			Header h = new BasicHeader(HttpHeaders.AUTHORIZATION, "Discogs token=" + this.authToken);
			defaultHeaders.add(h);
		}
	
		// otherwise check for username and password
		if (this.authUsername != null && !this.authUsername.isEmpty() &&
				this.authPassword != null && !this.authPassword.isEmpty()) {
			String value = "Discogs key=" + this.authUsername + ", secret=" + this.authPassword;
			Header h = new BasicHeader(HttpHeaders.AUTHORIZATION, value);
			defaultHeaders.add(h);
		}
		
		return defaultHeaders;
	}

	/**
	 * Returns a new {@link DatabaseOperations} client
	 * @return
	 */
	public DatabaseOperations getDatabaseOperations() {
		if (dbOps == null) {
			dbOps = new DatabaseOperations(httpClient, mapper);
		}
		return dbOps;
	}
	
	/**
	 * Returns the {@link UserCollectionOperations} client
	 * @return The client to access the user collection API's
	 */
	public UserCollectionOperations getUserCollectionOperations() {
		if (userCollectionOps == null) {
			userCollectionOps = new UserCollectionOperations(httpClient, mapper);
		}
		return userCollectionOps;
	}
}

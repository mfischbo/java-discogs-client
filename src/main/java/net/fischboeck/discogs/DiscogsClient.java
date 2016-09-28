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

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


public class DiscogsClient {

	private CloseableHttpClient httpClient;
	private ObjectMapper mapper;
	
	private DatabaseOperations dbOps;

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
		init();
	}

	
	private void init() {
		this.httpClient = HttpClients.createDefault();
		this.mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
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
}

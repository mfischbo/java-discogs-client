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

import net.fischboeck.discogs.security.AuthorizationStrategy;
import net.fischboeck.discogs.security.TokenAuthenticationStrategy;
import org.junit.Before;

import java.io.IOException;
import java.util.Properties;

public class AbstractClientTest {

	private AuthorizationStrategy strategy;

	protected String testUsername;
	protected String testAuthToken;

	protected DatabaseOperations dbOps;
	protected UserCollectionOperations userCollectionOps;

	public AbstractClientTest() {
		try {
			Properties testProps = new Properties();
			testProps.load(this.getClass().getResourceAsStream("/test.properties"));
			
			this.testUsername = testProps.getProperty("test.username");
			this.testAuthToken = testProps.getProperty("test.authToken");

			this.strategy = new TokenAuthenticationStrategy(this.testAuthToken);

		} catch (IOException ex) {
			System.err.println("Unable to read src/test/resources/test.properties");
		}
	}
	
	@Before
	public void setup() {
		DiscogsClient client = new DiscogsClient();
		this.dbOps = client.getDatabaseOperations(this.strategy);
		this.userCollectionOps = client.getUserCollectionOperations(this.strategy);

		// don't stress the api
		try {
			Thread.sleep(1000L);
		} catch (Exception ex) {
			// noop
		}
	}
}

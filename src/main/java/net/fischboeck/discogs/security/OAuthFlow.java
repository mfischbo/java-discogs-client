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

package net.fischboeck.discogs.security;

import net.fischboeck.discogs.ClientException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * This class implements a mechanism to authenticate an application agains the Discogs API
 * using the OAuth 1.0a protocol.
 * <br/><br/>
 * Authorization is done in the following steps:<br/>
 * Example:<br/><code>
 * DiscogsClient client = new DiscogsClient();
 * DiscogsOAuthFlow authFlow = client.getOAuthFlow(new OAuthVector(consumerKey, consumerSecret));
 * OAuthCredentials credentials = authFlow.getAuthenticationUrl();
 * \/\/ The credentials now contains a URL you need to point the user to in order to 
 * \/\/ authorize your application. The URL can be obtained by:
 * String redirectUrl = credentials.getRedirectUrl();
 * 
 * \/\/ After the user granted access he/she will be presented a verification code. Which will be
 * \/\/ provided in the authentication step:
 * credentials = authFlow.getAccessToken(credentials, verificationCode);
 * 
 * \/\/ At this point the credentials will contain the access token and the shared secret. Those
 * \/\/ should be persisted in order to be used throughout different sessions.
 * 
 * </code>
 *
 * @author M. Fischboeck
 *
 */
public class OAuthFlow {

	static final String TOKEN_URL = "https://api.discogs.com/oauth/request_token";
	static final String AUTHENTICATION_URL = "https://www.discogs.com/de/oauth/authorize";
	static final String ACCESS_TOKEN_URL = "https://api.discogs.com/oauth/access_token";

	private HttpClient client;
	private OAuthVector vector;

	/**
	 * Creates a new OAuth authentication flow.
	 * @param client The HttpClient to be used for communication with the API endpoint
	 * @param vector The client vector (e.g. consumer key and client secret)
	 */
	public OAuthFlow(HttpClient client, OAuthVector vector) {
		this.vector = vector;
		this.client = client;
	}


	/**
	 * Returns the authentication URL a user should be redirected to.
	 * This usually displays a page on the discogs site where the user can authorize access to the application.
	 * @return The OAuthCredentials that are used to authenticate any further requests
	 * @throws ClientException On any communications error
	 */
	public OAuthCredentials getAuthenticationUrl() throws ClientException {
		
		HttpGet getTempToken = new HttpGet(TOKEN_URL);
		getTempToken.setHeader(HttpHeaders.AUTHORIZATION, getTempTokenHeader());
	
		try {
			HttpResponse response = client.execute(getTempToken);
			HttpEntity entity = response.getEntity();
		
			String body = readInputStream(entity.getContent());
			
			OAuthCredentials cb = new OAuthCredentials();
			return parseTempTokenBody(cb, body);
		} catch (Exception ex) {
			throw new ClientException(ex.getMessage());
		}
	}


	/**
	 * Returns the access token for the user
	 * @param bean The OAuthCredentials retrieved from {@link #getAccessToken(OAuthCredentials, String)}
	 * @param verifierCode The verification code the user has entered
	 * @return The fully authenticated OAuthCredentials that are required for any further authenticated requests
	 * @throws ClientException On any communications error
	 */
	public OAuthCredentials getAccessToken(OAuthCredentials bean, String verifierCode) throws ClientException {

		bean.verifierCode = verifierCode;
		
		HttpPost accessRequest = new HttpPost(ACCESS_TOKEN_URL);
		accessRequest.setHeader(HttpHeaders.AUTHORIZATION, getAccessTokenHeader(bean));
	
		try {
			HttpResponse response = client.execute(accessRequest);
			String body = readInputStream(response.getEntity().getContent());
			return parseAccessTokenBody(bean, body);
		} catch (Exception ex) {
			throw new ClientException(ex.getMessage());
		}
	}

	
	private String getTempTokenHeader() {
		StringBuilder b = new StringBuilder("OAuth ")
			.append("oauth_consumer_key=\"").append(vector.getConsumerKey()).append("\", ")
			.append("oauth_nonce=\"").append(UUID.randomUUID().toString()).append("\", ")
			.append("oauth_signature=\"").append(vector.getClientSecret()).append("&\", ")
			.append("oauth_signature_method=\"").append("PLAINTEXT").append("\", ")
			.append("oauth_timestamp=").append(System.currentTimeMillis() / 1000);
		return b.toString();
		
	}
	
	private OAuthCredentials parseTempTokenBody(OAuthCredentials bean, String body) {
		
		String[] parts = body.split("&");
		bean.sharedSecret = parts[0].substring(parts[0].indexOf("=") + 1);
		bean.tempToken = parts[1].substring(parts[1].indexOf("=") + 1);
		bean.authorizationUrl = AUTHENTICATION_URL + "?oauth_token=" + bean.tempToken;
		return bean;
	}
	
	
	private OAuthCredentials parseAccessTokenBody(OAuthCredentials bean, String body) {
		
		String[] parts = body.split("&");
		bean.accessToken = (parts[1].substring(parts[1].lastIndexOf("=") + 1));
		bean.sharedSecret = (parts[0].substring(parts[0].lastIndexOf("=") + 1));
		return bean;
	}
	
	
	private String getAccessTokenHeader(OAuthCredentials bean) {
		
		StringBuilder b = new StringBuilder("OAuth ")
			.append("oauth_consumer_key=\"").append(vector.getConsumerKey()).append("\", ")
			.append("oauth_nonce=\"").append(UUID.randomUUID().toString()).append("\", ")
			.append("oauth_token=\"").append(bean.tempToken).append("\", ")
			.append("oauth_signature=\"").append(vector.getClientSecret()).append("&").append(bean.sharedSecret).append("\", ")
			.append("oauth_signature_method=\"").append("PLAINTEXT").append("\", ")
			.append("oauth_timestamp=\"").append(System.currentTimeMillis() / 1000).append("\", ")
			.append("oauth_verifier=\"").append(bean.verifierCode).append("\"");
		return b.toString();
	}
	
	
	private String readInputStream(InputStream inStream) throws IOException {
		
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		
		while ((length = inStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		
		return result.toString("UTF-8");
	}
}

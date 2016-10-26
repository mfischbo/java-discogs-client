/*
 *
 *   Copyright 2016 M. Fischboeck
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package net.fischboeck.discogs.security;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * Authorization strategy that uses a secret token to access API endpoints that require authentication.
 * This strategy can be used, when implementing a client that performs actions on behalf of a single user.
 * The api token will be sent as the value of the HTTP Authorization header.
 *
 * Created by M. Fischböck on 03.10.16.
 */
public class TokenAuthenticationStrategy implements AuthorizationStrategy {

    private String token;

    /**
     * Creates a new TokenAuthenticationStrategy given the clients api token
     * @param appToken The API token to be used for authenticated requests
     */
    public TokenAuthenticationStrategy(String appToken) {
        this.token = appToken;
    }

    /**
     * Modifies the provided request to be authenticated.
     * This is done by adding the HTTP Authorization header with the given api token
     * @param request The request to be modified
     * @return The modified request
     */
    public HttpUriRequest authorize(HttpUriRequest request) {
        request.setHeader(HttpHeaders.AUTHORIZATION, "Discogs token="+ this.token);
        return request;
    }
}

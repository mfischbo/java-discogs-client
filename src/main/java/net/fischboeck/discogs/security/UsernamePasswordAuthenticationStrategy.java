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
 * {@link AuthorizationStrategy} that modifies a request to contain the username and password being used for accessing
 * the API.
 * The strategy appends username and password as AUTHORIZATION header in the HTTP request. Since the client strictly
 * uses HTTPS transport security this implementation might be "secure enough" for some applications. For more details
 * please see: https://www.discogs.com/developers/#page:authentication,header:authentication-discogs-auth-flow
 */
public class UsernamePasswordAuthenticationStrategy implements AuthorizationStrategy {

    /**
     * The username to be sent with the request
     */
    private String username;

    /**
     * The password to be sent with the request
     */
    private String password;

    /**
     * Constructor specifying the username and password to be used for authentication
     * @param username The username
     * @param password The password for the specified username.
     */
    public UsernamePasswordAuthenticationStrategy(String username, String password) {
        this.username = username;
        this.password = password;
    }


    /**
     * Modifies the HTTP request by appending the HTTP header 'AUTHORIZATION' with the specified username and password.
     * @param request The HTTP request to be modified
     * @return The modified HTTP request
     */
    public HttpUriRequest authorize(HttpUriRequest request) {
        request.setHeader(HttpHeaders.AUTHORIZATION,
                "Discogs key=" + this.username + ", secret=" + this.password);
        return request;
    }
}

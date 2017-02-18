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

import org.apache.http.client.methods.HttpUriRequest;

/**
 * Implements a no op authorization strategy.
 * This implementation simply returns the request without making any modifications, which results in an
 * unauthenticated request.
 *
 * Created by M.Fischboeck on 03.10.16.
 */
public class NullAuthorizationStrategy implements AuthorizationStrategy {

    public HttpUriRequest authorize(HttpUriRequest request) {
        return request;
    }
}

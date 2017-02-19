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

package net.fischboeck.discogs;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.fischboeck.discogs.commands.UserProfileUpdateCommand;
import net.fischboeck.discogs.model.user.UserIdentity;
import net.fischboeck.discogs.model.user.UserProfile;
import net.fischboeck.discogs.security.AuthorizationStrategy;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * API operations to access a users profile.
 * See: https://www.discogs.com/developers/#page:user-identity
 */
public final class UserOperations extends BaseOperations {

	UserOperations(CloseableHttpClient client, ObjectMapper mapper, AuthorizationStrategy strategy) {
		super(client, mapper, strategy);
	}
	
	
	public UserIdentity getIdentity() throws ClientException {
		return doGetRequest(fromTokens("/oauth/identity"), UserIdentity.class);
	}

	/**
	 * Returns the users profile
	 * @param username The username to return the profile for
	 * @return The profile
	 * @throws ClientException On any unexpected client error
	 */
	public UserProfile getProfile(String username) throws ClientException {
		return doGetRequest(fromTokens("/users/", username), UserProfile.class);
	}


	/**
	 * Updates the profile of the user specified in the {@link UserProfileUpdateCommand}
	 * @param command The command to update the profile
	 * @return The updated profile
	 * @throws ClientException On any unexpected client error
	 */
	public UserProfile updateUserProfile(UserProfileUpdateCommand command) throws ClientException {
		return doPostRequest(fromTokens("/users/", command.getUsername()), command, UserProfile.class);
	}
}

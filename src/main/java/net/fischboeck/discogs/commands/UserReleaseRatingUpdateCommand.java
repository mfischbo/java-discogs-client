/*
 *
 *   Copyright 2017 M. Fischboeck
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

package net.fischboeck.discogs.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author M.Fischböck
 */
public class UserReleaseRatingUpdateCommand {

    @JsonProperty("release_id")
    private long releaseId;

    private String username;

    private int rating;

    public UserReleaseRatingUpdateCommand(long releaseId, String username, int rating) {
        if (rating < 1 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 1 and 5");

        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Requires a valid username.");
        }

        if (releaseId < 1)
            throw new IllegalArgumentException("Illegal release id provided.");

        this.releaseId = releaseId;
        this.username = username;
        this.rating = rating;
    }

    public long getReleaseId() {
        return releaseId;
    }

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }
}

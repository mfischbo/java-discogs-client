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
import net.fischboeck.discogs.model.Currency;
import net.fischboeck.discogs.model.user.UserProfile;

/**
 * @author M.Fischböck
 */
public class UserProfileUpdateCommand {

    private String username;

    private String name;

    @JsonProperty("home_page")
    private String homePage;

    private String location;

    private String profile;

    @JsonProperty("curr_abbr")
    private Currency currencyAbbreveation;


    public UserProfileUpdateCommand() {}

    public UserProfileUpdateCommand(String username, String name, String homePage, String location, String profile,
                                    Currency currencyAbbreveation) {
        this.username = username;
        this.name = name;
        this.homePage = homePage;
        this.location = location;
        this.profile = profile;
        this.currencyAbbreveation = currencyAbbreveation;
    }

    /**
     * Creates a new update command with all values set to the current profile.
     * This is helpful when one wants to modify only a single value
     * @param currentProfile
     */
    public UserProfileUpdateCommand(UserProfile currentProfile) {
        this.username = currentProfile.getUsername();
        this.name = currentProfile.getName();
        this.homePage = currentProfile.getHomePage();
        this.location = currentProfile.getLocation();
        this.profile = currentProfile.getProfile();
        this.currencyAbbreveation = currentProfile.getCurrency();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Currency getCurrencyAbbreveation() {
        return currencyAbbreveation;
    }

    public void setCurrencyAbbreveation(Currency currencyAbbreveation) {
        this.currencyAbbreveation = currencyAbbreveation;
    }
}

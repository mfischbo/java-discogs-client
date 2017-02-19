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

package net.fischboeck.discogs;

import net.fischboeck.discogs.commands.UserProfileUpdateCommand;
import net.fischboeck.discogs.model.user.UserIdentity;
import net.fischboeck.discogs.model.user.UserProfile;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author M.Fischböck
 */
public class UserTest extends AbstractClientTest {

    @Test
    public void canRetrieveUserIdentity() throws Exception {

        UserIdentity identity = userOps.getIdentity();
        assertNotNull(identity);
    }


    @Test
    public void canRetrieveUserProfile() throws Exception {

        UserProfile profile = userOps.getProfile(this.testUsername);
        assertNotNull(profile);
    }

    @Test
    public void canUpdateUserProfile() throws Exception {

        UserProfile profile = userOps.getProfile(this.testUsername);
        UserProfileUpdateCommand command = new UserProfileUpdateCommand(profile);

        String newHomepage = "https://www." + UUID.randomUUID().toString() + "/";
        command.setHomePage(newHomepage);

        UserProfile newProfile = userOps.updateUserProfile(command);
        assertNotNull(newProfile);
        assertEquals(newHomepage, newProfile.getHomePage());
    }
}

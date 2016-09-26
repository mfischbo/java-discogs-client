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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import net.fischboeck.discogs.DiscogsClient.Currency;
import net.fischboeck.discogs.model.release.CommunityRating;
import net.fischboeck.discogs.model.release.Release;
import net.fischboeck.discogs.model.release.UserReleaseRating;

import org.junit.Test;

public class ReleaseTest extends AbstractClientTest {

	@Test
	public void canRetrieveRelease() throws Exception {
		
		Release r = client.getRelease(28813);
		assertNotNull(r);
	}
	

	@Test
	public void canRetrieveReleaseWithCurrency() throws Exception {
		
		Release r = client.getRelease(28813, Currency.EUR);
		assertNotNull(r);
	}
	
	@Test
	public void canRetrieveCommunityRating() throws Exception {
		
		CommunityRating rating = client.getCommunityReleaseRating(335195);
		assertNotNull(rating);
		assertEquals(335195, rating.getReleaseId());
	}
	
	
	@Test
	public void canRetrieveUserReleaseRating() throws Exception {
		
		UserReleaseRating rating = client.getUserReleaseRating(335195, "a.paul");
		assertNotNull(rating);
		assertEquals(335195, rating.getReleaseId());
		assertEquals("a.paul", rating.getUsername());
	}
}

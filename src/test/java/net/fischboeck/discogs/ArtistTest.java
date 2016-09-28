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
import static org.junit.Assert.assertTrue;
import net.fischboeck.discogs.model.Page;
import net.fischboeck.discogs.model.artist.Artist;
import net.fischboeck.discogs.model.release.SimpleRelease;

import org.junit.Test;

public class ArtistTest extends AbstractClientTest {

	@Test
	public void canRetrieveArtist() throws Exception {

		Artist a = dbOps.getArtist(22212);
		assertNotNull(a);
	}
	
	@Test
	public void returnsNullOnArtistNotFound() throws Exception {
		
		Artist a = dbOps.getArtist(-1);
		assertEquals(null, a);
	}
	
	@Test
	public void canRetrieveArtistReleases() throws Exception {
		
		Page<SimpleRelease> releases = dbOps.getArtistReleases(22212, null);
		assertNotNull(releases);
		assertNotNull(releases.getContent());
		assertTrue(releases.getContent().size() > 0);
	}
}

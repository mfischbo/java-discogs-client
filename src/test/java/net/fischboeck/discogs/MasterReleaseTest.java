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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.fischboeck.discogs.model.Page;
import net.fischboeck.discogs.model.release.MasterRelease;
import net.fischboeck.discogs.model.release.Version;

import org.junit.Test;

public class MasterReleaseTest extends AbstractClientTest {

	@Test
	public void canReadMasterRelease() throws Exception {
	
		MasterRelease r = this.dbOps.getMasterRelease(28351);
		assertNotNull(r);
	}
	
	@Test
	public void canRetrieveMasterReleaseVersions() throws Exception {
		
		Page<Version> page = this.dbOps.getMasterReleaseVersions(1000, null);
		assertNotNull(page);
		assertNotNull(page.getContent());
		assertTrue(page.getContent().size() > 0);
	}
}

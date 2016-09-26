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


import net.fischboeck.discogs.DiscogsClient.QueryParam;

import org.junit.Test;

public class SearchTest extends AbstractClientTest {

	@Test(expected = IllegalStateException.class)
	public void failsSearchOnUnauthenticatedClient() throws Exception {
		
		client.search("Pete Rock", null, QueryParam.artist);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void failsOnMissingQueryString() throws Exception {
		
		client.search(null, null, QueryParam.artist);
	}
}

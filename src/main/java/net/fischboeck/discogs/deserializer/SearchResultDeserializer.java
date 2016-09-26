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

package net.fischboeck.discogs.deserializer;

import java.io.IOException;
import java.util.Iterator;

import net.fischboeck.discogs.model.PageInfo;
import net.fischboeck.discogs.model.search.ArtistSearchResult;
import net.fischboeck.discogs.model.search.LabelSearchResult;
import net.fischboeck.discogs.model.search.ReleaseSearchResult;
import net.fischboeck.discogs.model.search.SearchResult;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Deserializer that is used to deserialize the response from the search API. 
 * Since the content retrieved from the search API is not very uniform, 
 * this deserializer is used to retrieve different result items from the response
 * and construct a typed {@link SearchResult} object from it.
 * 
 * @author M. Fischboeck
 *
 */
public class SearchResultDeserializer extends JsonDeserializer<SearchResult> {

	/*
	 * (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public SearchResult deserialize(JsonParser jp, DeserializationContext ctx)
			throws IOException, JsonProcessingException {

		
		ObjectCodec codec = jp.getCodec();
		JsonNode root = jp.getCodec().readTree(jp);
		
		// read the page info
		JsonNode pagination = root.get("pagination");
		PageInfo pageInfo = codec.treeToValue(pagination, PageInfo.class);
	
		SearchResult retval = new SearchResult(pageInfo);
		
		// now iterate through all results and extract each result given the value of the field 'type'
		JsonNode results = root.get("results");
		Iterator<JsonNode> resIt = results.elements();
		while (resIt.hasNext()) {
			JsonNode result = resIt.next();
			String type = result.get("type").asText();

			if ("artist".equals(type)) {
				retval.getArtistResults().add(deserializeResult(result, codec, ArtistSearchResult.class));
				continue;
			}
			
			if ("release".equals(type)) {
				retval.getReleaseResults().add(deserializeResult(result, codec, ReleaseSearchResult.class));
				continue;
			}
			
			if ("master".equals(type)) {
				retval.getMasterReleaseResults().add(deserializeResult(result, codec, ReleaseSearchResult.class));
				continue;
			}
			
			if ("label".equals(type)) {
				retval.getLabelResults().add(deserializeResult(result, codec, LabelSearchResult.class));
				continue;
			}
			
			throw new RuntimeException("Unknown type " + type);
		}
		return retval;
	}

	/**
	 * Deserializes the provided result node into the target class
	 * @param result The JsonNode to be deserialized
	 * @param codec The ObjectCodec to be used for reading the tree
	 * @param type The target class of the return value
	 * @return The object
	 * @throws JsonProcessingException
	 */
	private <T> T deserializeResult(JsonNode result, ObjectCodec codec, Class<T> type) throws JsonProcessingException {
		return codec.treeToValue(result, type);
	}
}

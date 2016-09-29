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

import java.util.List;

import net.fischboeck.discogs.commands.CreateFolderCommand;
import net.fischboeck.discogs.model.NamedCollection;
import net.fischboeck.discogs.model.Page;
import net.fischboeck.discogs.model.collection.CollectionRelease;
import net.fischboeck.discogs.model.collection.CollectionValue;
import net.fischboeck.discogs.model.collection.Folder;

import org.apache.http.impl.client.CloseableHttpClient;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserCollectionOperations extends BaseOperations {

	
	UserCollectionOperations(CloseableHttpClient client, ObjectMapper mapper) {
		super(client, mapper);
	}

	
	public List<Folder> getFoldersByUser(String username) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(NamedCollection.class, Folder.class);
		
		NamedCollection<Folder> retval = doGetRequest(
				fromTokens("/users/", username, "/collection/folders"), t);
		return retval.getContent();
	}

	
	public Folder createFolder(String username, CreateFolderCommand command) throws ClientException {

		return doPostRequest(fromTokens("/users/", username, "/collection/folders"),
				command, Folder.class);
	}
	
	
	public void deleteFolder(String username, long folderId) throws ClientException {
		
		doDeleteRequest(fromTokens("/users/", username, "/collection/folders/", folderId));
	}
	
	
	public Folder getFolderByUsernameAndId(String username, long id, PageRequest page) throws ClientException {
		
		return doGetRequest(
				fromTokens("/users/", username, "/collection/folders/", id),
				Folder.class);
	}
	
	public Page<CollectionRelease> getCollectionReleasesByUsernameAndId(String username, long releaseId) throws ClientException {

		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, CollectionRelease.class);
		
		return doGetRequest(
				fromTokens("/users/", username, "/collection/releases/", releaseId), t);
	}
	
	
	public Page<CollectionRelease> getCollectionReleasesByFolderId(String username, long folderId, PageRequest page) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, CollectionRelease.class);
		return doGetRequest(
				fromTokensAndPage(page, "/users/", username, "/collection/folders/", folderId, "/releases"), t);
	}
	
	public CollectionValue getCollectionValue(String username) throws ClientException {
		
		return doGetRequest(fromTokens("/users/", username, "/collection/value"),
				CollectionValue.class);
	}
}

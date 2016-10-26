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

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.fischboeck.discogs.commands.CreateFolderCommand;
import net.fischboeck.discogs.commands.UpdateFolderCommand;
import net.fischboeck.discogs.model.NamedCollection;
import net.fischboeck.discogs.model.Page;
import net.fischboeck.discogs.model.collection.CollectionRelease;
import net.fischboeck.discogs.model.collection.CollectionValue;
import net.fischboeck.discogs.model.collection.Folder;
import net.fischboeck.discogs.security.AuthorizationStrategy;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.List;

/**
 * Provides API access to all operations regarding a users collection.
 */
class UserCollectionOperations extends BaseOperations {


	/**
	 * Creates a new UserCollectionOperation using the provided client and mapper
	 * @param client The {@link CloseableHttpClient} to be used for requests
	 * @param mapper The {@link ObjectMapper} to de-/serialize data
	 */
	UserCollectionOperations(CloseableHttpClient client, ObjectMapper mapper, AuthorizationStrategy strategy) {
		super(client, mapper, strategy);
	}


	/**
	 * Returns all folders for the given username
	 * @param username The username to return the folders for
	 * @return List containing {@link Folder}s
	 * @throws ClientException ON any communications error
	 */
	List<Folder> getFoldersByUser(String username) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(NamedCollection.class, Folder.class);
		
		NamedCollection<Folder> retval = doGetRequest(
				fromTokens("/users/", username, "/collection/folders"), t);
		return retval.getContent();
	}

	
	/**
	 * Creates a new folder for the given username
	 * @param username The username to create the folder for
	 * @param command The command to create a new folder
	 * @return The folder that has been created
	 * @throws ClientException On any communications error
	 */
	Folder createFolder(String username, CreateFolderCommand command) throws ClientException {

		return doPostRequest(fromTokens("/users/", username, "/collection/folders"),
				command, Folder.class);
	}
	
	
	/**
	 * Updates a folder or more specifically the folders name
	 * @param username The name of the user that owns the folder
	 * @param command The command to update the folder
	 * @return The updated folder
	 * @throws ClientException On any communications error
	 */
	Folder updateFolder(String username, UpdateFolderCommand command) throws ClientException {
		
		return doPostRequest(fromTokens("/users/", username, "/collection/folders/", command.getId()),
				command, Folder.class);
	}
	
	
	/**
	 * Deletes a folder
	 * @param username The name of the user owning the specified folder
	 * @param folderId The id of the folder to be removed
	 * @throws ClientException On any communications error
	 */
	void deleteFolder(String username, long folderId) throws ClientException {
		
		doDeleteRequest(fromTokens("/users/", username, "/collection/folders/", folderId));
	}
	
	
	/**
	 * Returns a single folder given the name of the user and the id of the folder.
	 * @param username The username who owns the folder
	 * @param id The id of the folder to be returned
	 * @return The folder
	 * @throws ClientException On any communications error
	 */
	Folder getFolderByUsernameAndId(String username, long id) throws ClientException {
		
		return doGetRequest(
				fromTokens("/users/", username, "/collection/folders/", id),
				Folder.class);
	}
	
	/**
	 * Returns all releases for a given release id the user added to his collection.
	 * This are the actual physical copies of the release
	 * @param username The name of the user to retrieve {@link CollectionRelease}s for
	 * @param releaseId The id of the release
	 * @return A list of {@link CollectionRelease}s for the given username and id
	 * @throws ClientException On any communications error
	 */
	Page<CollectionRelease> getCollectionReleasesByUsernameAndId(String username, long releaseId) throws ClientException {

		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, CollectionRelease.class);
		
		return doGetRequest(
				fromTokens("/users/", username, "/collection/releases/", releaseId), t);
	}
	

	/**
	 * Returns all {@link CollectionRelease}s for a given username and folder
	 * @param username The name of the user
	 * @param folderId The id of the folder holding the {@link CollectionRelease}s
	 * @param page The paging parameters. Can be null in which case the default page parameters are used
	 * @return A Page of {@link CollectionRelease}s
	 * @throws ClientException On any communications error
	 */
	Page<CollectionRelease> getCollectionReleasesByFolderId(String username, long folderId, PageRequest page) throws ClientException {
		
		JavaType t = mapper.getTypeFactory()
				.constructParametricType(Page.class, CollectionRelease.class);
		return doGetRequest(
				fromTokensAndPage(page, "/users/", username, "/collection/folders/", folderId, "/releases"), t);
	}
	
	
	/**
	 * Returns the monetary value of the collection
	 * @param username The name of the user to retrieve the collection value for
	 * @return The value of the collection
	 * @throws ClientException On any communications error
	 */
	CollectionValue getCollectionValue(String username) throws ClientException {
		
		return doGetRequest(fromTokens("/users/", username, "/collection/value"),
				CollectionValue.class);
	}
}

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

import net.fischboeck.discogs.commands.CreateFolderCommand;
import net.fischboeck.discogs.commands.UpdateFolderCommand;
import net.fischboeck.discogs.model.Page;
import net.fischboeck.discogs.model.collection.CollectionRelease;
import net.fischboeck.discogs.model.collection.CollectionValue;
import net.fischboeck.discogs.model.collection.Folder;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class FolderCollectionTest extends AbstractClientTest {

	static final long UPDATE_LIST_ID = 929495;
	
	@Test
	public void canAccessUserCollections() throws Exception {
		List<Folder> fc = this.userCollectionOps.getFoldersByUser(testUsername);
		assertNotNull(fc);
		assertTrue(fc.size() > 0);
	}
	
	@Test
	public void canAccessFolderByUsernameAndId() throws Exception {
		
		Folder f = this.userCollectionOps.getFolderByUsernameAndId(testUsername, 0);
		assertNotNull(f);
		assertTrue(f.getId() == 0);
	}
	
	
	@Test
	public void canCreateFolderByUsername() throws Exception {

		CreateFolderCommand cf = new CreateFolderCommand("test-folder");
		Folder f = this.userCollectionOps.createFolder(testUsername, cf);
		
		assertNotNull(f);
		assertEquals(f.getName(), cf.getName());
	}

	@Test
	public void canUpdateFolderByUsername() throws Exception {
		
		final String randomName = UUID.randomUUID().toString();
		final String randomNewName = UUID.randomUUID().toString();

		CreateFolderCommand cf = new CreateFolderCommand(randomName);
		Folder f = this.userCollectionOps.createFolder(testUsername, cf);

		assertNotNull(f);
		assertEquals(f.getName(), cf.getName());

		UpdateFolderCommand uc = new UpdateFolderCommand(f.getId(), randomNewName);
		Folder f2 = this.userCollectionOps.updateFolder(testUsername, uc);
		assertNotNull(f2);
		assertEquals(randomNewName, f2.getName());

		// cleanup
		this.userCollectionOps.deleteFolder(testUsername, f2.getId());
	}
	
	
	@Test
	public void canDeleteFolderByUsernameAndId() throws Exception {

		final String folderName = UUID.randomUUID().toString();

		CreateFolderCommand cf = new CreateFolderCommand(folderName);
		Folder f = this.userCollectionOps.createFolder(testUsername, cf);

		List<Folder> folders = this.userCollectionOps.getFoldersByUser(testUsername);
		assertTrue(folders.size() > 2);
		
		this.userCollectionOps.deleteFolder(testUsername, f.getId());
		List<Folder> folders2 = this.userCollectionOps.getFoldersByUser(testUsername);
		assertTrue(folders.size() == (folders2.size() + 1));
	}
	
	
	@Test
	public void canAccessReleasesByFolderIdAndUsername() throws Exception {
		
		Page<CollectionRelease> page = this.userCollectionOps
				.getCollectionReleasesByUsernameAndId(testUsername, 298776);
		assertNotNull(page);
		assertNotNull(page.getContent());
		assertTrue(page.getContent().size() == 1);
	}
	
	@Test
	public void canAccessCollectionReleasesByFolder() throws Exception {
		
		Page<CollectionRelease> page = this.userCollectionOps
				.getCollectionReleasesByFolderId(testUsername, 0, null);
		
		assertNotNull(page);
		assertNotNull(page.getContent());
		assertTrue(page.getContent().size() > 0);
	}
	
	@Test
	public void canAccessCollectionValue() throws Exception {
		
		CollectionValue value = this.userCollectionOps
				.getCollectionValue(testUsername);
		assertNotNull(value);
	}
}

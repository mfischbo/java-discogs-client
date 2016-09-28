package net.fischboeck.discogs.model.collection;

import java.time.OffsetDateTime;

import net.fischboeck.discogs.model.BaseRelease;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CollectionRelease {

	private long id;
	
	@JsonProperty("instance_id")
	private long instanceId;
	
	private int rating;
	
	@JsonProperty("folder_id")
	private long folderId;
	
	@JsonProperty("date_added")
	private OffsetDateTime dateAdded;
	
	@JsonProperty("basic_information")
	private BaseRelease releaseBasics;

	public long getId() {
		return id;
	}

	public long getInstanceId() {
		return instanceId;
	}

	public int getRating() {
		return rating;
	}

	public long getFolderId() {
		return folderId;
	}

	public OffsetDateTime getDateAdded() {
		return dateAdded;
	}

	public BaseRelease getReleaseBasics() {
		return releaseBasics;
	}
}

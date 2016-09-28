package net.fischboeck.discogs.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAnySetter;

public class NamedCollection<T> {

	private List<T> _content;

	@JsonAnySetter
	public void set(String name, List<T> value) {
		this._content = value;
	}
	
	public List<T> getContent() {
		return _content;
	}
}

package net.fischboeck.discogs.commands;

public class CreateFolderCommand {

	private String name;
	
	public CreateFolderCommand(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

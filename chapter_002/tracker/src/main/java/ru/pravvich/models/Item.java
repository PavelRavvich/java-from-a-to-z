package ru.pravvich.models;

public class Item {
	public String nameUser;
	public long create;
	public String header; 
	public String description;
	public int id;
	
	public Item() {

	}
	
	public Item(String nameUser, long create, 
				String header, String description, int id) {
		this.nameUser = nameUser;
		this.create = create;
		this.header = header;
		this.description = description;
		this.id = id;
	}

	public String getNameUser() {
		return nameUser;
	}

	public long getCreate() {
		return create;
	}

	public String getHeader() {
		return header;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}
}
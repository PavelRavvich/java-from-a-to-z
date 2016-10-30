package ru.pravvich.models;

public class Task extends Item {
	public Task(String nameUser, int id) {
		this.nameUser = nameUser;
		this.id = id;
	}

	public String calculatePrise() {
		return "1 000 000";
	}
}
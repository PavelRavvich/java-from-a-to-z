package ru.pravvich.start;

import ru.pravvich.models.*;

public class Tracker {
	public Item[] items = new Item[3];

	public static void main(String[] args) {
		Tracker tracker = new Tracker();
		
		tracker.items[0] = new Item("nameUser", 1L, "header", "description", 1);
		tracker.items[1] = new Task("Lee", 3);
		tracker.items[2] = new Bug();

		for (Item item : tracker.items) {
			if (item instanceof Task) {
				Task task = (Task) item;
				System.out.println(task.calculatePrise());
			}
			System.out.println(item.getNameUser() + " " + item.getId());
		}
	}
}